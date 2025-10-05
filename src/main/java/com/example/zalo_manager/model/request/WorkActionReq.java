package com.example.zalo_manager.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class WorkActionReq {
    @NotBlank(message = "username không được để trống")
    String username;
    @NotNull(message = "date không được để trống")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate date;
    @NotNull(message = "time không được để trống")
    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime time;
    Integer minCheckinLate;
    Integer minCheckoutEarly;
    @NotNull(message = "status không được để trống")
    @Min(value = 0, message = "status chỉ được phép từ 0 đến 2")
    @Max(value = 2, message = "status chỉ được phép từ 0 đến 2")
    private Integer status;
    private String ip;
    private String device;
    private String locationCheckin;
    private String locationCheckout;
    private Integer locationCheckinDis;
    private Integer locationCheckoutDis;
    private String checkinImage;
    private String checkouImage;
    private String note;

}
