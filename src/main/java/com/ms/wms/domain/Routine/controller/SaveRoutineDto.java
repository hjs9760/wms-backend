package com.ms.wms.domain.Routine.controller;

import lombok.Getter;

import java.util.List;

@Getter
public class SaveRoutineDto {

    private String name;
    private Long memberId;
    private List<SaveRoutineExerciseDto> saveRoutineExerciseDtoList;


}
