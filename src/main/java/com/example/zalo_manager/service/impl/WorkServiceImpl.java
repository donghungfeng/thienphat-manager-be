package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Work;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.WorkRepository;
import com.example.zalo_manager.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WorkServiceImpl  extends BaseServiceImpl<Work> implements WorkService {
    @Autowired
    private WorkRepository workRepository;
    @Override
    protected BaseRepository<Work> getRepository() {
        return workRepository;
    }


    @Override
    public BaseResponse infor(String username, LocalDate date) {
        return null;
    }
}
