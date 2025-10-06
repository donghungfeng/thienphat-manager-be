package com.example.zalo_manager.model.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class ZaloEvent {
    @JsonProperty("app_id")
    public String appId;

    @JsonProperty("user_id_by_app")
    public String userIdByApp;

    @JsonProperty("event_name")
    public String eventName;

    @JsonProperty("timestamp")
    public String timestamp;
}
