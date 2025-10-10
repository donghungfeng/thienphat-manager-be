package com.example.zalo_manager.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkInfoDto {
    private Long id;
    private String ip;
    private String device;
    private Integer status;
    private String note;
}
