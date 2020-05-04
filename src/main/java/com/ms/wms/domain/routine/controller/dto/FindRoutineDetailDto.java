package com.ms.wms.domain.routine.controller.dto;

import lombok.Getter;

import java.util.List;


@Getter
public class FindRoutineDetailDto {

    private Long id;
    private String name;
    private List<FindExerciseDetailInfoDto> exerciseDetailInfoList;

    public static FindRoutineDetailDto createRoutineDto(Long id, String name, List<FindExerciseDetailInfoDto> findExerciseDetailInfoDtoDtoList) {
        FindRoutineDetailDto findRoutineDetailDto = new FindRoutineDetailDto();
        findRoutineDetailDto.id = id;
        findRoutineDetailDto.name = name;
        findRoutineDetailDto.exerciseDetailInfoList = findExerciseDetailInfoDtoDtoList;

        return findRoutineDetailDto;
    }
}
