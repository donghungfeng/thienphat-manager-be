package com.example.zalo_manager.model.dto.statistic.issue;

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
public class StatisticQuantityIssueByDateDto {
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate date;
    Long count;
}
