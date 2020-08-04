package com.ms.wms.domain.routine.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

import java.util.List;

@Getter
public class FindExerciseDetailInfoDto {

    private Long exerciseId;
    private String name;
    private Category category;
    private List<RoutineExerciseDetailDto> routineExerciseList;

    public static FindExerciseDetailInfoDto createFindExerciseDetailInfo(FindExerciseDetailInfoDto dto, List<RoutineExerciseDetailDto> dtoList) {
        FindExerciseDetailInfoDto findExerciseDetailInfoDto = new FindExerciseDetailInfoDto();
        findExerciseDetailInfoDto.exerciseId = dto.exerciseId;
        findExerciseDetailInfoDto.name = dto.name;
        findExerciseDetailInfoDto.category = dto.category;
        findExerciseDetailInfoDto.routineExerciseList = dtoList;

        return findExerciseDetailInfoDto;
    }

    public static FindExerciseDetailInfoDto createExerciseInfo(Long id, String name, Category category) {
        FindExerciseDetailInfoDto dto = new FindExerciseDetailInfoDto();
        dto.exerciseId = id;
        dto.name = name;
        dto.category = category;

        return dto;
    }
}
