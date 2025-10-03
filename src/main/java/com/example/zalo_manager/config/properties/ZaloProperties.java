package com.example.zalo_manager.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "zalo")
@Getter
@Setter
public class ZaloProperties {
    private String accessToken;
    private String refreshToken;
    private String appId;
    private String secretKey;
}

