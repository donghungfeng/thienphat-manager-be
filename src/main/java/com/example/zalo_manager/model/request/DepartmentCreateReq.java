package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentCreateReq {
    @NotBlank
    private String name;
    @NotBlank(message = "longtitude không được để trống")
    private String longtitude;
    @NotBlank(message = "latitude không được để trống")
    private String latitude;
    private String factoryName;
    private String ntoe;
    private Integer status;
    private String ip;
    private Long threshold;
}
