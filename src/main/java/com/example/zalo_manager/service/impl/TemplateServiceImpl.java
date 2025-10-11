package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Customer;
import com.example.zalo_manager.entity.Template;
import com.example.zalo_manager.model.request.TemplateCreateReq;
import com.example.zalo_manager.model.request.TemplateUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.CustomerRepository;
import com.example.zalo_manager.repository.TemplateRepository;
import com.example.zalo_manager.service.TemplateService;
import com.example.zalo_manager.service.ZaloService;
import com.example.zalo_manager.util.MapperUtil;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl extends BaseServiceImpl<Template> implements TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    ZaloService zaloService;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    protected BaseRepository<Template> getRepository() {
        return templateRepository;
    }

    public String render(String template, Object obj) {
        Pattern pattern = Pattern.compile("\\{\\{(.*?)\\}\\}");
        Matcher matcher = pattern.matcher(template);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String fieldPath = matcher.group(1).trim(); // ví dụ "name" hoặc "company.name"
            Object value = null;

            try {
                value = resolveValue(obj, fieldPath);
            } catch (Exception e) {
                value = ""; // nếu lỗi thì để trống
            }

            matcher.appendReplacement(sb, value != null ? Matcher.quoteReplacement(value.toString()) : "");
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * Hàm này cho phép lấy giá trị từ object theo "đường dẫn" kiểu company.name hoặc company.address.city
     */
    private Object resolveValue(Object obj, String fieldPath) throws Exception {
        String[] parts = fieldPath.split("\\.");
        Object current = obj;

        for (String part : parts) {
            if (current == null) return null;
            String methodName = "get" + part.substring(0, 1).toUpperCase() + part.substring(1);
            Method method = current.getClass().getMethod(methodName);
            current = method.invoke(current);
        }

        return current;
    }

    @Override
    public BaseResponse create(TemplateCreateReq req) {
        return BaseResponse.success(templateRepository.save(MapperUtil.map(req, Template.class)));
    }

    @Override
    public BaseResponse sendTemplate(Long templateId, Long customerId) throws Exception {
        Template template = templateRepository.findAllById(templateId);
        Customer customer = customerRepository.findAllById(customerId);
        zaloService.sendMessage(this.render(template.getValue(), customer), customer.getUserId());
        return BaseResponse.success();
    }

    @Override
    public BaseResponse update(TemplateUpdateReq req) {
        Template template = templateRepository.findAllByIdAndIsActive(req.getId(), 1);
        if (template == null) {
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Template không tồn tại");
        }
        return BaseResponse.success(templateRepository.save(MapperUtil.mapValue(req, template)));
    }

    @Override
    public BaseResponse renderValue(Long customerId, Long templateId) {
        Customer customer = customerRepository.findAllByIdAndIsActive(customerId, 1);
        if (customer == null) {
            return BaseResponse.fail(customerId, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Customer không tồn tại");
        }
        Template template = templateRepository.findAllByIdAndIsActive(templateId, 1);
        if (template == null) {
            return BaseResponse.fail(templateId, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Template không tồn tại");
        }
        String result = this.render(template.getValue(), customer);
        return BaseResponse.success(result);
    }


}
