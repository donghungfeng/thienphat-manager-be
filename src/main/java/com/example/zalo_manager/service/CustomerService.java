package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.Customer;
import com.example.zalo_manager.model.response.BaseResponse;

public interface CustomerService extends BaseService<Customer> {
    BaseResponse mapCustomer();
}
