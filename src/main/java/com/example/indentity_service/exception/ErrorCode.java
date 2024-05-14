package com.example.indentity_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXITED(400, "User existed"),
    UNAUTHENTICATED(500, "Unauthenticated"),

    USER_NOT_EXITED(400, "User not existed");


    private int code;
    private String message;
}
