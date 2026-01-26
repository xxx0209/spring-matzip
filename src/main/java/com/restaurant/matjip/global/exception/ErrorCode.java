package com.restaurant.matjip.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND("U001", "사용자를 찾을 수 없습니다."),
    INVALID_CREDENTIAL("A001", "아이디 또는 비밀번호가 올바르지 않습니다."),
    ACCESS_DENIED("A002", "접근 권한이 없습니다."),
    INTERNAL_ERROR("S001", "서버 오류가 발생했습니다.");

    private final String code;
    private final String message;
}

