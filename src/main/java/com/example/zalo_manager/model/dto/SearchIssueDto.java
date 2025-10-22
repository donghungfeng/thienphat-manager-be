package com.example.zalo_manager.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchIssueDto {
    private Long id;
    private String code;
    private OtherUserDto create;
    private OtherUserDto assign;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate resolveDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dueDate;
    private Integer estimate;
    private String title;
    private String description;
    private String url;
    private Integer type;
    private Integer priority;
    private Integer status;
    private String note;
    private Long commentCount;
}
