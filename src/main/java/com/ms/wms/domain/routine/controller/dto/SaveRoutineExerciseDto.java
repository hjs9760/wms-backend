package com.ms.wms.domain.routine.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SaveRoutineExerciseDto {

    @NotNull
    private Long exerciseId;
    @NotNull
    private Integer count;
    @NotNull
    private Double weight;
    @NotNull
    private Integer exerciseSet;
}
