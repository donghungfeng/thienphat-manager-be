package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.Company;
import com.example.zalo_manager.model.request.CompanyCreateReq;
import com.example.zalo_manager.model.request.CompanyUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("company")
public class CompanyController extends BaseController<Company, Company> {
    @Autowired
    private CompanyService companyService;

    public CompanyController() {
        super(Company.class);
    }

    @Override
    protected BaseService<Company> getService() {
        return companyService;
    }

    @PostMapping("create")
    public BaseResponse create(@RequestBody @Valid CompanyCreateReq req){
        return companyService.create(req);
    }

    @PutMapping("update")
    public BaseResponse update(@RequestBody @Valid CompanyUpdateReq req){
        return companyService.update(req);
    }
}
