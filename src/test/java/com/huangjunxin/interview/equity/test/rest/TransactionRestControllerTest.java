package com.huangjunxin.interview.equity.test.rest;

import com.alibaba.fastjson.JSONObject;
import com.huangjunxin.interview.equity.constant.SecurityCode;
import com.huangjunxin.interview.equity.constant.TradeAction;
import com.huangjunxin.interview.equity.constant.TradeType;
import com.huangjunxin.interview.equity.entity.Transaction;
import com.huangjunxin.interview.equity.rest.TransactionRestController;
import com.huangjunxin.interview.equity.test.CommonTestNG;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Filename: TransactionRestControllerTest
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月27日 15:18
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月27 黄俊鑫 1.0 1.0 Version
 */
@Slf4j
public class TransactionRestControllerTest extends CommonTestNG {


    @Autowired
    private TransactionRestController transactionRestController;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(transactionRestController).build();
    }


    @Test
    public  void saveTransactionTest() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTradeId(5);
        transaction.setVersion(1);
        transaction.setQuantity(45);
        transaction.setSecurityCode(SecurityCode.REL.getCode());
        transaction.setTransactionActionCode(TradeAction.Insert.getCode());
        transaction.setTradeTypeCode(TradeType.Buy.getCode());

        String requestJson = JSONObject.toJSONString(transaction);
        String responseString = mockMvc.perform( MockMvcRequestBuilders.post("/transaction").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
        log.info(responseString);
    }

    @Test
    public   void listRealtimePositionsTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/transaction/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

}
