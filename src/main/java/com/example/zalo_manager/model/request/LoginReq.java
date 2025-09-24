package com.example.zalo_manager.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginReq {
    String username;
    String password;
}
