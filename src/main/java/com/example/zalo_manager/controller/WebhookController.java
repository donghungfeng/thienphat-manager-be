package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.ZaloFollowEvent;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.ZaloEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("webhook")
public class WebhookController extends BaseController<ZaloFollowEvent, ZaloFollowEvent>{
    @Autowired
    ZaloEventService zaloEventService;

    public WebhookController() {
        super(ZaloFollowEvent.class);
    }


    @Override
    protected BaseService<ZaloFollowEvent> getService() {
        return null;
    }

    @PostMapping()
    public ResponseEntity<BaseResponse> followEvent(@RequestBody String rawBody) {
        zaloEventService.event(rawBody);
        return ResponseEntity.ok(BaseResponse.success());
    }


}
