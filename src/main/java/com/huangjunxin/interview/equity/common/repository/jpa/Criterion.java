package com.huangjunxin.interview.equity.common.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Filename: Criterion
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月26日 13:51
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月26 黄俊鑫 1.0 1.0 Version
 */
public interface Criterion {

    Predicate toPredicate(Root<?> var1, CriteriaQuery<?> var2, CriteriaBuilder var3);

    public static enum Operator {
        EQ,
        NE,
        LIKE,
        GT,
        LT,
        GTE,
        LTE,
        AND,
        OR,
        ISNULL,
        ISNOTNULL;

        private Operator() {
        }
    }
}
