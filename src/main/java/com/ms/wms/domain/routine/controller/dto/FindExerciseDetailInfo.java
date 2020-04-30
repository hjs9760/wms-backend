package com.ms.wms.domain.routine.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

@Getter
public class FindExerciseDetailInfo {

    private Long exerciseId;
    private String name;
    private Category category;
    private Integer count;
    private Double weight;
    private Integer exerciseSet;

    public static FindExerciseDetailInfo createFindExerciseDetailInfo(Long exerciseId, String name, Category category, int count, Double weight, int exerciseSet) {
        FindExerciseDetailInfo findExerciseDetailInfo = new FindExerciseDetailInfo();
        findExerciseDetailInfo.exerciseId = exerciseId;
        findExerciseDetailInfo.name = name;
        findExerciseDetailInfo.category = category;
        findExerciseDetailInfo.count = count;
        findExerciseDetailInfo.weight = weight;
        findExerciseDetailInfo.exerciseSet = exerciseSet;

        return findExerciseDetailInfo;
    }
}
