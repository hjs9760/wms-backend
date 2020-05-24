package com.ms.wms.global.config.exception.business;

import com.ms.wms.global.config.exception.ErrorCode;

public class DuplicateException extends BusinessException {

    public DuplicateException(Long memberId, String exerciseName) {
        super(ErrorCode.DUPLICATE_ROW, "member_id가 "+ memberId+ "가 등록할 '" + exerciseName +"' 는(은) 중복되는 운동명 입니다.");
    }
}
