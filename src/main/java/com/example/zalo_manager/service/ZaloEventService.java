package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.ZaloFollowEvent;
import com.example.zalo_manager.model.response.BaseResponse;

public interface ZaloEventService extends BaseService<ZaloFollowEvent> {
    void event(String rawBody);
}
