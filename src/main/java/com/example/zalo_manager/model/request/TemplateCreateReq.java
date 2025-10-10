package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateCreateReq {
    @NotBlank(message = "Value không được để trống")
    private String value;
    private String name;
    private Integer type;
    private String title;
    private Integer status;
    private String note;
    private String description;
}
