package com.example.zalo_manager.controller;

import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("config")
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @GetMapping
    public BaseResponse getConfig(@RequestParam String key){
        return configService.getConfig(key);
    }
}
