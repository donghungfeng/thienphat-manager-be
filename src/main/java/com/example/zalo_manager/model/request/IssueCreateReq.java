package com.example.zalo_manager.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueCreateReq {
    private String code;
    private Long assignId;
    @JsonFormat(pattern = "dd/MM/yyyy")   // ✅ đảm bảo format JSON
    private LocalDate resolveDate;
    @JsonFormat(pattern = "dd/MM/yyyy")   // ✅ đảm bảo format JSON
    private LocalDate dueDate;
    private Float estimate;
    private String title;
    private String description;
    private String url;
    private Integer type;
    private Integer priority;
    private Integer status;
    private String note;
}
