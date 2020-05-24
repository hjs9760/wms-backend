package com.ms.wms.domain.routine.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class UpdateRoutineExerciseDto {

    @NotNull
    private Long exerciseId;
    @NotBlank
    private String exerciseName;
    @NotNull
    private Integer exerciseSet;
    @NotNull
    private Double weight;
    @NotNull
    private Integer count;
}
