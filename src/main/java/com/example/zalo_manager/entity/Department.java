package com.example.zalo_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "department")
@Builder
public class Department extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column(name = "factory_name")
    private String factoryName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String longtitude;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String latitude;

    private String note;

    private String ip;

    @Column(name = "thresho_id")
    private Long threshold;

    private Integer status;
}
