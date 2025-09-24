package com.example.zalo_manager.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchReq {
    private String filter;

    private Integer page;

    private Integer size;

    private String sort;
}

