package com.ms.wms.domain.routine.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

@Getter
public class FindExerciseDetailInfoDto {

    private Long exerciseId;
    private String name;
    private Category category;
    private Integer count;
    private Double weight;
    private Integer exerciseSet;

    public static FindExerciseDetailInfoDto createFindExerciseDetailInfo(Long exerciseId, String name, Category category, int count, Double weight, int exerciseSet) {
        FindExerciseDetailInfoDto findExerciseDetailInfoDto = new FindExerciseDetailInfoDto();
        findExerciseDetailInfoDto.exerciseId = exerciseId;
        findExerciseDetailInfoDto.name = name;
        findExerciseDetailInfoDto.category = category;
        findExerciseDetailInfoDto.count = count;
        findExerciseDetailInfoDto.weight = weight;
        findExerciseDetailInfoDto.exerciseSet = exerciseSet;

        return findExerciseDetailInfoDto;
    }
}
