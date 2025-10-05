package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.ZaloFollowEvent;
import com.example.zalo_manager.model.dto.UserDto;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.model.webhook.ZaloFollowEventWebhook;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.ZaloFollowEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("webhook")
public class WebhookController extends BaseController<ZaloFollowEvent, ZaloFollowEvent>{
    @Autowired
    ZaloFollowEventService zaloFollowEventService;

    public WebhookController() {
        super(ZaloFollowEvent.class);
    }


    @Override
    protected BaseService<ZaloFollowEvent> getService() {
        return null;
    }

    @PostMapping("follow-event")
    public ResponseEntity<BaseResponse> followEvent(@RequestBody String rawBody,
                                                    @RequestHeader(value = "X-ZEvent-Signature", required = false) String signatureHeader) {
        return ResponseEntity.ok(zaloFollowEventService.create(rawBody, signatureHeader));
    }
}
