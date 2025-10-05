package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.config.properties.ZaloProperties;
import com.example.zalo_manager.entity.ZaloFollowEvent;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.model.webhook.ZaloFollowEventWebhook;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.ZaloFollowEventRepository;
import com.example.zalo_manager.service.ZaloFollowEventService;
import com.example.zalo_manager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ZaloFollowerEventServiceImpl extends BaseServiceImpl<ZaloFollowEvent> implements ZaloFollowEventService {
    @Autowired
    ZaloFollowEventRepository zaloFollowEventRepository;

    @Autowired
    ZaloProperties zaloProperties;

    @Override
    protected BaseRepository<ZaloFollowEvent> getRepository() {
        return zaloFollowEventRepository;
    }

    @Override
    public BaseResponse create(String req, String signatureHeader) {
        // Parse JSON
        ZaloFollowEventWebhook event = MapperUtil.fromJson(req, ZaloFollowEventWebhook.class);
        ZaloFollowEvent zaloFollowEvent = MapperUtil.map(event, ZaloFollowEvent.class);
        zaloFollowEvent.setFollowerId(event.getFollower().getId());
        return BaseResponse.success(zaloFollowEventRepository.save(zaloFollowEvent));
    }

    private boolean verifySignature(String rawBody, String timestamp, String signatureHeader) {
        try {
            // Kiểm tra timestamp và signatureHeader
            if (timestamp == null || signatureHeader == null) {
                System.err.println("Timestamp or signature header is missing");
                return false;
            }

            // Tạo chuỗi rawData theo công thức
            String rawData = zaloProperties.getAppId() + rawBody + timestamp + zaloProperties.getSecretKey();

            // Tính SHA-256 hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(rawData.getBytes(StandardCharsets.UTF_8));

            // Chuyển sang hex string (lowercase)
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            String expectedSignature = "mac=" + hexString.toString();

            // So sánh với header
            System.out.println("Expected signature: " + expectedSignature);
            System.out.println("Received signature: " + signatureHeader);
            return signatureHeader.trim().equalsIgnoreCase(expectedSignature.trim());

        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 error: " + e.getMessage());
            return false;
        }
    }

}
