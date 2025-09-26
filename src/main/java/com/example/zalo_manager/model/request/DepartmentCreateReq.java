package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentCreateReq {
    @NotBlank
    private String name;

    @NotBlank
    private Long companyId;
}
