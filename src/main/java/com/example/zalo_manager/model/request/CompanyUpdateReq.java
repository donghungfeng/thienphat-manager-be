package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CompanyUpdateReq {
    @NotBlank(message = "Id công ty không đượd để trống")
    private Long id;
    private String name;
    private String taxCode;
    private String address;
    @Pattern(regexp = "^0[0-9]*$", message = "Số điện thoại phải bắt đầu bằng 0 và chỉ chứa chữ số")
    private String phone;
}
