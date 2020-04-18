package com.ms.wms.domain.Routine.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateRoutineDto {

    private Long routineId;
    private String name;
    private List<SaveRoutineExerciseDto> saveRoutineExerciseDtoList;

}
