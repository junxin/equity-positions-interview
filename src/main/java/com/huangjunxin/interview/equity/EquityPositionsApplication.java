package com.huangjunxin.interview.equity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Filename: EquityPositionsApplication
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月26日 16:39
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月26 黄俊鑫 1.0 1.0 Version
 */
@EnableJpaRepositories(basePackages = {"com.huangjunxin.interview.equity.repository"})
@EntityScan(basePackages = {"com.huangjunxin.interview.equity.entity"})
@SpringBootApplication(scanBasePackages =  {"com.huangjunxin.interview.equity"})
public class EquityPositionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquityPositionsApplication.class);
    }
}
