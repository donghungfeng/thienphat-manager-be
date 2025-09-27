package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DepartmentCreateReq {
    @NotBlank
    private String name;

    @NotNull
    @Positive(message = "Id công ty phải là số dương")
    private Long companyId;
}
