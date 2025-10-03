package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Customer;
import com.example.zalo_manager.entity.Template;
import com.example.zalo_manager.model.request.TemplateCreateReq;
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

    public String render(String template, Customer obj) {
        Pattern pattern = Pattern.compile("\\{\\{(.*?)\\}\\}");
        Matcher matcher = pattern.matcher(template);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String fieldName = matcher.group(1).trim(); // ví dụ "name" hoặc "age"

            try {
                // Tạo tên getter: name -> getName, age -> getAge
                String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method method = obj.getClass().getMethod(methodName);
                Object value = method.invoke(obj);

                matcher.appendReplacement(sb, value != null ? value.toString() : "");
            } catch (Exception e) {
                matcher.appendReplacement(sb, ""); // nếu không có getter thì bỏ trống
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
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


}
