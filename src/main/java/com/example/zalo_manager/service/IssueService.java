package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.Issue;
import com.example.zalo_manager.model.request.IssueCreateReq;
import com.example.zalo_manager.model.request.IssueUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;

public interface IssueService extends BaseService<Issue> {
    BaseResponse create(IssueCreateReq req);
    BaseResponse update(IssueUpdateReq req);
}
