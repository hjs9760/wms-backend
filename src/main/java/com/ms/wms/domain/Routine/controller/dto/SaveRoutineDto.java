package com.ms.wms.domain.Routine.controller.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SaveRoutineDto {

    private String name;
    private List<SaveRoutineExerciseDto> saveRoutineExerciseDtoList;

}
