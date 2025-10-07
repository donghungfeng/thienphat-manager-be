package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DepartmentUpdateReq {
    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải là số dương")
    private Long id;
    private String name;
    private String longtitude;
    private String latitude;
    private String factoryName;
    private String ntoe;
    private Integer status;
    private String ip;
    private Long threshold;
}
