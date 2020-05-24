package com.ms.wms.domain.exercise.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class UpdateExerciseDto {

    @NotNull
    private Long exerciseId;

    @NotBlank(message = "운동명은 Null 또는 빈값일 수 없습니다.")
    private String name;

    @NotBlank(message = "운동명은 Null값일 수 없습니다.")
    private Category category;
}
