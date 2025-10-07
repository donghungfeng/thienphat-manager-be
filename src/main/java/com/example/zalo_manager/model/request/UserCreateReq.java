package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateReq {

    @NotBlank(message = "username Không được để trống")
    private String username;

    @NotBlank(message = "password Không được để trống")
    private String password;

    @NotBlank(message = "fullName Không được để trống")
    private String fullName;

    private String email;

    private String phone;

    private String address;

    private String deviceName;

    private String deviceCode;

    private String note;
    private String identityCardNumber;

    @NotBlank(message = "departmentId Không được để trống")
    private Long departmentId;
}
