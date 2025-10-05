package com.example.zalo_manager.model.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZaloFollowEventWebhook {
    @JsonProperty("oa_id")
    private String oaId;

    private Follower follower;

    @JsonProperty("user_id_by_app")
    private String userIdByApp;

    @JsonProperty("event_name")
    private String eventName;

    private String source;

    @JsonProperty("app_id")
    private String appId;

    private String timestamp;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Follower {
        private String id;
    }
}
