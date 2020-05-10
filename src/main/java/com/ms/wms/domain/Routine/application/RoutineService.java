package com.ms.wms.domain.routine.application;

import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.exercise.domain.ExerciseRepository;
import com.ms.wms.domain.routine.controller.dto.*;
import com.ms.wms.domain.routine.domain.Routine;
import com.ms.wms.domain.routine.domain.RoutineExercise;
import com.ms.wms.domain.routine.domain.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final ExerciseRepository exerciseRepository;

    public void saveRoutine(Long memberId, SaveRoutineDto saveRoutineDto) {

        Routine routine = Routine.createRoutine(saveRoutineDto.getName(), memberId);

        for (SaveRoutineExerciseDto dto : saveRoutineDto.getRoutineExerciseList()) {
            Exercise exercise = exerciseRepository.findById(dto.getExerciseId())
                    .orElseThrow(() -> new RuntimeException(("존재하지 않는 exercise 입니다.")));

            if(!memberId.equals(exercise.getMemberId())) {
                throw new RuntimeException("no permission");
            }

            routine.addExerciseInfo(exercise, dto);

        }

        routineRepository.save(routine);
    }

    @Transactional
    public void updateRoutine(Long memberId, UpdateRoutineDto updateRoutineDto) {

        Routine routine = routineRepository.findById(updateRoutineDto.getRoutineId())
                .orElseThrow(() -> new RuntimeException(("존재하지 않는 routine 입니다.")));

        List<UpdateRoutineExerciseDto> dtoList = updateRoutineDto.getUpdateRoutineExerciseDtoList();
        List<RoutineExercise> routineExerciseList = new ArrayList<>();
        for (UpdateRoutineExerciseDto dto  : dtoList) {
            Exercise exercise = exerciseRepository.findById(dto.getExerciseId())
                    .orElseThrow(() -> new RuntimeException(("존재하지 않는 exercise 입니다.")));

            if(!memberId.equals(exercise.getMemberId())) {
                throw new RuntimeException(("no permission"));
            }

            RoutineExercise routineExercise = RoutineExercise.createRoutineExercise(routine, exercise, dto.getExerciseSet(), dto.getCount(), dto.getWeight());
            routineExerciseList.add(routineExercise);
        }

        routine.updateRoutineInfo(updateRoutineDto.getName(), routineExerciseList);
    }

    public FindRoutineDetailDto findRoutine(Long memberId, Long routineId) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new RuntimeException(("존재하지 않는 routine 입니다.")));

        if(!memberId.equals(routine.getMemberId())) {
            throw new RuntimeException("no permission");
        }
        List<RoutineExercise> routineExerciseList = routine.getRoutineExerciseList();

        List<FindExerciseDetailInfoDto> findExerciseDetailInfoList = new ArrayList<>();

        for(RoutineExercise routineExercise : routineExerciseList) {
            FindExerciseDetailInfoDto findExerciseDetailInfo = FindExerciseDetailInfoDto.createFindExerciseDetailInfo(routineExercise.getExercise().getId()
                    , routineExercise.getExercise().getName()
                    , routineExercise.getExercise().getCategory()
                    , routineExercise.getCount()
                    , routineExercise.getWeight()
                    , routineExercise.getExerciseSet());
            findExerciseDetailInfoList.add(findExerciseDetailInfo);
        }

        return FindRoutineDetailDto.createRoutineDto(routine.getId(), routine.getName(), findExerciseDetailInfoList);
    }

    public void removeRoutine(Long memberId, Long routineId) {
        routineRepository.deleteByIdAndMemberId(routineId, memberId);
    }

}
