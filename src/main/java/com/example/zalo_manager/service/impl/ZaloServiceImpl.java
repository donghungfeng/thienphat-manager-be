package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.config.properties.ZaloProperties;
import com.example.zalo_manager.entity.Config;
import com.example.zalo_manager.model.dto.zalo.ZaloMessageRequest;
import com.example.zalo_manager.model.dto.zalo.user.detail.ZaloUserDetailRes;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.model.response.ZaloRefreshResponse;
import com.example.zalo_manager.repository.ConfigRepository;
import com.example.zalo_manager.service.ZaloService;
import com.example.zalo_manager.util.MapperUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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
    private static final String GET_INFO_URL = "https://openapi.zalo.me/v3.0/oa/user/detail";

    @Override
    public BaseResponse sendMessage(String temp, String userId) throws Exception {
        String accessToken = configRepository.findByKey("access_token").get().getValue();

        HttpHeaders headers = new HttpHeaders();
        headers.set("access_token", accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ZaloMessageRequest request = new ZaloMessageRequest(
                new ZaloMessageRequest.Recipient(userId),
                new ZaloMessageRequest.Message(temp)
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
            return sendMessage(temp, userId); // gọi lại sau khi refresh
        }

        return BaseResponse.success(request);
    }

    @Override
    public ZaloUserDetailRes getUserDetail(String userId) {
        String accessToken = configRepository.findByKey("access_token").get().getValue();

        HttpHeaders headers = new HttpHeaders();
        headers.set("access_token", accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            // JSON gốc
            String rawData = String.format("{\"user_id\":\"%s\"}", userId);

            // Encode toàn bộ JSON
            String encodedData = URLEncoder.encode(rawData, StandardCharsets.UTF_8);

            // Tạo URL giống hệt cURL của bạn (giữ nguyên {} bên ngoài)
            String url = "https://openapi.zalo.me/v3.0/oa/user/detail?data=" + encodedData;

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    URI.create(url), // dùng URI.create() để giữ nguyên encode
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            JsonNode json = MapperUtil.toJsonNode(response.getBody());
            if (json.has("error") && json.get("error").asInt() == -216) {
                refreshToken();
                return getUserDetail(userId);
            }

            return MapperUtil.fromJson(response.getBody(), ZaloUserDetailRes.class);

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi gọi API Zalo: " + e.getMessage(), e);
        }
    }




//    @PostConstruct
//    public void runOnStartup() throws Exception {
//        this.refreshToken();
//    }

    @Scheduled(cron = "0 0 0 * * ?") // Cron expression: giây=0, phút=0, giờ=0, ngày=*, tháng=*, ngày trong tuần=?
    public void runDailyAtMidnight() throws Exception {
        this.refreshToken();
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
