package com.example.zalo_manager.repository;

import com.example.zalo_manager.entity.Work;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface WorkRepository extends BaseRepository<Work>{
    Work findByDateAndUsername(LocalDate date, String username);
}
