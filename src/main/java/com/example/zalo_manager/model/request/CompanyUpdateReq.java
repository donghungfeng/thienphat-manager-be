package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CompanyUpdateReq {
    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải là số dương")
    private Long id;
    private String name;
    private String taxCode;
    private String address;
    @Pattern(regexp = "^0[0-9]*$", message = "Số điện thoại phải bắt đầu bằng 0 và chỉ chứa chữ số")
    private String phone;
    private String note;
    private Integer status;
}
