package com.ms.wms.domain.exercise.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

import java.util.List;

/**
 * Created by momentjin@gmail.com on 2020-07-05
 * Github : http://github.com/momentjin
 */
@Getter
public class ExerciseListByCategoryDto {

    private CategoryDto category;
    private List<FindExerciseDetailDto> exerciseDetailList;

    public ExerciseListByCategoryDto(Category category, List<FindExerciseDetailDto> findExerciseDetailDtos) {
        this.category = new CategoryDto(category);
        this.exerciseDetailList = findExerciseDetailDtos;
    }
}
