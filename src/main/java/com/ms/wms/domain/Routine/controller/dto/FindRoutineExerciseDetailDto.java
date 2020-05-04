package com.ms.wms.domain.routine.controller.dto;

import com.ms.wms.domain.exercise.controller.FindExerciseDetailDto;
import lombok.Getter;

import java.util.List;

@Getter
public class FindRoutineExerciseDetailDto {

    private List<FindExerciseDetailDto> exerciseDetailList;
}
