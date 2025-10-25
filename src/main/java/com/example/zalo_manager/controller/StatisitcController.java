package com.example.zalo_manager.controller;

import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin
@RestController
@RequestMapping("statistic")
public class StatisitcController {
    @Autowired
    StatisticService statisticService;

    @GetMapping("/issue/by-date")
    public BaseResponse getIssueByDate(
            @RequestParam("startDate") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate) {

        return statisticService.countIssuesByResolveDate(startDate, endDate);
    }

    @GetMapping("/issue/sum-by-user")
    public BaseResponse getSumIssueByUser(
            @RequestParam("startDate") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate) {

        return statisticService.sumEstimateByAssign(startDate, endDate);
    }

    @GetMapping("/issue/count-by-user")
    public BaseResponse getCountIssueByUser(
            @RequestParam("startDate") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate) {

        return statisticService.countIssueByAssign(startDate, endDate);
    }
}
