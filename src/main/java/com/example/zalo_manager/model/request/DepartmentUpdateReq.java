package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentUpdateReq {
    @NotBlank(message = "Id phòng ban không đượd để trống")
    private Long id;
    private String name;
    @NotBlank(message = "Id công ty không đượd để trống")
    private Long companyId;
}
