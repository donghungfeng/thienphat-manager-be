package com.example.zalo_manager.model.dto.zalo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZaloMessageRequest {
    private Recipient recipient;
    private Object message;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Recipient {
        @JsonProperty("user_id")
        private String userId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Message {
        private String text;
    }
}

