package com.example.zalo_manager.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportCreateReq {
    String code;
    String name;
    Integer type = 0;
    String note;
}
