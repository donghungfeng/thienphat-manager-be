package com.example.zalo_manager.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerCreateReq {
    private String userId;
    private String userIdByApp;
    private String userExternalId;
    private String displayName;
    private String userAlias;
    private Boolean isSensitice;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate userLastInteractionDate;
    private Boolean userIsFollower;
    private String avatar;
    private String avatar240;
    private String avatar120;
    private String dynamicParam;
    private String notes;
    private String tagNames;
    private String address;
    private String city;
    private String district;
    private String phone;
    private String name;
    private String userDob;
    private Integer status;
    private Long companyId;
}
