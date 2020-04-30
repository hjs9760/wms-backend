package com.ms.wms.domain.Routine.controller.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SaveRoutineDto {

    private String name;
    private Long memberId;

    // todo : 필드 이름까지 ~DtoList라고 쓰는 건 비추천. frontent에서 파라미터를 보낼 때 key값이 ~DtoList이어야 하는데 굳이 그런 단어를 붙일 필요가 없음
    // routineExercises or routineExerciseList로 하는 걸 추천함
    private List<SaveRoutineExerciseDto> saveRoutineExerciseDtoList;


}
