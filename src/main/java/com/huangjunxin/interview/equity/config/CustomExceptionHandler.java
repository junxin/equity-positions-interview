package com.huangjunxin.interview.equity.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.huangjunxin.interview.equity.common.exception.RestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

/**
 * Filename: CustomExceptionHandler
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月27日 14:47
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月27 黄俊鑫 1.0 1.0 Version
 */
@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RestException.class})
    public final ResponseEntity<?> handleRestException(RestException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        //将异常信息转换成json
        HashMap<String, Object> errorMap = Maps.newHashMap();
        errorMap.put("code", String.valueOf(ex.status.value()));
        errorMap.put("message", ex.getMessage());
        String error = JSON.toJSONString(errorMap);
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        return handleExceptionInternal(ex, errorMap, headers,ex.status, request);
        // return new ResponseEntity(errorMap, headers, HttpStatus.OK);
    }
}
