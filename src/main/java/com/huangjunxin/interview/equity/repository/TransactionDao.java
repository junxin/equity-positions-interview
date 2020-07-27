package com.huangjunxin.interview.equity.repository;

import com.huangjunxin.interview.equity.common.repository.BaseRepository;
import com.huangjunxin.interview.equity.entity.Transaction;
import org.springframework.data.jpa.repository.Query;

/**
 * Filename: TransactionDao
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月26日 13:32
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月26 黄俊鑫 1.0 1.0 Version
 */
public interface TransactionDao extends BaseRepository<Transaction,Long> {

    @Query("select max(t.version) from Transaction t where t.tradeId = :tradeId")
    Integer getMaxVersionByTradeId(Integer tradeId);

    @Query("select min(t.version) from Transaction t where t.tradeId = :tradeId")
    Integer getMinVersionByTradeId(Integer tradeId);


    @Query(value = "select * from t_transaction  where trade_id = :tradeId order by  version asc LIMIT 1 for update ",nativeQuery = true)
    Transaction getMinVersionByTradeId2(Integer tradeId);
}
