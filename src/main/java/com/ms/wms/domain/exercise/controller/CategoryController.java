package com.ms.wms.domain.exercise.controller;

import com.ms.wms.domain.exercise.controller.dto.CategoryDto;
import com.ms.wms.domain.exercise.domain.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping
    public List<CategoryDto> getCategories() {
        return Arrays.stream(Category.values())
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }
}
