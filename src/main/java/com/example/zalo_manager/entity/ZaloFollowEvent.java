package com.example.zalo_manager.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "zalo_follow_event")
@Builder
public class ZaloFollowEvent extends BaseEntity{
    @Column(name = "oa_id", length = 256)
    private String oaId;

    @Column(name = "follower_id", length = 256)
    private String followerId;

    @Column(name = "user_id_by_app", length = 256)
    private String userIdByApp;

    @Column(name = "event_name", length = 256)
    private String eventName;

    @Column(name = "source", length = 256)
    private String source;

    @Column(name = "app_id", length = 256)
    private String appId;

    @Column(length = 256)
    private String timestamp;
}
