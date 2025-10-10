package com.example.zalo_manager.service;

import com.example.zalo_manager.model.dto.zalo.user.detail.ZaloUserDetailRes;
import com.example.zalo_manager.model.response.BaseResponse;

public interface ZaloService{
    BaseResponse sendMessage(String temp, String userId) throws Exception;
    BaseResponse sendMessageTransactionOrder(String temp, String userId) throws Exception;
    BaseResponse sendMessagePromotion(String temp, String userId) throws Exception;
    ZaloUserDetailRes getUserDetail(String userId);
}
