package com.huangjunxin.interview.equity.common.service;

import com.huangjunxin.interview.equity.common.repository.BaseRepository;
import com.huangjunxin.interview.equity.common.repository.jpa.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Filename: BaseService
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月26日 13:50
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月26 黄俊鑫 1.0 1.0 Version
 */
public abstract class BaseService<T, ID extends Serializable> {
    public BaseService() {
    }

    public abstract BaseRepository getBaseRepository();

    protected PageRequest buildPageRequest(int pageNumber, int pageSize, List<Sort.Order> orderList) {
        Sort sort = new Sort(orderList);
        return new PageRequest(pageNumber - 1, pageSize, sort);
    }

    public HashMap<String, Object> getEntityListPage(Criteria<T> criteria, int pageNumber, int pageSize) {
        List<Sort.Order> orderList = new ArrayList();
        orderList.add(new Sort.Order(Sort.Direction.DESC, "id"));
        return this.getEntityListPage(criteria, pageNumber, pageSize, orderList);
    }

    public HashMap<String, Object> getEntityListPage(Criteria<T> criteria, int pageNumber, int pageSize, List<Sort.Order> orderList) {
        PageRequest pageRequest = this.buildPageRequest(pageNumber, pageSize, orderList);
        Page<T> page = this.getBaseRepository().findAll(criteria, pageRequest);
        HashMap<String, Object> res = new HashMap();
        res.put("records", page.getContent());
        res.put("totalpage", page.getTotalPages());
        res.put("totalElements", page.getTotalElements());
        return res;
    }

    public T getEntity(ID id) {
        Optional<T> entityOptional = getBaseRepository().findById(id);
        if (entityOptional.isPresent()){
            return  entityOptional.get();
        }
        return null;
    }

    public T getEntity(Criteria<T> criteria) {
        Optional<T> entityOptional = getBaseRepository().findOne(criteria);
        if (entityOptional.isPresent()){
            return  entityOptional.get();
        }
        return null;
    }

    public T saveEntity(T entity) {
        return  (T)getBaseRepository().save(entity);
    }

    public void updateEntity(T entity) {
        this.getBaseRepository().save(entity);
    }

    public void deleteEntity(ID id) {
        this.getBaseRepository().delete(id);
    }

    public List<T> getEntityList(Criteria<T> criteria) {
        return this.getBaseRepository().findAll(criteria);
    }

    public List<T> getEntityList(Criteria<T> criteria, List<Sort.Order> orderList) {
        Sort sort = new Sort(orderList);
        return this.getBaseRepository().findAll(criteria, sort);
    }
}
