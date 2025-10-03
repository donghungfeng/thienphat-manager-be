package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.Template;
import com.example.zalo_manager.model.dto.UserDto;
import com.example.zalo_manager.model.request.TemplateCreateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.TemplateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("template")
public class TemplateController extends BaseController<Template, Template>{
    @Autowired
    private TemplateService templateService;

    public TemplateController() {
        super(Template.class);
    }
    @Override
    protected BaseService<Template> getService() {
        return templateService;
    }

    @PostMapping("create")
    public BaseResponse create(@RequestBody @Valid TemplateCreateReq req){
        return templateService.create(req);
    }

    @GetMapping("send-temp")
    public BaseResponse sendTemp(@RequestParam Long tempId, @RequestParam Long cusId) throws Exception {
        return templateService.sendTemplate(tempId, cusId);
    }
}
