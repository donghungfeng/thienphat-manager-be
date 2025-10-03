package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.config.properties.ZaloProperties;
import com.example.zalo_manager.entity.Config;
import com.example.zalo_manager.model.dto.ZaloMessageRequest;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.model.response.ZaloRefreshResponse;
import com.example.zalo_manager.repository.ConfigRepository;
import com.example.zalo_manager.service.ZaloService;
import com.example.zalo_manager.util.MapperUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ZaloServiceImpl implements ZaloService {
    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ZaloProperties zaloProperties;

    private static final String SEND_MSG_URL = "https://openapi.zalo.me/v3.0/oa/message/cs";
    private static final String REFRESH_URL = "https://oauth.zaloapp.com/v4/oa/access_token";

    @Override
    public BaseResponse sendMessage() throws Exception {
        String accessToken = configRepository.findByKey("access_token").get().getValue();

        HttpHeaders headers = new HttpHeaders();
        headers.set("access_token", accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ZaloMessageRequest request = new ZaloMessageRequest(
                new ZaloMessageRequest.Recipient("6520181963459154601"),
                new ZaloMessageRequest.Message("hello")
        );

        HttpEntity<ZaloMessageRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                SEND_MSG_URL,
                HttpMethod.POST,
                entity,
                String.class
        );

        JsonNode json = MapperUtil.toJsonNode(response.getBody());

        if (json.has("error") && json.get("error").asInt() == -216) {
            this.refreshToken();
            return sendMessage(); // gọi lại sau khi refresh
        }

        return BaseResponse.success(request);
    }


    private void refreshToken() throws Exception {
        String refreshToken = configRepository.findByKey("refresh_token").get().getValue();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("secret_key", zaloProperties.getSecretKey()); // đổi secret của bạn

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("refresh_token", refreshToken);
        formData.add("app_id", zaloProperties.getAppId());
        formData.add("grant_type", "refresh_token");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                REFRESH_URL,
                HttpMethod.POST,
                entity,
                String.class
        );

        ZaloRefreshResponse json = MapperUtil.fromJson(response.getBody(), ZaloRefreshResponse.class);

        if (json.getAccessToken() != null && json.getRefreshToken() != null) {
            // update DB
            Config accessCfg = configRepository.findByKey("access_token").get();
            accessCfg.setValue(json.getAccessToken());
            configRepository.save(accessCfg);

            Config refreshCfg = configRepository.findByKey("refresh_token").get();
            refreshCfg.setValue(json.getRefreshToken());
            configRepository.save(refreshCfg);
        } else {
            throw new RuntimeException("Refresh token failed: " + response.getBody());
        }
    }


}
