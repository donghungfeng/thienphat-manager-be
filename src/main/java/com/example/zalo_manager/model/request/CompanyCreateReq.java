package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CompanyCreateReq {
    @NotBlank(message = "Tên công ty không được để trống")
    private String name;

    @NotBlank(message = "MST công ty không được để trống")
    private String taxCode;

    @NotBlank(message = "Địa chỉ công ty không được để trống")
    private String address;

    @NotBlank(message = "SĐT công ty không được để trống")
    @Pattern(regexp = "^0[0-9]*$", message = "Số điện thoại phải bắt đầu bằng 0 và chỉ chứa chữ số")
    private String phone;

    private String note;

    private Integer status;
}
