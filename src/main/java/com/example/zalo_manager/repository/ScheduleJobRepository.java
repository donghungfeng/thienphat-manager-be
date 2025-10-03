package com.example.zalo_manager.repository;

import com.example.zalo_manager.entity.ScheduleJob;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleJobRepository extends BaseRepository<ScheduleJob>{
    List<ScheduleJob> findAllByStatusAndIsActive(Integer status, Integer isActive);
}
