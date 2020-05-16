package com.ms.wms.domain.routine.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by momentjin@gmail.com on 2020-05-10
 * Github : http://github.com/momentjin
 */
@Getter
public class ExerciseInfoDto {

    private Long id;
    private String name;
    private Category category;

    @Setter
    private List<ExerciseDetailInfoDto> exerciseDetailInfoList;

    public ExerciseInfoDto(Long id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }


}
