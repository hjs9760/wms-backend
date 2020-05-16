package com.ms.wms.domain.routine.controller.dto;

import lombok.Getter;

@Getter
public class UpdateRoutineExerciseDto {

    private Long exerciseId;
    private Integer exerciseSet;
    private Double weight;
    private Integer count;
}
