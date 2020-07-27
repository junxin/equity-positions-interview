package com.huangjunxin.interview.equity.common.repository.jpa;

import org.hibernate.criterion.MatchMode;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * Filename: Restrictions
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月27日 10:26
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月27 黄俊鑫 1.0 1.0 Version
 */
public class Restrictions {

    /**
     * 等于
     * @param fieldName
     * @param value
     * @return
     */
    public  static SimpleExpression eq(String fieldName, Object value){
        if(!StringUtils.hasText(String.valueOf(value))){
            return null;
        }
        return  new SimpleExpression(fieldName,value, Criterion.Operator.EQ);
    }

    /**
     * 不等于
     * @param fieldName
     * @param value
     * @return
     */
    public  static  SimpleExpression ne(String fieldName, Object value){
        if(!StringUtils.hasText(String.valueOf(value))){
            return null;
        }
        return  new SimpleExpression(fieldName,value, Criterion.Operator.NE);
    }

    public static SimpleExpression like(String fieldName, Object value){
        return like(fieldName, value, MatchMode.ANYWHERE);
    }

    public static SimpleExpression like(String fieldName, Object value, MatchMode matchMode){
        if(!StringUtils.hasText(String.valueOf(value))){
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.LIKE, matchMode);
    }

    /**
     * 大于
     * @param fieldName
     * @param value
     * @return
     */
    public  static  SimpleExpression gt(String fieldName, Object value){
        if(!StringUtils.hasText(String.valueOf(value))){
            return null;
        }
        return  new SimpleExpression(fieldName,value, Criterion.Operator.GT);
    }


    /**
     * 小于
     * @param fieldName
     * @param value
     * @return
     */
    public  static  SimpleExpression lt(String fieldName, Object value){
        if(!StringUtils.hasText(String.valueOf(value))){
            return null;
        }
        return  new SimpleExpression(fieldName,value, Criterion.Operator.LT);
    }


    /**
     * 大于等于
     * @param fieldName
     * @param value
     * @return
     */
    public  static  SimpleExpression gte(String fieldName, Object value){
        if(!StringUtils.hasText(String.valueOf(value))){
            return null;
        }
        return  new SimpleExpression(fieldName,value, Criterion.Operator.GTE);
    }


    /**
     * 小于等于
     * @param fieldName
     * @param value
     * @return
     */
    public  static  SimpleExpression lte(String fieldName, Object value){
        if(!StringUtils.hasText(String.valueOf(value))){
            return null;
        }
        return  new SimpleExpression(fieldName,value, Criterion.Operator.LTE);
    }

    public  static  SimpleExpression isNull(String fieldName){
        return  new SimpleExpression(fieldName,null, Criterion.Operator.ISNULL);
    }

    public  static  SimpleExpression isNotNull(String fieldName){
        return  new SimpleExpression(fieldName,null, Criterion.Operator.ISNOTNULL);
    }

    public  static  LogicalExpression and(Criterion... criterions){
        return new LogicalExpression(criterions, Criterion.Operator.AND);
    }

    public  static  LogicalExpression or(Criterion... criterions){
        return new LogicalExpression(criterions, Criterion.Operator.OR);
    }

    public  static  LogicalExpression in(String fieldName, Collection value){
        if(value == null){
            return null;
        }
        SimpleExpression[]  expressionArray = new SimpleExpression[value.size()];
        int i = 0;
        for(Object var : value){
            expressionArray[i] = new SimpleExpression(fieldName, var, Criterion.Operator.EQ);
            i++;
        }
        return new LogicalExpression(expressionArray, Criterion.Operator.OR);
    }
}
