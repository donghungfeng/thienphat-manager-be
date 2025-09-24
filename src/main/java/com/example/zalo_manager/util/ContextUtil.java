package com.example.zalo_manager.util;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Data
@Getter
public class ContextUtil {
    private String userName;
}

