package com.huangjunxin.interview.equity.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 * Filename: RestException
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月27日 14:34
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月27 黄俊鑫 1.0 1.0 Version
 */
public class RestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public HttpStatus status;
    public MediaType mt;
    public String errorCode;

    public RestException() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.mt = MediaType.APPLICATION_JSON;
        this.errorCode = "NOT_DATA";
    }

    public RestException(HttpStatus status) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.mt = MediaType.APPLICATION_JSON;
        this.errorCode = "NOT_DATA";
        this.status = status;
    }

    public RestException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.mt = MediaType.APPLICATION_JSON;
        this.errorCode = "NOT_DATA";
    }

    public RestException(HttpStatus status, String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.mt = MediaType.APPLICATION_JSON;
        this.errorCode = "NOT_DATA";
        this.status = status;
    }

    public RestException(HttpStatus status, String errorcode, String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.mt = MediaType.APPLICATION_JSON;
        this.errorCode = "NOT_DATA";
        this.status = status;
        this.errorCode = errorcode;
    }
}
