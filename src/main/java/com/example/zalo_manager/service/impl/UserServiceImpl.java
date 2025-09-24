package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.config.jwt.CustomUserDetails;
import com.example.zalo_manager.config.jwt.JwtTokenProvider;
import com.example.zalo_manager.entity.User;
import com.example.zalo_manager.model.dto.UserDto;
import com.example.zalo_manager.model.request.ChangePasswordReq;
import com.example.zalo_manager.model.request.ChangeRoleReq;
import com.example.zalo_manager.model.request.LoginReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.model.response.LoginRes;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.UserRepository;
import com.example.zalo_manager.service.UserService;
import com.example.zalo_manager.util.DateUtil;
import com.example.zalo_manager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected BaseRepository<User> getRepository() {
        return userRepository;
    }


    @Override
    public BaseResponse login(LoginReq req) {
        // Xác thực username/password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        // Set authentication vào SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Lấy user từ CustomUserDetails
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        // Tạo JWT token
        String token = jwtTokenProvider.generateToken(user.getUsername());

        // Build response
        LoginRes result = new LoginRes();
        MapperUtil.mapValue(user, result);
        result.setToken(token);

        return new BaseResponse().success(result);
    }


    @Override
    public BaseResponse register(User user) {
        User result = new User();
        try {
            if (user.getUsername() == null) {
                return new BaseResponse().fail("Tài khoản không được để trống");
            }
            if (user.getPassword() == null) {
                return new BaseResponse().fail("Mật khẩu không được để trống");
            }

            if (userRepository.findAllByUsername(user.getUsername()).isPresent()) {
                return new BaseResponse().fail("Tài khoản đã tồn tại");
            }
            user.setCreateDate(DateUtil.getCurrenDateTime());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("user");
            user.setStatus(1);
            result = super.create(user);
            return new BaseResponse().success(MapperUtil.map(result, UserDto.class));
        }catch (Exception e){
            return new BaseResponse(500, "Có lỗi xảy ra khi tạo tài khoản", null);
        }
    }

    @Override
    public BaseResponse changeRole(ChangeRoleReq req) throws Exception {
        if (!req.getRole().equals("admin") && !req.getRole().equals("user")){
            return new BaseResponse().fail("Quyền chỉ có thể là admin hoặc user");
        }
        User user = this.getById(req.getId());
        if (user == null) {
            return new BaseResponse().fail("Tài khoản không tồn tại");
        }
        user.setRole(req.getRole());
        return new BaseResponse().success(MapperUtil.map(user, UserDto.class));
    }

    @Override
    public BaseResponse changePassword(ChangePasswordReq req) throws Exception {
        User user = this.getById(req.getUserId());
        if (user == null){
            return new BaseResponse().fail("Tài khoản không tồn tại!");
        }
        if (!passwordEncoder.matches(req.getOldPassword(), user.getPassword())){
            return new BaseResponse().fail("Mật khẩu cũ không khớp!");
        }
        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        userRepository.save(user);
        return new BaseResponse().success("Thay đổi mật khẩu thành công!");
    }

    @Override
    public BaseResponse lockUser(Long id) throws Exception {
        User user = this.getById(id);
        if (user == null){
            return new BaseResponse().fail("Tài khoản không tồn tại Hoặc đã bị khóa!");
        }
        user.setStatus(-1);
        userRepository.save(user);
        return new BaseResponse().success("Khóa tài khoản thành công!");
    }

    @Override
    public BaseResponse unlockUser(Long id) throws Exception {
        User user = this.getById(id);
        if (user == null || user.getIsActive() != 1){
            return new BaseResponse().fail("Tài khoản không tồn tại!");
        }
        user.setStatus(1);
        userRepository.save(user);
        return new BaseResponse().success("Mở Khóa tài khoản thành công!");
    }

    @Override
    public User update(User t) throws Exception {
        User entityMy = this.getRepository().findAllById(t.getId());
        MapperUtil.mapValue(t, entityMy);
        t.setUpdateDate(DateUtil.getCurrenDateTime());
//        entityMy.setPassword(passwordEncoder.encode(t.getPassword()));
        return getRepository().save(entityMy);
    }

    private boolean isValidPassword(String userPass, String reqPass) {
        return !StringUtils.isEmpty(reqPass) && passwordEncoder.matches(reqPass, userPass);
    }
}

