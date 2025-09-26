package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.BaseEntity;
import com.example.zalo_manager.model.request.SearchReq;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    Page<T> search(SearchReq req);

    T getById(Long id) throws Exception;

    List<T> getByActive();

    List<T> getAll();

    void delete(Long id);
}
