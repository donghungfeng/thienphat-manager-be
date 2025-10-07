package com.example.zalo_manager.model.dto.zalo.user.detail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avatars {

    @JsonProperty("240")
    private String avatar240;

    @JsonProperty("120")
    private String avatar120;
}
