package com.example.zalo_manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "schedule")
@Builder
public class ScheduleJob extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "run_time")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime runTime;

    private Integer status; // 0 = PENDING, 1 = DONE
}
