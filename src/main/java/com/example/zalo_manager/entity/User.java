package com.example.zalo_manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    @Column()
    private String password;

    @Column()
    private String fullName;

    @Column()
    private String role;

    @Column()
    private String email;

    @Column()
    private String phone;

    @Column()
    private String address;

    @Column()
    private int status = 1;
}
