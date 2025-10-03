package com.example.zalo_manager.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleJobCreateReq {
    @NotNull(message = "template không được để trống")
    private Long templateId;

    @NotNull(message = "customer không được để trống")
    private Long customerId;

    @NotNull(message = "time không được để trống")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime time;
}
