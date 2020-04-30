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

    // todo : 이 메소드 이름도 convert라는 건 괜찮은데 그 뒤에 나오는 이름들은 이 클래스와 중복되서 쓸데없이 김.
    // 만약 메소드 시그니처가
    // List<FindExerciseDetailDto> convertFrom(List<Exercise> exerciseList) 이렇게 해도
    // 파라미터 (exerciseList)를 반환형 (List<FindExerciseDetailDto>) 으로 컨버팅하는구나 라고 쉽게 이해할 수 있음
    public static List<FindExerciseDetailDto> convertFindListExerciseDetailDto(List<Exercise> exerciseList) {

        return exerciseList.stream()
                .map(exercise -> new FindExerciseDetailDto(exercise.getId(), exercise.getName()))
                .collect(Collectors.toList());
    }

}
