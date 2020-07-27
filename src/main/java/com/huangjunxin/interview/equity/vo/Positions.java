package com.huangjunxin.interview.equity.vo;

import com.huangjunxin.interview.equity.constant.TradeAction;
import com.huangjunxin.interview.equity.entity.Transaction;
import lombok.Data;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Filename: Positions
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月25日 20:39
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月25 黄俊鑫 1.0 1.0 Version
 */
@Data
public class Positions {

    private  final  static  int INITIAL_STOCK_POSITIONS = 0;

    private CopyOnWriteArrayList<Transaction> transactionList = new CopyOnWriteArrayList<>();

    private int state;

    private  int initialPositions = INITIAL_STOCK_POSITIONS;

    private int currentPositions = INITIAL_STOCK_POSITIONS;

    public  void addTransaction(Transaction transaction){
        String transactionActionCode = transaction.getTransactionActionCode();

        if (TradeAction.Insert.getCode().equals(transactionActionCode) || TradeAction.Update.getCode().equals(transactionActionCode)){
            transactionList.add(transaction);
        }else{
            for(Transaction trans : transactionList){
                if (trans.getTradeId().equals(transaction.getTradeId())){
                    transactionList.remove(trans);
                }
            }
        }
        transactionList.sort((t1,t2) -> {
            if(t1.getTradeId().intValue() == t2.getTradeId().intValue()){
                return t1.getVersion() - t2.getVersion();
            }else{
                return  t1.getTradeId() - t2.getTradeId();
            }
        });

        if (TradeAction.Insert.getCode().equals(transactionActionCode)){
            Integer currentPos = getCurrentPositions();
            currentPos = currentPos +  transaction.getQuantity();
            setCurrentPositions(currentPos);
        }else if (TradeAction.Update.getCode().equals(transactionActionCode)){
            setCurrentPositions(transaction.getQuantity());
        }else if (TradeAction.Cancel.getCode().equals(transactionActionCode)){
            setCurrentPositions(INITIAL_STOCK_POSITIONS);
        }



    }




}
