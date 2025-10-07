package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.User;
import com.example.zalo_manager.model.request.*;
import com.example.zalo_manager.model.response.BaseResponse;

public interface UserService extends BaseService<User> {
    BaseResponse login(LoginReq req);
    BaseResponse register(UserCreateReq req);
    BaseResponse changeRole(ChangeRoleReq req) throws Exception;
    BaseResponse changePassword(ChangePasswordReq req) throws Exception;
    BaseResponse lockUser(Long id) throws Exception;
    BaseResponse unlockUser(Long id) throws Exception;
    BaseResponse update(UserUpdateReq req) throws Exception;
}