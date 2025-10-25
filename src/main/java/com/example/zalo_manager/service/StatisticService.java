package com.example.zalo_manager.service;

import com.example.zalo_manager.model.response.BaseResponse;

import java.time.LocalDate;

public interface StatisticService {
    BaseResponse countIssueByAssign(LocalDate startDate, LocalDate endDate);
    BaseResponse countIssuesByResolveDate(LocalDate startDate, LocalDate endDate);
    BaseResponse sumEstimateByAssign(LocalDate startDate, LocalDate endDate);

}
