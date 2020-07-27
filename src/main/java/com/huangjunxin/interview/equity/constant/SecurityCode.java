package com.huangjunxin.interview.equity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Filename: SecurityCode
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月27日 10:37
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月27 黄俊鑫 1.0 1.0 Version
 */
@AllArgsConstructor
@Getter
public enum SecurityCode {

    REL("REL","REL"),
    ITC("ITC","ITC"),
    INF("INF","INF");

    private  String  code;
    private String name;
}
