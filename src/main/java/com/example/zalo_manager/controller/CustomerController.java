package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.Customer;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("customer")
public class CustomerController extends BaseController<Customer, Customer>{
    @Autowired
    private CustomerService customerService;

    public CustomerController() {
        super(Customer.class);
    }

    @Override
    protected BaseService<Customer> getService() {
        return customerService;
    }
}
