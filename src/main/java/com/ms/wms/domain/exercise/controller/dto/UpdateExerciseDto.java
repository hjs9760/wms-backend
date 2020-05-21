package com.ms.wms.domain.exercise.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class UpdateExerciseDto {

    private Long exerciseId;
    @NotEmpty(message = "운동명은 Null 또는 빈값일 수 없습니다.")
    private String name;
    @NotNull(message = "운동명은 Null값일 수 없습니다.")
    private Category category;
}
