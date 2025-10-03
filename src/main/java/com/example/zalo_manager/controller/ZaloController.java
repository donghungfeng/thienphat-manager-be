package com.example.zalo_manager.controller;

import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.ZaloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("zalo")
public class ZaloController {
    @Autowired
    private ZaloService zaloService;

    @GetMapping("send")
    public BaseResponse send() throws Exception {
        return zaloService.sendMessage();
    }
}
