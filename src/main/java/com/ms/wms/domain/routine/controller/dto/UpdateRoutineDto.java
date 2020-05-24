package com.ms.wms.domain.routine.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class UpdateRoutineDto {

    @NotNull
    private Long routineId;
    @NotBlank
    private String name;
    @NotNull
    private List<UpdateRoutineExerciseDto> updateRoutineExerciseDtoList;
}
