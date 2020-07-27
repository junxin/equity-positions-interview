package com.huangjunxin.interview.equity.rest;

import com.huangjunxin.interview.equity.common.exception.BusinessException;
import com.huangjunxin.interview.equity.common.exception.RestException;
import com.huangjunxin.interview.equity.common.exception.ResultDTO;
import com.huangjunxin.interview.equity.entity.Transaction;
import com.huangjunxin.interview.equity.service.PositionsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Filename: TransactionRestController
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月27日 14:30
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月27 黄俊鑫 1.0 1.0 Version
 */
@RestController
public class TransactionRestController {

    @Autowired
    private PositionsService positionsService;

    @ApiOperation("交易下单")
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResultDTO<Transaction>  saveTransaction(@RequestBody Transaction transaction){
        try {
            transaction = positionsService.calculatePositions(transaction);
            return  new ResultDTO<>(transaction);
        } catch (BusinessException e) {
            throw new RestException(HttpStatus.BAD_REQUEST, "ERROR", e.getMessage());
        }
    }

    @ApiOperation("实时仓位")
    @RequestMapping(value = "/transaction/list", method = RequestMethod.GET)
    public  ResultDTO<List<String>> listRealtimePositions(){
        List<String> list = positionsService.listRealtimePositions();
        return new ResultDTO<>(list);
    }





}
