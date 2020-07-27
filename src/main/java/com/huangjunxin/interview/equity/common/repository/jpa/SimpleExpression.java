package com.huangjunxin.interview.equity.common.repository.jpa;

import org.hibernate.criterion.MatchMode;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

/**
 * Filename: SimpleExpression
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
public class SimpleExpression implements  Criterion{

    private String fieldName;       //属性名
    private Object value;           //对应值
    private Operator operator;      //计算符
    private MatchMode matchMode; //匹配模式

    public SimpleExpression(String fieldName, Object value, Operator operator){
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;

    }

    public SimpleExpression(String fieldName, Object value, Operator operator,MatchMode matchMode){
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
        this.matchMode = matchMode;

    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }


    @Override
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path expression = null;
        if(fieldName.contains(".")){
            String[] names = StringUtils.split(fieldName, ".");
            expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }
        }else{
            expression = root.get(fieldName);
        }
        switch (operator) {
            case EQ:
                return builder.equal(expression, value);
            case NE:
                return builder.notEqual(expression, value);
            case LIKE:
                if(matchMode == null ){
                    return builder.like((Expression<String>) expression, "%" + value + "%");
                }
                String var = String.valueOf(value);
                String pattern = matchMode.toMatchString(var);
                return builder.like((Expression<String>) expression, pattern);
            case LT:
                return builder.lessThan(expression, (Comparable) value);
            case GT:
                return builder.greaterThan(expression, (Comparable) value);
            case LTE:
                return builder.lessThanOrEqualTo(expression, (Comparable) value);
            case GTE:
                return builder.greaterThanOrEqualTo(expression, (Comparable) value);
            case ISNULL:
                return builder.isNull(expression);
            case ISNOTNULL:
                return builder.isNotNull(expression);
            default:
                return null;
        }
    }
}