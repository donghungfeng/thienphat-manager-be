package com.example.zalo_manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "company")
@Builder
public class Company extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "tax_code", nullable = false, unique = true)
    private String taxCode;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(columnDefinition = "LONGTEXT")
    private String note;

    @Column()
    private Integer status;
}
