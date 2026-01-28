package com.restaurant.matjip.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ErrorResponse error;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> fail(String code, String message) {
        return new ApiResponse<>(false, null, new ErrorResponse(code, message, null));
    }

    public static <T> ApiResponse<T> fail(String code, String message, Object fields) {
        return new ApiResponse<>(false, null, new ErrorResponse(code, message, fields));
    }
}
