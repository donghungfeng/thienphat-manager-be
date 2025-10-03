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
@Table(name = "config")
@Builder
public class Config extends BaseEntity {
    @Column(name = "config_key",length = 256, unique = true, nullable = false)
    private String key;
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String value;
    private String description;
}
