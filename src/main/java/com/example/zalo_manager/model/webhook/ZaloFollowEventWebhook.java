package com.example.zalo_manager.model.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZaloFollowEventWebhook extends ZaloEvent{
    @JsonProperty("oa_id")
    private String oaId;

    private Follower follower;

    private String source;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Follower {
        private String id;
    }
}
