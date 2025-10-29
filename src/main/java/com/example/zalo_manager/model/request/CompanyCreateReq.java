package com.example.zalo_manager.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyCreateReq {
    private String code;
    private String name;
    private String shortName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate establishedDate;
    private String representativeName;
    private String director;
    private String chiefAccountantName;
    private String taxCode;
    private String taxDepartmentName;
    private String address;
    @Pattern(regexp = "^0[0-9]*$", message = "Số điện thoại phải bắt đầu bằng 0 và chỉ chứa chữ số")
    private String phone;
    private String email;
    private String businessField;
    private String note;
    private Integer status;
    private String hasArising;
    private Long accountantId; // Kế toán viên
    private Long supporterId; // Hỗ trợ
    private Long generalAccountantId; // Kế toán tổng hợp
    private Long collaboratorId; // Cộng tác viên
    private String invoiceLink;
    private String invoiceUsername;
    private String invoicePassword;
    private Integer invoicePurchasedCount;
    private Integer invoiceUsedCount;
    private Integer invoiceRemainingCount;
    private Integer invoiceStatus;
    private String electronicTaxPassword;
    private String electronicOrderPassword;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate digitalSignatureExpiryDate;
    private Integer remainingDays;
    private String totalZaloLink;
    private String insuranceLink;
    private String insuranceUsername;
    private String insurancePassword;
    private String bankInfo1;
    private String bankInfo2;
    private String bankInfo3;
    private String bankInfo4;
    private String bankInfo5;
}
