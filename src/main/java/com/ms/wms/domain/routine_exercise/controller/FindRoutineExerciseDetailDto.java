package com.ms.wms.domain.routine_exercise.controller;

import com.ms.wms.domain.exercise.controller.FindExerciseDetailDto;
import lombok.Getter;

import java.util.List;

@Getter
public class FindRoutineExerciseDetailDto {

    private List<FindExerciseDetailDto> exerciseDetailDto;
}
