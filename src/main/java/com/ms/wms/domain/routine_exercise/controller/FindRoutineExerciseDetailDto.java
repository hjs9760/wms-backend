package com.ms.wms.domain.routine_exercise.controller;

import com.ms.wms.domain.exercise.controller.FindExerciseDetailDto;
import lombok.Getter;

import java.util.List;

@Getter
public class FindRoutineExerciseDetailDto {

    // todo : 코드 일관성이 너무 없음. 아까 다른 코드와 같은 규칙이라면 findExerciseDetailDtoList라고 했어야 맞음
    // todo : 마찬가지로 dto라는 postfix 붙이기 말기. -> 사용하지 않는 dto라 master에서 삭제햇음
    private List<FindExerciseDetailDto> exerciseDetailList;
}
