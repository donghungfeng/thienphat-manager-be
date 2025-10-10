package com.example.zalo_manager.model.dto.zalo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZaloMessagePromotionRequest {
    private Recipient recipient;
    private Object message; // để linh hoạt cho String hoặc Object JSON

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Recipient {
        private String user_id;
    }
}

