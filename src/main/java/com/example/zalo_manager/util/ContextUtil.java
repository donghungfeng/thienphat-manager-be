package com.example.zalo_manager.util;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Data
@Getter
public class ContextUtil {
    private static final ThreadLocal<String> USER = new ThreadLocal<>();

    public void setUserName(String username) {
        USER.set(username);
    }

    public String getUserName() {
        return USER.get();
    }

    public void clear() {
        USER.remove(); // cực kỳ quan trọng: xóa khi request kết thúc
    }
}

