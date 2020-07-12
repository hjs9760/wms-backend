package com.ms.wms.domain.routine.controller.dto;

import lombok.Getter;

@Getter
public class RoutineExerciseDetailDto {

    private Integer count;
    private Double weight;
    private Integer exerciseSet;

    public static RoutineExerciseDetailDto createRoutineExerciseDto(int count, double weight, int exerciseSet) {
        RoutineExerciseDetailDto dto = new RoutineExerciseDetailDto();
        dto.count = count;
        dto.weight = weight;
        dto.exerciseSet = exerciseSet;

        return dto;
    }

}
