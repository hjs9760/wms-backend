package com.ms.wms.domain.exercise.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

@Getter
public class SaveExerciseDto {

    private String name;
    private Category category;

}
