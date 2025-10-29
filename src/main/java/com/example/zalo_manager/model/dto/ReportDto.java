package com.example.zalo_manager.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDto {
    Long id;
    String code;
    String name;
    Integer type = 0;
    String note;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdDate;
}
