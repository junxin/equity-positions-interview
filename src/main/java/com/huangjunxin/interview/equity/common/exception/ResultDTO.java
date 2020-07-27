package com.huangjunxin.interview.equity.common.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Filename: ResultDTO
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月27日 14:35
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月27 黄俊鑫 1.0 1.0 Version
 */
@Data
@AllArgsConstructor
@ApiModel(description = "返回类")
public class ResultDTO<T> {

    @ApiModelProperty(value = "状态码")
    private String code;
    @ApiModelProperty(value = "描述")
    private String message;
    @ApiModelProperty(value = "返回对象")
    private T data;

    private static final String CODE_OK = "200";
    private static final  String MESSAGE_OK = "操作成功";

    public ResultDTO(String code, String message) {
        this(code, message, null);
    }

    public ResultDTO(T data){
        this(CODE_OK, MESSAGE_OK, data);
    }

    public ResultDTO(){
        this(CODE_OK, MESSAGE_OK);
    }


    public ResultDTO(Errors errors) {
        StringBuilder msg = new StringBuilder();
        errors.getFieldErrors().forEach((ObjectError error) -> {
            msg.append(error.getDefaultMessage() + "\n");
        });
        this.code = "500";
        this.message = msg.toString();
    }

}
