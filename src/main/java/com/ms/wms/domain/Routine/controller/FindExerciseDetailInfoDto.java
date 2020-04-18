package com.ms.wms.domain.Routine.controller;

import com.ms.wms.domain.routine_exercise.RoutineExercise;
import lombok.Getter;

@Getter
public class FindExerciseDetailInfoDto {

    private Long exerciseId;
    private String name;
    private int count;
    private int weight;
    private int exerciseSet;

    public static FindExerciseDetailInfoDto createFindExerciseDetailInfo(Long exerciseId, String name, int count, int weight, int exerciseSet) {
        FindExerciseDetailInfoDto findExerciseDetailInfoDto = new FindExerciseDetailInfoDto();
        findExerciseDetailInfoDto.exerciseId = exerciseId;;
        findExerciseDetailInfoDto.name = name;
        findExerciseDetailInfoDto.count = count;
        findExerciseDetailInfoDto.weight = weight;
        findExerciseDetailInfoDto.exerciseSet = exerciseSet;

        return findExerciseDetailInfoDto;
    }
}
