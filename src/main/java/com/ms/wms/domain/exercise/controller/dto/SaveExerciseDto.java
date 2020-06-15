package com.ms.wms.domain.exercise.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class SaveExerciseDto {

    @NotBlank(message = "운동명은 Null 또는 빈값일 수 없습니다.")
    private String name;

    private Category category;
}
