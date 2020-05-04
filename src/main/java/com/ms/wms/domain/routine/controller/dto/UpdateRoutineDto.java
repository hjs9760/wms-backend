package com.ms.wms.domain.routine.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateRoutineDto {

    private Long routineId;
    private String name;
    private List<UpdateRoutineExerciseDto> updateRoutineExerciseDtoList;
}
