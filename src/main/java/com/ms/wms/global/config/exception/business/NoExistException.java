package com.ms.wms.global.config.exception.business;

import com.ms.wms.global.config.exception.ErrorCode;

public class NoExistException extends BusinessException {

    public NoExistException(String message) {
        super(ErrorCode.NOT_FOUND_ROW, message);
    }
}
