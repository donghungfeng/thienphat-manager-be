package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.User;
import com.example.zalo_manager.entity.Work;
import com.example.zalo_manager.model.dto.UserDto;
import com.example.zalo_manager.model.request.WorkActionReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.model.response.WorkInforRes;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.UserRepository;
import com.example.zalo_manager.repository.WorkRepository;
import com.example.zalo_manager.service.WorkService;
import com.example.zalo_manager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WorkServiceImpl  extends BaseServiceImpl<Work> implements WorkService {
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    protected BaseRepository<Work> getRepository() {
        return workRepository;
    }


    @Override
    public BaseResponse infor(String username, LocalDate date) {
        User user = userRepository.findAllByUsername(username).orElse(null);
        if (user == null) {
            return BaseResponse.fail(username, HttpStatus.INTERNAL_SERVER_ERROR.value(), "username không tồn tại");
        }
        Work work = workRepository.findByDateAndUsername(date, username);
        return BaseResponse.success(WorkInforRes
                .builder()
                .department(user.getDepartment())
                .work(work)
                .userDto(MapperUtil.map(user, UserDto.class))
                .build());
    }

    @Override
    public BaseResponse action(WorkActionReq req) {
        User user = userRepository.findAllByUsername(req.getUsername()).orElse(null);
        if (user == null) {
            return BaseResponse.fail(req.getUsername(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "username không tồn tại");
        }
        Work work = workRepository.findByDateAndUsername(req.getDate(), req.getUsername());
        if (work == null){
            work = new Work();
            work = MapperUtil.mapValue(req, work);
            work.setTimeCheckin(req.getTime());
        }else {
            work = MapperUtil.mapValue(req, work);
            work.setTimeCheckout(req.getTime());
        }
        return BaseResponse.success(workRepository.save(work));
    }

    @Override
    public BaseResponse update(Work req) {
        Work work = workRepository.findById(req.getId()).orElse(null);
        if (work == null) {
            return BaseResponse.fail(req.getId(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "work không tồn tại");
        }
        return BaseResponse.success(workRepository.save(MapperUtil.mapValue(req, work)));
    }

}
