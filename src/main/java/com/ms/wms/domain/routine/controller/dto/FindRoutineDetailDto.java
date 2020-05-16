package com.ms.wms.domain.routine.controller.dto;

import lombok.Getter;

import java.util.List;


@Getter
public class FindRoutineDetailDto {

    private Long id;
    private String name;
    private List<ExerciseInfoDto> exerciseInfoList;

    public static FindRoutineDetailDto createRoutineDto(Long id, String name, List<ExerciseInfoDto> exerciseInfoList) {
        FindRoutineDetailDto findRoutineDetailDto = new FindRoutineDetailDto();
        findRoutineDetailDto.id = id;
        findRoutineDetailDto.name = name;
        findRoutineDetailDto.exerciseInfoList = exerciseInfoList;

        return findRoutineDetailDto;
    }
}
