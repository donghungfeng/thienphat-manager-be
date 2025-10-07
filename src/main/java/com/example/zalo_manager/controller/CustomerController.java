package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.Customer;
import com.example.zalo_manager.model.request.CustomerCreateReq;
import com.example.zalo_manager.model.request.CustomerUpdateReq;
import com.example.zalo_manager.model.request.DepartmentCreateReq;
import com.example.zalo_manager.model.request.DepartmentUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("create")
    public BaseResponse create(@RequestBody @Valid CustomerCreateReq req){
        return customerService.create(req);
    }

    @PutMapping("update")
    public BaseResponse update(@RequestBody @Valid CustomerUpdateReq req){
        return customerService.update(req);
    }
}
