package com.ms.wms.domain.exercise.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import com.ms.wms.domain.exercise.domain.Exercise;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FindExerciseDetailDto {

    private Long id;
    private String name;
    private Category category;

    public FindExerciseDetailDto(Long id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public static FindExerciseDetailDto convertFindExerciseDetailDto(Long id, String name, Category category) {
        FindExerciseDetailDto findExerciseDetailDto = new FindExerciseDetailDto(id, name, category);
        return findExerciseDetailDto;
    }

    public static List<FindExerciseDetailDto> convertFrom(List<Exercise> exerciseList) {

        return exerciseList.stream()
                .map(exercise -> new FindExerciseDetailDto(exercise.getId(), exercise.getName(), exercise.getCategory()))
                .collect(Collectors.toList());
    }

}
