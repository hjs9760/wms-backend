package com.ms.wms.domain.routine.controller.dto;

import lombok.Getter;

/**
 * Created by momentjin@gmail.com on 2020-05-10
 * Github : http://github.com/momentjin
 */

@Getter
public class ExerciseDetailInfoDto {

    private Integer exerciseSet;
    private Integer count;
    private Double weight;

    public ExerciseDetailInfoDto(Integer exerciseSet, Integer count, Double weight) {
        this.exerciseSet = exerciseSet;
        this.count = count;
        this.weight = weight;
    }
}
