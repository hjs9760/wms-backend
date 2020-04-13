package com.ms.wms.domain.Routine.controller;

import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.routine_exercise.RoutineExercise;
import lombok.Getter;

import java.util.List;

@Getter
public class FindRoutineDetailDto {

    private Long id;
    private String name;
    private List<Exercise> exerciseList;
    private List<RoutineExercise> routineExerciseList;

    public static FindRoutineDetailDto resultRoutinDto(Long id, String name, List<Exercise> exerciseList, List<RoutineExercise> routineExerciseList) {
        FindRoutineDetailDto findRoutineDetailDto = new FindRoutineDetailDto();
        findRoutineDetailDto.id = id;
        findRoutineDetailDto.name = name;
        findRoutineDetailDto.exerciseList = exerciseList;
        findRoutineDetailDto.routineExerciseList = routineExerciseList;

        return findRoutineDetailDto;
    }
}
