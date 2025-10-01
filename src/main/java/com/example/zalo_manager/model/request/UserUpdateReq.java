package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UserUpdateReq {
    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải là số dương")
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Integer status;
    private Long departmentId;
}
