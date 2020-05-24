package com.ms.wms.global.config.exception.business;

import com.ms.wms.global.config.exception.ErrorCode;

public class UnAuthorityException extends BusinessException {

    public UnAuthorityException(String message) {
        super(ErrorCode.MEMBER_ACCESS_DENIED, message);
    }
}
