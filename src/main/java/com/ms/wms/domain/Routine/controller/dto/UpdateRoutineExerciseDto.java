package com.ms.wms.domain.Routine.controller.dto;

import lombok.Getter;

@Getter
public class UpdateRoutineExerciseDto {

    private Long exerciseId;
    private String exerciseName;
    private Integer exerciseSet;
    private Double weight;
    private Integer count;
}
