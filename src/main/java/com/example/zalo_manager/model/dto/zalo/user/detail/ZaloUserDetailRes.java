package com.example.zalo_manager.model.dto.zalo.user.detail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZaloUserDetailRes {

    @JsonProperty("data")
    private ZaloUserData data;

    @JsonProperty("error")
    private int error;

    @JsonProperty("message")
    private String message;
}
