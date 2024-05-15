package com.example.indentity_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXITED(400, "User existed", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(501, "Unauthenticated", HttpStatus.UNAUTHORIZED),

    USER_NOT_EXITED(401, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(403, "You do not have permission", HttpStatus.FORBIDDEN);


    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
