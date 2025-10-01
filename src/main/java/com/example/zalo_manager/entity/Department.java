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

    @Column(nullable = false, columnDefinition = "TEXT")
    private String longtitude;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String latitude;
}
