package com.huangjunxin.interview.equity.test.service;

import com.huangjunxin.interview.equity.common.exception.BusinessException;
import com.huangjunxin.interview.equity.constant.SecurityCode;
import com.huangjunxin.interview.equity.constant.TradeAction;
import com.huangjunxin.interview.equity.constant.TradeType;
import com.huangjunxin.interview.equity.entity.Transaction;
import com.huangjunxin.interview.equity.service.PositionsService;
import com.huangjunxin.interview.equity.test.CommonTestNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Filename: PositionsServiceTest
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月27日 12:35
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月27 黄俊鑫 1.0 1.0 Version
 */
public class PositionsServiceTest extends CommonTestNG {

    @Autowired
    private PositionsService positionsService;

    private List<Transaction> mockTransactionList(){
        List<Transaction> transactionList = new ArrayList<>();
        Transaction tran1 = new Transaction(1,1, SecurityCode.REL.getCode(),50, TradeAction.Insert.getCode(), TradeType.Buy.getCode());
        Transaction tran2 = new Transaction(2,1, SecurityCode.ITC.getCode(),40, TradeAction.Insert.getCode(), TradeType.Sell.getCode());
        Transaction tran3 = new Transaction(3,1, SecurityCode.INF.getCode(),70, TradeAction.Insert.getCode(), TradeType.Buy.getCode());
        Transaction tran4 = new Transaction(1,2, SecurityCode.REL.getCode(),60, TradeAction.Update.getCode(), TradeType.Buy.getCode());
        Transaction tran5 = new Transaction(2,2, SecurityCode.ITC.getCode(),30, TradeAction.Cancel.getCode(), TradeType.Buy.getCode());
        Transaction tran6 = new Transaction(4,1, SecurityCode.INF.getCode(),20, TradeAction.Insert.getCode(), TradeType.Sell.getCode());
        transactionList.add(tran1);
        transactionList.add(tran2);
        transactionList.add(tran3);
        transactionList.add(tran4);
        transactionList.add(tran5);
        transactionList.add(tran6);
        return transactionList;
    }

    @Test
    public  void calculatePositionsTest(){
        List<Transaction> transactions = mockTransactionList();
        transactions.forEach(trans -> {
            try {
                positionsService.calculatePositions(trans);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        });
        List<String> positList = positionsService.listRealtimePositions();
        positList.forEach(s -> System.out.println(s));

    }

    @Test
    public void  listRealtimePositionsTest(){
        List<String> positList = positionsService.listRealtimePositions();
        positList.forEach(s -> System.out.println(s));
    }
}
