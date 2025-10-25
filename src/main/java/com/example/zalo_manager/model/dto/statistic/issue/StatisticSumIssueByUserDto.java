package com.example.zalo_manager.model.dto.statistic.issue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticSumIssueByUserDto {
    private Long id;
    private String username;
    private String role;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private int status;
    private Integer isActive;
    private String deviceName;
    private String deviceCode;
    private String note;
    private String identityCardNumber;
    private BigDecimal sum;
}
