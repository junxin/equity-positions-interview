package com.huangjunxin.interview.equity.service;

import com.huangjunxin.interview.equity.common.exception.BusinessException;
import com.huangjunxin.interview.equity.common.repository.BaseRepository;
import com.huangjunxin.interview.equity.common.repository.jpa.Criteria;
import com.huangjunxin.interview.equity.common.repository.jpa.Restrictions;
import com.huangjunxin.interview.equity.common.service.BaseService;
import com.huangjunxin.interview.equity.constant.TradeAction;
import com.huangjunxin.interview.equity.constant.TradeType;
import com.huangjunxin.interview.equity.entity.Transaction;
import com.huangjunxin.interview.equity.repository.TransactionDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Filename: TransactionService
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月26日 10:32
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月26 黄俊鑫 1.0 1.0 Version
 */
@Service
@Slf4j
public class TransactionService extends BaseService<Transaction,Integer> {

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public BaseRepository getBaseRepository() {
        return transactionDao;
    }


    public List<Transaction> getAllTransactionList(){
        Criteria<Transaction> criteria = new Criteria<>();
        return getEntityList(criteria);
    }

    public  Transaction signTransaction(Transaction transaction){
        String  tradeTypeCode = transaction.getTradeTypeCode();
        Integer quantity = transaction.getQuantity();
        if (TradeType.Sell.getCode().equals(tradeTypeCode)){
            if (quantity  > 0){
                quantity = quantity * (-1);
            }
        }else if (TradeType.Buy.getCode().equals(tradeTypeCode)){
            if (quantity  < 0){
                quantity = quantity * (-1);
            }
        }
        transaction.setQuantity(quantity);
        return  transaction;

    }

    public  synchronized   Transaction syncSaveTransaction(Transaction transaction) throws BusinessException{
        return saveTransaction(transaction);
    }


    @Transactional
    public     Transaction saveTransaction(Transaction transaction) throws BusinessException {
        int version = transaction.getVersion();
        String transactionActionCode = transaction.getTransactionActionCode();
        Integer tradeId = transaction.getTradeId();
        if (TradeAction.Insert.getCode().equals(transactionActionCode)){
            Integer minVersion = transactionDao.getMinVersionByTradeId(tradeId);
            System.out.println("minVersion="+ minVersion + "当前线程:" + Thread.currentThread().getName());
            if (minVersion != null && version >=  minVersion){
                throw  new BusinessException("插入流水的版本号错误","insert version error");
            }
        }else if(TradeAction.Cancel.getCode().equals(transactionActionCode)){
            Integer maxVersion = transactionDao.getMaxVersionByTradeId(tradeId);
            if( maxVersion != null &&  version <= maxVersion){
                throw  new BusinessException("取消流水的版本号错误","cancel version error");
            }
        }
        transaction = signTransaction(transaction);
        transaction =  saveEntity(transaction);

        if(TradeAction.Cancel.getCode().equals(transactionActionCode)){
            //对于取消流水 需要将该TradeId下的所有流水， 状态都置位0
            Criteria<Transaction> criteria = new Criteria<>();
            criteria.add(Restrictions.eq("tradeId",tradeId));
            List<Transaction>  transactions = getEntityList(criteria);
            if (!CollectionUtils.isEmpty(transactions)){
                transactions.forEach(trans -> {
                    trans.setStatus(-1);
                    updateEntity(trans);
                });
            }

        }

        return  transaction;
    }





}
