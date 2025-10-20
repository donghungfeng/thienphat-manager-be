package com.example.zalo_manager.model.dto;


import com.example.zalo_manager.entity.Department;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtherUserDto {
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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate updatedDate;
}
