package com.example.zalo_manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "report")
@Builder
public class Report extends BaseEntity {
    String code;
    String name;
    Integer type = 0;

    @Column(length = 1000)
    String note;

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    @JsonFormat(pattern = "HH:mm:ss")   // ✅ đảm bảo format JSON
    private LocalDateTime createTime;
}
