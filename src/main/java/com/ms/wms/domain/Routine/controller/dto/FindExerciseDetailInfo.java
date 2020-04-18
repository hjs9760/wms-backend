package com.ms.wms.domain.Routine.controller.dto;

import lombok.Getter;

@Getter
public class FindExerciseDetailInfo {

    private Long exerciseId;
    private String name;
    private Integer count;
    private Integer weight;
    private Integer exerciseSet;

    public static FindExerciseDetailInfo createFindExerciseDetailInfo(Long exerciseId, String name, int count, int weight, int exerciseSet) {
        FindExerciseDetailInfo findExerciseDetailInfo = new FindExerciseDetailInfo();
        findExerciseDetailInfo.exerciseId = exerciseId;;
        findExerciseDetailInfo.name = name;
        findExerciseDetailInfo.count = count;
        findExerciseDetailInfo.weight = weight;
        findExerciseDetailInfo.exerciseSet = exerciseSet;

        return findExerciseDetailInfo;
    }
}
