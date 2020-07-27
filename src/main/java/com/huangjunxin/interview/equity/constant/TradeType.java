package com.huangjunxin.interview.equity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Filename: TradeType
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月26日 15:15
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月26 黄俊鑫 1.0 1.0 Version
 */
@AllArgsConstructor
@Getter
public enum TradeType {

    Buy("BUY","买入"),
    Sell("SELL","卖出");

    private  String  code;
    private String name;
}
