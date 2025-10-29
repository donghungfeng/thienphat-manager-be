package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Company;
import com.example.zalo_manager.entity.User;
import com.example.zalo_manager.model.dto.CompanyDto;
import com.example.zalo_manager.model.request.CompanyCreateReq;
import com.example.zalo_manager.model.request.CompanyUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.CompanyRepository;
import com.example.zalo_manager.repository.UserRepository;
import com.example.zalo_manager.service.CompanyService;
import com.example.zalo_manager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected BaseRepository<Company> getRepository() {
        return companyRepository;
    }

    @Override
    public BaseResponse create(CompanyCreateReq req) {
        Company company = MapperUtil.map(req, Company.class);
        if (req.getAccountantId() != null){
            User accountant = userRepository.findAllByIdAndIsActive(req.getAccountantId(), 1);
            if (accountant == null){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Accountant không tồn tại");
            }
            if (accountant.getStatus() == -1){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài khoản accountant đã bị khóa");
            }
            company.setAccountant(accountant);
        }

        if (req.getSupporterId() != null){
            User supporter = userRepository.findAllByIdAndIsActive(req.getSupporterId(), 1);
            if (supporter == null){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Supporter không tồn tại");
            }
            if (supporter.getStatus() == -1){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài supporter khoản đã bị khóa");
            }
            company.setSupporter(supporter);
        }

        if (req.getGeneralAccountantId() != null){
            User generalAccount = userRepository.findAllByIdAndIsActive(req.getGeneralAccountantId(), 1);
            if (generalAccount == null){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "generalAccount không tồn tại");
            }
            if (generalAccount.getStatus() == -1){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài generalAccount khoản đã bị khóa");
            }
            company.setGeneralAccountant(generalAccount);
        }

        if (req.getCollaboratorId() != null){
            User collab = userRepository.findAllByIdAndIsActive(req.getCollaboratorId(), 1);
            if (collab == null){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "collab không tồn tại");
            }
            if (collab.getStatus() == -1){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài collab khoản đã bị khóa");
            }
            company.setCollaborator(collab);
        }
        return BaseResponse.success(MapperUtil.map(companyRepository.save(company), CompanyDto.class));
    }

    @Override
    public BaseResponse update(CompanyUpdateReq req) {
        Company company = companyRepository.findAllByIdAndIsActive(req.getId(), 1);
        if (company == null){
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Công ty không tồn tại");
        }

        if (req.getAccountantId() != null){
            User accountant = userRepository.findAllByIdAndIsActive(req.getAccountantId(), 1);
            if (accountant == null){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Accountant không tồn tại");
            }
            if (accountant.getStatus() == -1){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài khoản accountant đã bị khóa");
            }
            company.setAccountant(accountant);
        }

        if (req.getSupporterId() != null){
            User supporter = userRepository.findAllByIdAndIsActive(req.getSupporterId(), 1);
            if (supporter == null){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Supporter không tồn tại");
            }
            if (supporter.getStatus() == -1){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài supporter khoản đã bị khóa");
            }
            company.setSupporter(supporter);
        }

        if (req.getGeneralAccountantId() != null){
            User generalAccount = userRepository.findAllByIdAndIsActive(req.getGeneralAccountantId(), 1);
            if (generalAccount == null){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "generalAccount không tồn tại");
            }
            if (generalAccount.getStatus() == -1){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài generalAccount khoản đã bị khóa");
            }
            company.setGeneralAccountant(generalAccount);
        }

        if (req.getCollaboratorId() != null){
            User collab = userRepository.findAllByIdAndIsActive(req.getCollaboratorId(), 1);
            if (collab == null){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "collab không tồn tại");
            }
            if (collab.getStatus() == -1){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài collab khoản đã bị khóa");
            }
            company.setCollaborator(collab);
        }

        company = MapperUtil.mapValue(req, company);
        return BaseResponse.success(MapperUtil.map(companyRepository.save(company), CompanyDto.class));
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

    private String createValid(CompanyCreateReq req) {
        if (req.getName() != null && this.existsByName(req.getName())) return  "Tên công ty ";
        if (req.getTaxCode() != null && this.existsByTaxCode(req.getTaxCode())) return "MST Công ty ";
        if (req.getPhone() != null && this.existsByPhone(req.getPhone())) return "SDT Công ty ";
        return "";
    }

    private String updateValid(CompanyUpdateReq req) {
        if (req.getName() != null && companyRepository.existsByNameAndIdNot(req.getName(), req.getId())) return  "Tên công ty ";
        if (req.getTaxCode() != null && companyRepository.existsByTaxCodeAndIdNot(req.getTaxCode(), req.getId())) return "MST Công ty ";
        if (req.getPhone() != null && companyRepository.existsByPhoneAndIdNot(req.getPhone(), req.getId())) return "SDT Công ty ";
        return "";
    }
}
