package com.ms.wms.domain.exercise.controller;

import com.ms.wms.domain.exercise.domain.Exercise;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FindExerciseDetailDto {

    private Long id;
    private String name;

    private FindExerciseDetailDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FindExerciseDetailDto create(Long id, String name) {

        FindExerciseDetailDto findExerciseDetailDto = new FindExerciseDetailDto(id, name);
        return findExerciseDetailDto;
    }

    public static List<FindExerciseDetailDto> convertFrom(List<Exercise> exerciseList) {

        return exerciseList.stream()
                .map(exercise -> new FindExerciseDetailDto(exercise.getId(), exercise.getName()))
                .collect(Collectors.toList());
    }

}
