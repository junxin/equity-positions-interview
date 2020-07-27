package com.huangjunxin.interview.equity.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Filename: Transaction
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月25日 20:41
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月25 黄俊鑫 1.0 1.0 Version
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "t_transaction")
@Access(AccessType.FIELD)
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    private  Integer tradeId;

    private  int version;

    private  String securityCode;

    private Integer quantity;

    private String transactionActionCode;

    private  String  tradeTypeCode;

    private  int status = 1;

    public Transaction(Integer tradeId, int version, String securityCode,Integer quantity,String transactionActionCode,String  tradeTypeCode){
        this.tradeId = tradeId;
        this.version = version;
        this.securityCode = securityCode;
        this.quantity = quantity;
        this.transactionActionCode = transactionActionCode;
        this.tradeTypeCode = tradeTypeCode;

    }
}
