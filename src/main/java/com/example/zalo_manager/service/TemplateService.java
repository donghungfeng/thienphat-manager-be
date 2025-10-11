package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.Template;
import com.example.zalo_manager.model.request.TemplateCreateReq;
import com.example.zalo_manager.model.request.TemplateUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;

public interface TemplateService extends BaseService<Template>{
    BaseResponse create(TemplateCreateReq req);
    BaseResponse sendTemplate(Long templateId, Long customerId) throws Exception;
    BaseResponse update(TemplateUpdateReq req);
    BaseResponse renderValue(Long customerId, Long templateId);
    String render(String customerId, Object templateId);
}
