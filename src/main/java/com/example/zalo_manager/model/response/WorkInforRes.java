package com.example.zalo_manager.model.response;

import com.example.zalo_manager.entity.Department;
import com.example.zalo_manager.entity.Work;
import com.example.zalo_manager.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkInforRes {
    Work work;
    UserDto userDto;
}
