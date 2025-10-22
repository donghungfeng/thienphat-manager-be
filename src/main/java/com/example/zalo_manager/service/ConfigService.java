package com.example.zalo_manager.service;

import com.example.zalo_manager.model.response.BaseResponse;

public interface ConfigService {
    public BaseResponse getConfig(String key);
}
