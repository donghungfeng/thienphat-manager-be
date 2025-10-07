package com.example.zalo_manager.model.dto;

import com.example.zalo_manager.entity.Department;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
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
    private Department department;
    private String identityCardNumber;
}