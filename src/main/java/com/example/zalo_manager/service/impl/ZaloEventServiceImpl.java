package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.config.properties.ZaloProperties;
import com.example.zalo_manager.entity.Customer;
import com.example.zalo_manager.entity.ZaloFollowEvent;
import com.example.zalo_manager.model.dto.zalo.user.detail.ZaloUserData;
import com.example.zalo_manager.model.dto.zalo.user.detail.ZaloUserDetailRes;
import com.example.zalo_manager.model.webhook.ZaloFollowEventWebhook;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.CustomerRepository;
import com.example.zalo_manager.repository.ZaloFollowEventRepository;
import com.example.zalo_manager.service.ZaloEventService;
import com.example.zalo_manager.service.ZaloService;
import com.example.zalo_manager.util.DateUtil;
import com.example.zalo_manager.util.MapperUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ZaloEventServiceImpl extends BaseServiceImpl<ZaloFollowEvent> implements ZaloEventService {
    @Autowired
    ZaloFollowEventRepository zaloFollowEventRepository;

    @Autowired
    ZaloService zaloService;

    @Autowired
    ZaloProperties zaloProperties;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    protected BaseRepository<ZaloFollowEvent> getRepository() {
        return zaloFollowEventRepository;
    }

    @Override
    public void event(String rawBody) {
        JsonNode root = MapperUtil.toJsonNode(rawBody);
        String eventName = root.get("event_name").asText();
        switch (eventName){
            case "follow":
                ZaloFollowEventWebhook zaloFollowEventWebhook = MapperUtil.fromJson(rawBody, ZaloFollowEventWebhook.class);
                this.follow(zaloFollowEventWebhook);
                break;
        }
    }

    public void follow(ZaloFollowEventWebhook zaloFollowEventWebhook) {
        String userId = zaloFollowEventWebhook.getFollower().getId();
        ZaloUserDetailRes zaloUserDetailRes = zaloService.getUserDetail(userId);
        customerRepository.save(zaloUserDetailToCustomer(zaloUserDetailRes));
    }

    public Customer zaloUserDetailToCustomer(ZaloUserDetailRes zaloUserDetailRes){
        ZaloUserData data = zaloUserDetailRes.getData();
        return Customer
                .builder()
                .userId(data.getUserId())
                .userIdByApp(data.getUserIdByApp())
                .userExternalId(data.getUserExternalId())
                .displayName(data.getDisplayName())
                .userAlias(data.getUserAlias())
                .isSensitice(data.isSensitive())
                .userLastInteractionDate(DateUtil.stringToLocalDate(data.getUserLastInteractionDate()))
                .userIsFollower(data.isUserIsFollower())
                .avatar(data.getAvatar())
                .avatar120(data.getAvatars().getAvatar120())
                .avatar240(data.getAvatars().getAvatar240())
                .dynamicParam(data.getDynamicParam())
                .notes(data.getTagsAndNotesInfo().getNotes().toString())
                .tagNames(data.getTagsAndNotesInfo().getTagNames().toString())
                .address(data.getSharedInfo().getAddress())
                .city(data.getSharedInfo().getCity())
                .district(data.getSharedInfo().getDistrict())
                .phone(data.getSharedInfo().getPhone())
                .name(data.getSharedInfo().getName())
                .userDob(data.getSharedInfo().getUserDob())
                .status(1)
                .build();
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
