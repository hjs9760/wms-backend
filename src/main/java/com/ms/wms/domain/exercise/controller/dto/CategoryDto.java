package com.ms.wms.domain.exercise.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

/**
 * Created by momentjin@gmail.com on 2020-07-05
 * Github : http://github.com/momentjin
 */
@Getter
public class CategoryDto {

    private String key;
    private String value;

    public CategoryDto(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public CategoryDto(Category category) {
        this.key = category.name();
        this.value = category.getName();
    }
}
