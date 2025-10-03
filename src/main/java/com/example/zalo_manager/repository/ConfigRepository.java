package com.example.zalo_manager.repository;

import com.example.zalo_manager.entity.Config;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigRepository extends BaseRepository<Config>{
    Optional<Config> findByKey(String key);
}
