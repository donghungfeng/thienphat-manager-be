package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Company;
import com.example.zalo_manager.model.request.CompanyCreateReq;
import com.example.zalo_manager.model.request.CompanyUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.CompanyRepository;
import com.example.zalo_manager.service.CompanyService;
import com.example.zalo_manager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    protected BaseRepository<Company> getRepository() {
        return companyRepository;
    }

    @Override
    public BaseResponse create(CompanyCreateReq req) {
        String validStr = this.createValid(req);
        if (!validStr.isEmpty()){
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), validStr + "Đã tồn tại");
        }
        return BaseResponse.success(this.getRepository().save(MapperUtil.map(req, Company.class)));
    }

    @Override
    public BaseResponse update(CompanyUpdateReq req) {
        String validStr = this.updateValid(req);
        Company company = companyRepository.findAllByIdAndIsActive(req.getId(), 1);
        if (company == null){
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Công ty không tồn tại");
        }
        if (!validStr.isEmpty()){
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), validStr + "Đã tồn tại");
        }
        return BaseResponse.success(this.getRepository().save(MapperUtil.mapValue(req, company)));
    }

    public Boolean existsByName(String name) {
        return companyRepository.existsByName(name);
    }

    public Boolean existsByTaxCode(String taxCode) {
        return companyRepository.existsByTaxCode(taxCode);
    }

    public boolean existsByPhone(String phone) {
        return companyRepository.existsByPhone(phone);
    }

    public Company findById(Long id) {
        return companyRepository.findAllById(id);
    }

    private String createValid(CompanyCreateReq req) {
        if (req.getName() != null && this.existsByName(req.getName())) return  "Tên công ty ";
        if (req.getTaxCode() != null && this.existsByTaxCode(req.getTaxCode())) return "MST Công ty ";
        if (req.getPhone() != null && this.existsByPhone(req.getPhone())) return "SDT Công ty ";
        return "";
    }

    private String updateValid(CompanyUpdateReq req) {
        if (req.getName() != null && this.existsByName(req.getName())) return  "Tên công ty ";
        if (req.getTaxCode() != null && this.existsByTaxCode(req.getTaxCode())) return "MST Công ty ";
        if (req.getPhone() != null && this.existsByPhone(req.getPhone())) return "SDT Công ty ";
        return "";
    }
}
