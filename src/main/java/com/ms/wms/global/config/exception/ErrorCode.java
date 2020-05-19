package com.ms.wms.global.config.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "Binding Error "),
    INTERNAL_SERVER_ERROR(500, "C002", "Internal Server Error"),
    NOT_FOUND_ROW(404, "C003"),
    Duplicate_ROW(409, "C004"),

    // Member
    MEMBER_ACCESS_DENIED(403, "C100");

    private int status;
    private String code;
    private String message;

    // default exception
    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
    }

    // custom exception
    ErrorCode(int status, String code) {
        this.status = status;
        this.code = code;
    }
}
