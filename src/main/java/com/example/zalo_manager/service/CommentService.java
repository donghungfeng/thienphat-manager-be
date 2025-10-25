package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.Comment;
import com.example.zalo_manager.model.request.CommentCreateReq;
import com.example.zalo_manager.model.response.BaseResponse;

public interface CommentService extends BaseService<Comment> {
    BaseResponse create(CommentCreateReq req);
    BaseResponse deleteCustom(Long id);
}
