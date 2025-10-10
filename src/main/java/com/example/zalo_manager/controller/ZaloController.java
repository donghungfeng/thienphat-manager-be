package com.example.zalo_manager.controller;

import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.TemplateRepository;
import com.example.zalo_manager.service.ZaloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("zalo")
public class ZaloController {
    @Autowired
    private ZaloService zaloService;

    @Autowired
    private TemplateRepository tRepository;

    @GetMapping("send")
    public BaseResponse send() throws Exception {
        return zaloService.sendMessage("hello", "6520181963459154601");
    }

    @GetMapping("send_promotion")
    public BaseResponse sendPromotion() throws Exception {
        return zaloService.sendMessagePromotion(tRepository.findById(3L).get().getValue(), "6520181963459154601");
    }

    @GetMapping("send_transaction")
    public BaseResponse sendTransaction() throws Exception {
        return zaloService.sendMessagePromotion("", "6520181963459154601");
    }
}
