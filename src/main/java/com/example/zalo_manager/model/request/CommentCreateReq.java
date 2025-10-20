package com.example.zalo_manager.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CommentCreateReq {
    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải là số dương")
    private Long issueId;
    private Integer status = 0;
    @NotBlank(message = "Text không được để trống")
    private String text;
}
