package com.ms.wms.domain.Routine.controller.dto;

import lombok.Getter;

@Getter
public class SaveRoutineExerciseDto {

    private Long exerciseId;
    private Integer count;
    private Integer weight;
    private Integer exerciseSet;
}
