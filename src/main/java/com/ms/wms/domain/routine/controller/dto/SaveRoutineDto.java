package com.ms.wms.domain.routine.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class SaveRoutineDto {

    @NotBlank
    private String name;
    @NotNull
    private Long memberId;
    @NotNull
    private List<SaveRoutineExerciseDto> routineExerciseList;
}
