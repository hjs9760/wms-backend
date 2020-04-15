package com.ms.wms.domain.exercise.controller;

import com.ms.wms.domain.exercise.domain.Exercise;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FindExerciseDetailDto {

    private Long id;
    private String name;

    public FindExerciseDetailDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FindExerciseDetailDto convertFindExerciseDetailDto(Long id, String name) {
        FindExerciseDetailDto findExerciseDetailDto = new FindExerciseDetailDto(id, name);
        return findExerciseDetailDto;
    }

    public static List<FindExerciseDetailDto> convertFindListExerciseDetailDto(List<Exercise> exerciseList) {
        List<FindExerciseDetailDto> findExerciseDetailDtoList = new ArrayList<>();
        for(Exercise exercise : exerciseList) {
            findExerciseDetailDtoList.add(new FindExerciseDetailDto(exercise.getId(), exercise.getName()));
        }

        return findExerciseDetailDtoList;
    }

}
