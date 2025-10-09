package com.example.zalo_manager.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueUpdateReq {
    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải là số dương")
    private Long id;
    private String code;
    private Long createId;
    private Long assignId;
    @JsonFormat(pattern = "dd/MM/yyyy")   // ✅ đảm bảo format JSON
    private LocalDate resolveDate;
    @JsonFormat(pattern = "dd/MM/yyyy")   // ✅ đảm bảo format JSON
    private LocalDate dueDate;
    private Integer estimate;
    private String title;
    private String description;
    private String url;
    private Integer type;
    private Integer priority;
    private Integer status;
    private String note;
}
