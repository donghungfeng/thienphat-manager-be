package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Company;
import com.example.zalo_manager.entity.Customer;
import com.example.zalo_manager.model.request.CustomerCreateReq;
import com.example.zalo_manager.model.request.CustomerUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.CompanyRepository;
import com.example.zalo_manager.repository.CustomerRepository;
import com.example.zalo_manager.service.CustomerService;
import com.example.zalo_manager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CompanyRepository companyRepository;

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
        return BaseResponse.success(customerRepository.save(MapperUtil.mapValue(req, customer)));
    }
}
