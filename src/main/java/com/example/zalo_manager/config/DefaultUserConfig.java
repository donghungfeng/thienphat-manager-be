package com.example.zalo_manager.config;

import com.example.zalo_manager.entity.User;
import com.example.zalo_manager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DefaultUserConfig {

    @Bean
    CommandLineRunner init(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findAllByUsername("hnhy01").isEmpty()) {
                User admin = new User();
                admin.setUsername("hnhy01");
                admin.setFullName("NamKimBum");
                admin.setPassword(passwordEncoder.encode("123456")); // mã hoá trước khi lưu
                admin.setIsActive(1);
                admin.setStatus(1);
                admin.setRole("admin");
                userRepository.save(admin);
            }
        };
    }
}
