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
@Table(name = "customer")
@Builder
public class Customer extends BaseEntity{
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_id_by_app")
    private String userIdByApp;

    @Column(name = "user_external_id")
    private String userExternalId;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "user_alias")
    private String userAlias;

    @Column(name = "is_sensitice")
    private Boolean isSensitice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "user_last_interaction_date")
    private LocalDate userLastInteractionDate;

    @Column(name = "user_is_follower")
    private Boolean userIsFollower;

    @Column(name = "avatar", columnDefinition = "TEXT")
    private String avatar;

    @Column(name = "avatar_240", columnDefinition = "TEXT")
    private String avatar240;

    @Column(name = "avatar_120", columnDefinition = "TEXT")
    private String avatar120;

    @Column(name = "dynamic_param")
    private String dynamicParam;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "tag_names", columnDefinition = "TEXT")
    private String tagNames;

    @Column(name = "address", length = 256)
    private String address;

    @Column(name = "city", length = 256)
    private String city;

    @Column(name = "district", length = 256)
    private String district;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "user_dob")
    private String userDob;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
