package com.ms.wms.domain.exercise.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

@Getter
public class UpdateExerciseDto {

    private Long exerciseId;
    private String name;
    private Category category;
}
