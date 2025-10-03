package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Customer;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.CustomerRepository;
import com.example.zalo_manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    protected BaseRepository<Customer> getRepository() {
        return customerRepository;
    }

    @Override
    public BaseResponse mapCustomer() {
        return null;
    }
}
