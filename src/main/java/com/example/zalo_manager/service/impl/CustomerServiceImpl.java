package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Company;
import com.example.zalo_manager.entity.Customer;
import com.example.zalo_manager.model.dto.zalo.user.detail.ZaloUserData;
import com.example.zalo_manager.model.dto.zalo.user.detail.ZaloUserDetailRes;
import com.example.zalo_manager.model.request.CustomerCreateReq;
import com.example.zalo_manager.model.request.CustomerUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.CompanyRepository;
import com.example.zalo_manager.repository.CustomerRepository;
import com.example.zalo_manager.service.CustomerService;
import com.example.zalo_manager.service.ZaloEventService;
import com.example.zalo_manager.util.DateUtil;
import com.example.zalo_manager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ZaloServiceImpl zaloService;

    @Override
    protected BaseRepository<Customer> getRepository() {
        return customerRepository;
    }

    @Override
    public BaseResponse mapCustomer() {

        return null;
    }

    @Override
    public BaseResponse create(CustomerCreateReq req) {
        if (req.getUserId() != null){
            ZaloUserDetailRes zaloUserDetailRes = zaloService.getUserDetail(req.getUserId());
            Customer customer = zaloUserDetailToCustomer(zaloUserDetailRes);
            return BaseResponse.success(customerRepository.save(customer));
        }
        Customer customer = MapperUtil.map(req, Customer.class);
        if (req.getCompanyId() != null){
            Company company = companyRepository.findAllByIdAndIsActive(req.getCompanyId(), 1);
            if (company == null) {
                return BaseResponse.fail("Company Không tồn tại");
            }
            customer.setCompany(company);
        }
        return BaseResponse.success(customerRepository.save(customer));
    }

    @Override
    public BaseResponse update(CustomerUpdateReq req) {
        Customer customer = customerRepository.findAllByIdAndIsActive(req.getId(), 1);
        if (customer == null){
            return BaseResponse.fail("Customer Không tồn tại");
        }
        if (req.getCompanyId() != null){
            Company company = companyRepository.findAllByIdAndIsActive(req.getCompanyId(), 1);
            if (company == null) {
                return BaseResponse.fail("Company Không tồn tại");
            }
            customer.setCompany(company);
        }
        customer.setDisplayName(customer.getName());
        return BaseResponse.success(customerRepository.save(MapperUtil.mapValue(req, customer)));
    }

    public Customer zaloUserDetailToCustomer(ZaloUserDetailRes zaloUserDetailRes){
        ZaloUserData data = zaloUserDetailRes.getData();
        Company company = companyRepository.findAllByIdAndIsActive(1L, 1);
        return Customer
                .builder()
                .userId(data.getUserId())
                .userIdByApp(data.getUserIdByApp())
                .userExternalId(data.getUserExternalId())
                .displayName(data.getDisplayName())
                .userAlias(data.getUserAlias())
                .isSensitice(data.isSensitive())
                .userLastInteractionDate(DateUtil.stringToLocalDate(data.getUserLastInteractionDate()))
                .userIsFollower(data.isUserIsFollower())
                .avatar(data.getAvatar())
                .avatar120(data.getAvatars().getAvatar120())
                .avatar240(data.getAvatars().getAvatar240())
                .dynamicParam(data.getDynamicParam())
                .notes(data.getTagsAndNotesInfo().getNotes().toString())
                .tagNames(data.getTagsAndNotesInfo().getTagNames().toString())
                .address(data.getSharedInfo().getAddress())
                .city(data.getSharedInfo().getCity())
                .district(data.getSharedInfo().getDistrict())
                .phone(data.getSharedInfo().getPhone())
                .name(data.getDisplayName())
                .userDob(data.getSharedInfo().getUserDob())
                .status(1)
                .company(company)
                .build();
    }

}
