package com.ms.wms.global.config.exception.business;

import com.ms.wms.global.config.exception.ErrorCode;
import lombok.Getter;

public class BusinessException extends RuntimeException {

    @Getter
    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
