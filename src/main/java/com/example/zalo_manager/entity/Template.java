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
@Table(name = "template")
@Builder
public class Template extends BaseEntity{
    private String name;
    private Integer type;
    private String title;
    private Integer status;
    private String note;
    @Column(columnDefinition = "LONGTEXT")
    private String value;
    private String description;
}
