package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.model.dto.statistic.issue.StatisticQuantityIssueByDateDto;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zalo_manager.service.StatisticService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private IssueRepository issueRepository;

    @Override
    public BaseResponse countIssueByAssign(LocalDate startDate, LocalDate endDate) {
        return BaseResponse.success(issueRepository.countIssueByAssign(startDate, endDate));
    }

    @Override
    public BaseResponse countIssuesByResolveDate(LocalDate startDate, LocalDate endDate) {
        List<StatisticQuantityIssueByDateDto> data = issueRepository.countIssuesByResolveDate(startDate, endDate);

        Map<LocalDate, Long> countByDate = data.stream()
                .collect(Collectors.toMap(
                        StatisticQuantityIssueByDateDto::getDate,
                        StatisticQuantityIssueByDateDto::getCount
                ));

        List<StatisticQuantityIssueByDateDto> result = new ArrayList<>();
        LocalDate current = startDate;

        while (!current.isAfter(endDate)) {
            Long count = countByDate.getOrDefault(current, 0L);
            result.add(new StatisticQuantityIssueByDateDto(current, count));
            current = current.plusDays(1);
        }

        return BaseResponse.success(result);
    }

    @Override
    public BaseResponse sumEstimateByAssign(LocalDate startDate, LocalDate endDate) {
        return BaseResponse.success(issueRepository.sumEstimateByAssign(startDate, endDate));
    }
}
