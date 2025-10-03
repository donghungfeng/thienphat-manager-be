package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Config;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.ConfigRepository;
import com.example.zalo_manager.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl extends BaseServiceImpl<Config> implements ConfigService {
    @Autowired
    ConfigRepository configRepository;
    @Override
    protected BaseRepository<Config> getRepository() {
        return configRepository;
    }
}
