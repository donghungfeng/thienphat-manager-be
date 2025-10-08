package com.example.zalo_manager.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class WorkUpdateReq {
    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải là số dương")
    private Long id;
    private String username;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime timeCheckin;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime timeCheckout;
    private Integer minCheckinLate;
    private Integer minCheckoutEarly;
    private Integer status;
    private String reason;
    private String ip;
    private String device;
    private String locationCheckin;
    private String locationCheckout;
    private Integer locationCheckinDis;
    private Integer locationCheckoutDis;
    private String checkinImage;
    private String checkoutImage;
    private String note;
    private Integer hourWorkingAct;
    private Integer hourWorkingRecord;
}
