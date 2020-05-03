package com.ms.wms.domain.Routine.controller.dto;

import com.ms.wms.domain.Routine.controller.FindExerciseDetailInfoDto;
import lombok.Getter;

import java.util.List;

@Getter
public class FindRoutineDetailDto {

    private Long id;
    private String name;

    // todo : 마찬가지로 dtoList로 끝내지말고, exerciseDetailInfoList, exerciseDetailInfos로 변경
    private List<FindExerciseDetailInfoDto> exerciseDetailInfoList;

    // todo : 메소드 이름이 이상해
    public static FindRoutineDetailDto createRoutineDto(Long id, String name, List<FindExerciseDetailInfoDto> findExerciseDetailInfoDtoList) {
        FindRoutineDetailDto findRoutineDetailDto = new FindRoutineDetailDto();
        findRoutineDetailDto.id = id;
        findRoutineDetailDto.name = name;
        findRoutineDetailDto.exerciseDetailInfoList = findExerciseDetailInfoDtoList;

        return findRoutineDetailDto;
    }
}
