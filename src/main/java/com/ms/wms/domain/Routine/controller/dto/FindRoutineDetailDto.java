package com.ms.wms.domain.Routine.controller.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class FindRoutineDetailDto {

    private Long id;
    private String name;
    private List<FindExerciseDetailInfo> findExerciseDetailInfoList;

    public static FindRoutineDetailDto resultRoutineDto(Long id, String name, List<FindExerciseDetailInfo> findExerciseDetailInfoList) {
        FindRoutineDetailDto findRoutineDetailDto = new FindRoutineDetailDto();
        findRoutineDetailDto.id = id;
        findRoutineDetailDto.name = name;
        findRoutineDetailDto.findExerciseDetailInfoList = findExerciseDetailInfoList;

        return findRoutineDetailDto;
    }
}
