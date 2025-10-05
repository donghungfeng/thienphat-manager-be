package com.example.zalo_manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "work")
@Builder
public class Work extends BaseEntity {
    private String username;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "time_checkin")
    private LocalTime timeCheckin;
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "time_checkout")
    private LocalTime timeCheckout;
    @Column(name = "min_checkin_late")
    private Integer minCheckinLate;
    @Column(name = "min_checkout_early")
    private Integer minCheckoutEarly;
    private Integer status;
    @Column(columnDefinition = "TEXT")
    private String reason;
    private String ip;
    private String device;
    @Column(name = "location_checkin", length = 1000)
    private String locationCheckin;
    @Column(name = "location_checkout", length = 1000)
    private String locationCheckout;
    @Column(name = "location_checkin_dis")
    private Integer locationCheckinDis;
    @Column(name = "location_checkout_dis")
    private Integer locationCheckoutDis;
    @Column(name = "checkin_image", columnDefinition = "LONGTEXT")
    private String checkinImage;
    @Column(name = "checkout_image", columnDefinition = "LONGTEXT")
    private String checkouImage;
    @Column(columnDefinition = "TEXT")
    private String note;
    @Column(name = "hour_working_act")
    private Integer hourWorkingAct;
    @Column(name = "hour_working_record")
    private Integer hourWorkingRecord;
}
