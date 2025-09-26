package com.example.zalo_manager.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private int code;
    private String message;
    private Object result;

    public static BaseResponse success() {
        return success(null);
    }

    public static BaseResponse success(Object result) {
        return BaseResponse.builder()
                .code(200)
                .message("Thành công!")
                .result(result)
                .build();
    }

    public static BaseResponse success(Object result, int code, String message) {
        return BaseResponse.builder()
                .code(code)
                .message(message)
                .result(result)
                .build();
    }

    public static BaseResponse fail() {
        return fail(null);
    }

    public static BaseResponse fail(Object result) {
        return BaseResponse.builder()
                .code(500)
                .message("Thất bại!")
                .result(result)
                .build();
    }

    public static BaseResponse fail(Object result, int code, String message) {
        return BaseResponse.builder()
                .code(code)
                .message(message)
                .result(result)
                .build();
    }
}
