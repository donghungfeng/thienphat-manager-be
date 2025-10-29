package com.example.zalo_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "company_report")
@Builder
public class CompanyReport extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @ManyToOne
    @JoinColumn(name = "accountant_id")
    private User accountant; // Kế toán viên

    @ManyToOne
    @JoinColumn(name = "supporter_id")
    private User supporter; // Hỗ trợ

    @ManyToOne
    @JoinColumn(name = "general_accountant_id")
    private User generalAccountant; // Kế toán tổng hợp

    private int status = 0;

    @Column(length = 1000)
    private String note;
}
