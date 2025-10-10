package com.example.zalo_manager.model.response;

import com.example.zalo_manager.model.dto.WorkInfoDto;
import com.example.zalo_manager.model.dto.WorkInfoUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkInforRes {
    WorkInfoDto work;
    WorkInfoUserDto userDto;
}
