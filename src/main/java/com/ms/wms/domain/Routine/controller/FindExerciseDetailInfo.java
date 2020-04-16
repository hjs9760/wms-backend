package com.ms.wms.domain.Routine.controller;

import lombok.Getter;

@Getter
public class FindExerciseDetailInfo {

    private Long exerciseId;
    private String name;
    private int count;
    private int weight;
    private int exerciseSet;

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
