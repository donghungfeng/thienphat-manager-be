package com.example.zalo_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
@Builder
public class User extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String role;

    @Column()
    private String email;

    @Column()
    private String phone;

    @Column()
    private String address;

    @Column()
    private int status = 1;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "device_code")
    private String deviceCode;

    private String note;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
