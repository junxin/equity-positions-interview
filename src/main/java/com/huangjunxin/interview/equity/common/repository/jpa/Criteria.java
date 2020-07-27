package com.huangjunxin.interview.equity.common.repository.jpa;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Filename: Criteria
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月26日 13:52
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月26 黄俊鑫 1.0 1.0 Version
 */
public class Criteria<T> implements Specification<T> {
    private List<Criterion> criterions = new ArrayList();

    public Criteria() {
    }

    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (!this.criterions.isEmpty()) {
            List<Predicate> predicates = new ArrayList();
            Iterator var5 = this.criterions.iterator();

            while(var5.hasNext()) {
                Criterion c = (Criterion)var5.next();
                predicates.add(c.toPredicate(root, query, builder));
            }

            if (predicates.size() > 0) {
                return builder.and((Predicate[])predicates.toArray(new Predicate[predicates.size()]));
            }
        }

        return builder.conjunction();
    }

    public void add(Criterion criterion) {
        if (criterion != null) {
            this.criterions.add(criterion);
        }

    }
}
