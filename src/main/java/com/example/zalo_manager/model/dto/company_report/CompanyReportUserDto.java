package com.example.zalo_manager.model.dto.company_report;

import com.example.zalo_manager.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyReportUserDto {
    private Long id;
    private String username;
    private String role;
    private String fullName;
    private String email;
    private String phone;
    private String address;
}
