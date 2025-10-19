package com.example.zalo_manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "issue")
@Builder
public class Issue extends BaseEntity{
    private String code;

    @ManyToOne
    @JoinColumn(name = "create_id", nullable = false)
    private User create;

    @ManyToOne
    @JoinColumn(name = "assign_id", nullable = true)
    private User assign;

    @Column(name = "resolve_date")// ✅ đảm bảo format JSON
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate resolveDate;

    @Column(name = "due_date")// ✅ đảm bảo format JSON
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dueDate;

    private Integer estimate;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String url;

    private Integer type;

    private Integer priority;

    private Integer status;

    @Column(length = 500)
    private String note;
}
