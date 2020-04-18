package com.ms.wms.domain.Routine.controller.dto;

import com.ms.wms.domain.Routine.controller.FindExerciseDetailInfoDto;
import lombok.Getter;

import java.util.List;

@Getter
public class FindRoutineDetailDto {

    private Long id;
    private String name;
    private List<FindExerciseDetailInfoDto> findExerciseDetailInfoDtoList;

    public static FindRoutineDetailDto resultRoutineDto(Long id, String name, List<FindExerciseDetailInfoDto> findExerciseDetailInfoDtoList) {
        FindRoutineDetailDto findRoutineDetailDto = new FindRoutineDetailDto();
        findRoutineDetailDto.id = id;
        findRoutineDetailDto.name = name;
        findRoutineDetailDto.findExerciseDetailInfoDtoList = findExerciseDetailInfoDtoList;

        return findRoutineDetailDto;
    }
}
