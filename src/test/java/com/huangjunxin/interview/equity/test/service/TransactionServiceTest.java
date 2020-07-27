package com.huangjunxin.interview.equity.test.service;

import com.huangjunxin.interview.equity.common.exception.BusinessException;
import com.huangjunxin.interview.equity.constant.TradeAction;
import com.huangjunxin.interview.equity.constant.TradeType;
import com.huangjunxin.interview.equity.entity.Transaction;
import com.huangjunxin.interview.equity.service.TransactionService;
import com.huangjunxin.interview.equity.test.CommonTestNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Filename: TransactionServiceTest
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月26日 18:00
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月26 黄俊鑫 1.0 1.0 Version
 */
public class TransactionServiceTest extends CommonTestNG {

    @Autowired
    private TransactionService  transactionService;

    @Test
    public  void  saveTransaction(){
        Transaction transaction = new Transaction();
        transaction.setTradeId(1);
        transaction.setVersion(1);
        transaction.setQuantity(50);
        transaction.setSecurityCode("REL");
        transaction.setTransactionActionCode(TradeAction.Insert.getCode());
        transaction.setTradeTypeCode(TradeType.Buy.getCode());

        try {
            transactionService.saveTransaction(transaction);
        } catch (BusinessException e) {
            e.printStackTrace();
        }

    }

    private  Transaction mockInsertTrade(Integer tradeId){
        Transaction transaction = new Transaction();
        transaction.setTradeId(tradeId);
        transaction.setVersion(1);
        transaction.setQuantity(50);
        transaction.setSecurityCode("REL");
        transaction.setTransactionActionCode(TradeAction.Insert.getCode());
        transaction.setTradeTypeCode(TradeType.Buy.getCode());
        return  transaction;
    }

    @Test(invocationCount = 4,threadPoolSize = 4)
    public  void  parallelSaveTransaction(){
        System.out.println("当前时间("+ new Date()+"),当前线程("+Thread.currentThread().getName()+")准备执行");
        Transaction insertTrade = mockInsertTrade(2);
        try {
            insertTrade = transactionService.syncSaveTransaction(insertTrade);
            System.out.println("insertTrade.id"+ insertTrade.getTransactionId());
        } catch (BusinessException e) {
            e.printStackTrace();
        }

    }
}
