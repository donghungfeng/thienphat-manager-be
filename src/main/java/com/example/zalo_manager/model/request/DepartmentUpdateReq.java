package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DepartmentUpdateReq {
    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải là số dương")
    private Long id;
    private String name;
    @NotNull(message = "Id công ty không đượd để trống")
    @Positive(message = "Id công ty phải là số dương")
    private Long companyId;
}
