package com.ms.wms.domain.routine.application;

import com.ms.wms.domain.routine.controller.dto.*;
import com.ms.wms.domain.routine.domain.Routine;
import com.ms.wms.domain.routine.domain.RoutineRepository;
import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.exercise.domain.ExerciseRepository;
import com.ms.wms.domain.routine_exercise.RoutineExercise;
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

        for (SaveRoutineExerciseDto dto : saveRoutineDto.getSaveRoutineExerciseDtoList()) {
            Exercise exercise = exerciseRepository.findByIdAndMemberId(dto.getExerciseId(), memberId);

            if(!memberId.equals(exercise.getMemberId())) {
                throw new RuntimeException("no permission");
            }

            routine.addExerciseInfo(exercise, dto);

        }

        routineRepository.save(routine);
    }

    @Transactional
    public void updateRoutine(Long memberId, UpdateRoutineDto updateRoutineDto) {

        Routine routine = routineRepository.findByIdAndMemberId(updateRoutineDto.getRoutineId(), memberId);

        List<SaveRoutineExerciseDto> dtoList = updateRoutineDto.getSaveRoutineExerciseDtoList();
        List<RoutineExercise> routineExerciseList = new ArrayList<>();
        for (SaveRoutineExerciseDto dto  : dtoList) {
            Exercise exercise = exerciseRepository.findByIdAndMemberId(dto.getExerciseId(), memberId);

            if(!memberId.equals(exercise.getMemberId())) {
                throw new RuntimeException(("no permission"));
            }

            RoutineExercise routineExercise = RoutineExercise.createSaveRoutineExercise(routine, exercise, dto.getExerciseSet(), dto.getCount(), dto.getWeight());
            routineExerciseList.add(routineExercise);
        }

        routine.updateRoutineInfo(updateRoutineDto.getName(), routineExerciseList);
    }

    public FindRoutineDetailDto findRoutine(Long memberId, Long routineId) {
        Routine routine = routineRepository.findByIdAndMemberId(routineId, memberId);

        if(!memberId.equals(routine.getMemberId())) {
            throw new RuntimeException("no permission");
        }
        List<RoutineExercise> routineExerciseList = routine.getRoutineExerciseList();

        List<FindExerciseDetailInfo> findExerciseDetailInfoList = new ArrayList<>();

        for(RoutineExercise routineExercise : routineExerciseList) {
            FindExerciseDetailInfo findExerciseDetailInfo = FindExerciseDetailInfo.createFindExerciseDetailInfo(routineExercise.getExercise().getId()
                                                                                        , routineExercise.getExercise().getName()
                                                                                        , routineExercise.getExercise().getCategory()
                                                                                        , routineExercise.getCount()
                                                                                        , routineExercise.getWeight()
                                                                                        , routineExercise.getExerciseSet());
            findExerciseDetailInfoList.add(findExerciseDetailInfo);
        }

        return FindRoutineDetailDto.resultRoutineDto(routine.getId(), routine.getName(), findExerciseDetailInfoList);
    }

    public void removeRoutine(Long memberId, Long routineId) {
        routineRepository.deleteByIdAndMemberId(routineId, memberId);
    }

}
