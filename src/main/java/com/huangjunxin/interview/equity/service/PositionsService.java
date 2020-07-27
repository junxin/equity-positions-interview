package com.huangjunxin.interview.equity.service;

import com.huangjunxin.interview.equity.common.exception.BusinessException;
import com.huangjunxin.interview.equity.common.repository.jpa.Criteria;
import com.huangjunxin.interview.equity.common.repository.jpa.Restrictions;
import com.huangjunxin.interview.equity.constant.SecurityCode;
import com.huangjunxin.interview.equity.entity.Transaction;
import com.huangjunxin.interview.equity.vo.Positions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Filename: PositionsService
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月26日 10:11
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月26 黄俊鑫 1.0 1.0 Version
 */
@Service
@Slf4j
public class PositionsService {

    @Autowired
    private  TransactionService transactionService;

    private ConcurrentHashMap<String, Positions> realtimePositionsMap = new ConcurrentHashMap<>();

    @PostConstruct
    public  void  init(){
        log.info("初始化实时仓位缓存容器...");
        for (SecurityCode sc : SecurityCode.values()){
            realtimePositionsMap.put(sc.getCode(), new Positions());
        }
        // 从数据库中加载有效的交易信息（忽略已经取消的tradeId）
        Criteria<Transaction> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("status",1));
        List<Transaction> transactions = transactionService.getEntityList(criteria);

        //分组
        Map<String, List<Transaction>> groupBySecurityCode = transactions.stream().collect(Collectors.groupingBy(Transaction::getSecurityCode));
        //遍历分组
        for (Map.Entry<String, List<Transaction>> entryTransaction : groupBySecurityCode.entrySet()) {
            String key = entryTransaction.getKey();
            List<Transaction> entryTransactionList = entryTransaction.getValue();
            Positions positions = realtimePositionsMap.get(key);
            entryTransactionList.forEach(trans -> positions.addTransaction(trans));

        }

    }


    public  List<String>  listRealtimePositions(){
        List<String> positList = new ArrayList<>();
        for (Map.Entry<String, Positions> entry : realtimePositionsMap.entrySet()) {
            String strValue = Integer.toString(entry.getValue().getCurrentPositions());
            if(entry.getValue().getCurrentPositions() > 0) {
                strValue = "+" + strValue;
            }
            positList.add("股票名称:" + entry.getKey() + ", 当前仓位:" + strValue);

        }
        return positList;
    }



    public Transaction  calculatePositions(Transaction transaction) throws BusinessException {
        transaction = transactionService.syncSaveTransaction(transaction);
        String securityCode = transaction.getSecurityCode();
        Positions positions = realtimePositionsMap.get(securityCode);
        synchronized (positions){
            positions.addTransaction(transaction);
            realtimePositionsMap.put(securityCode,positions);
        }
        return  transaction;



    }




}
