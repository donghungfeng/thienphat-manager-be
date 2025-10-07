package com.example.zalo_manager.model.response;

import com.example.zalo_manager.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRes {
    public String token;
    public String username;
    public String phone;
    public String email;
    public String address;
    public String id;
    public String role;
    public Department department;
    public String zaloAccessToken;
}
