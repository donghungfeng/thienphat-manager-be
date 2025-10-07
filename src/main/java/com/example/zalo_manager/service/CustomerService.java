package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.Customer;
import com.example.zalo_manager.model.request.CustomerCreateReq;
import com.example.zalo_manager.model.request.CustomerUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;

public interface CustomerService extends BaseService<Customer> {
    BaseResponse mapCustomer();
    BaseResponse create(CustomerCreateReq req);
    BaseResponse update(CustomerUpdateReq req);
}
