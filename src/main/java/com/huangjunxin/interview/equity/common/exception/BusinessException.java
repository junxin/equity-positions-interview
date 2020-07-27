package com.huangjunxin.interview.equity.common.exception;

import java.io.Serializable;

/**
 * Filename: BusinessException
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月26日 14:51
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月26 黄俊鑫 1.0 1.0 Version
 */
public class BusinessException extends Exception implements Serializable {
    private static final long serialVersionUID = 1664922714958960385L;
    private String message;
    private String code;

    public BusinessException() {
    }

    public BusinessException(String msg) {
        super(msg);
        this.message = msg;
    }

    public BusinessException(String msg, String code) {
        super(msg);
        this.message = msg;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }
}