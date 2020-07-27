package com.huangjunxin.interview.equity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Filename: TradeAction
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月25日 21:20
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月25 黄俊鑫 1.0 1.0 Version
 */
@Getter
@AllArgsConstructor
public enum TradeAction {

    Insert("INSERT","新增"),
    Update("UPDATE","修改"),
    Cancel("CANCEL","删除");

    private  String  code;
    private String name;



}
