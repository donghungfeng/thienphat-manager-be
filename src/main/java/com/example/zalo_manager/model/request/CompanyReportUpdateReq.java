package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyReportUpdateReq {
    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải là số dương")
    private Long id;
    private Long companyId;
    private Long reportId;
    private Long accountantId; // Kế toán viên
    private Long supporterId; // Hỗ trợ
    private Long generalAccountantId; // Kế toán tổng hợp
    private int status;
    private String note;
}
