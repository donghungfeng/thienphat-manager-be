package com.example.zalo_manager.model.response;

import com.example.zalo_manager.entity.Department;
import com.example.zalo_manager.entity.Work;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkInforRes {
    Department department;
    Work work;
}
