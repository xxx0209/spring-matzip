package com.restaurant.matjip.global.exception;

public class InvalidCredentialException extends BusinessException {

    public InvalidCredentialException() {
        super(ErrorCode.INVALID_CREDENTIAL);
    }
}

