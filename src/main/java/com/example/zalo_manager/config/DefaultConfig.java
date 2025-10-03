package com.example.zalo_manager.config;

import com.example.zalo_manager.config.properties.ZaloProperties;
import com.example.zalo_manager.entity.Config;
import com.example.zalo_manager.entity.Department;
import com.example.zalo_manager.entity.User;
import com.example.zalo_manager.repository.ConfigRepository;
import com.example.zalo_manager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DefaultConfig {

    @Bean
    CommandLineRunner init(UserRepository userRepository, ConfigRepository configRepository, BCryptPasswordEncoder passwordEncoder, ZaloProperties zaloProperties) {
        return args -> {

            if (userRepository.findAllByUsername("hnhy01").isEmpty()) {
                Department department = Department
                        .builder()
                        .name("DHF")
                        .longtitude("HN")
                        .latitude("HN")
                        .build();
                User admin = new User();
                admin.setUsername("hnhy01");
                admin.setFullName("NamKimBum");
                admin.setPassword(passwordEncoder.encode("123456")); // mã hoá trước khi lưu
                admin.setIsActive(1);
                admin.setStatus(1);
                admin.setRole("admin");
                admin.setDepartment(department);
                userRepository.save(admin);
            }
            List<Config> configs = new ArrayList<>();
            if (configRepository.findByKey("access_token").isEmpty()) {
                Config config = Config
                        .builder()
                        .key("access_token")
                        .value(zaloProperties.getAccessToken())
                        .build();
                configs.add(config);
            }
            if (configRepository.findByKey("refresh_token").isEmpty()) {
                Config config = Config
                        .builder()
                        .key("refresh_token")
                        .value(zaloProperties.getRefreshToken())
                        .build();
                configs.add(config);
            }
            configRepository.saveAll(configs);
        };
    }
}
