package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.ZaloFollowEvent;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.model.webhook.ZaloFollowEventWebhook;

public interface ZaloFollowEventService extends BaseService<ZaloFollowEvent> {
    BaseResponse create(String req, String signatureHeader);
}
