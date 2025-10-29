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
@Table(name = "company")
@Builder
public class Company extends BaseEntity{
    private String code;

    @Column(unique = true)
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "established_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate establishedDate;

    @Column(name = "representative_name")
    private String representativeName;

    private String director;

    @Column(name = "chief_accountant_name")
    private String chiefAccountantName;

    @Column(name = "tax_code", unique = true)
    private String taxCode;

    @Column(name = "tax_department_name", columnDefinition = "TEXT")
    private String taxDepartmentName;

    @Column(columnDefinition = "LONGTEXT")
    private String address;

    @Column(unique = true)
    private String phone;

    private String email;

    @Column(name = "business_field")
    private String businessField;

    @Column(columnDefinition = "LONGTEXT")
    private String note;

    @Column()
    private Integer status;

    @Column(name = "has_arising")
    private String hasArising;

    @ManyToOne
    @JoinColumn(name = "accountant_id")
    private User accountant; // Kế toán viên

    @ManyToOne
    @JoinColumn(name = "supporter_id")
    private User supporter; // Hỗ trợ

    @ManyToOne
    @JoinColumn(name = "general_accountant_id")
    private User generalAccountant; // Kế toán tổng hợp

    @ManyToOne
    @JoinColumn(name = "collaborator_id")
    private User collaborator; // Cộng tác viên

    @Column(name = "invoice_link", columnDefinition = "TEXT")
    private String invoiceLink;

    @Column(name = "invoice_username")
    private String invoiceUsername;

    @Column(name = "invoice_password")
    private String invoicePassword;

    @Column(name = "invoice_purchased_count")
    private Integer invoicePurchasedCount;

    @Column(name = "invoice_used_count")
    private Integer invoiceUsedCount;

    @Column(name = "invoice_remaining_count")
    private Integer invoiceRemainingCount;

    @Column(name = "invoice_status")
    private Integer invoiceStatus;

    @Column(name = "electronic_tax_password")
    private String electronicTaxPassword;

    @Column(name = "electronic_order_password")
    private String electronicOrderPassword;

    @Column(name = "digital_signature_expiry_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate digitalSignatureExpiryDate;

    @Column(name = "remaining_days")
    private Integer remainingDays;

    @Column(name = "total_zalo_link")
    private String totalZaloLink;

    @Column(name = "insurance_link", columnDefinition = "TEXT")
    private String insuranceLink;

    @Column(name = "insurance_username")
    private String insuranceUsername;

    @Column(name = "insurance_password")
    private String insurancePassword;

    @Column(name = "bank_info_1", columnDefinition = "TEXT")
    private String bankInfo1;

    @Column(name = "bank_info_2", columnDefinition = "TEXT")
    private String bankInfo2;

    @Column(name = "bank_info_3", columnDefinition = "TEXT")
    private String bankInfo3;

    @Column(name = "bank_info_4", columnDefinition = "TEXT")
    private String bankInfo4;

    @Column(name = "bank_info_5", columnDefinition = "TEXT")
    private String bankInfo5;
}
