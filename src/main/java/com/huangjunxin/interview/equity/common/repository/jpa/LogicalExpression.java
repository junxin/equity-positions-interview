package com.huangjunxin.interview.equity.common.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Filename: LogicalExpression
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月27日 10:25
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月27 黄俊鑫 1.0 1.0 Version
 */
public class LogicalExpression implements  Criterion {
    private Criterion[] criterions; //逻辑表达式中包含的表达式
    private Operator operator; //逻辑运算符

    public LogicalExpression(Criterion[] criterions, Operator operator){
        this.criterions = criterions;
        this.operator = operator;
    }

    @Override
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for(Criterion crit : criterions){
            predicates.add(crit.toPredicate(root,query,builder));
        }
        Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
        switch (operator){
            case OR:
                return  builder.or(predicatesArray);
            case  AND:
                return builder.and(predicatesArray);
            default:
                return null;
        }
    }
}
