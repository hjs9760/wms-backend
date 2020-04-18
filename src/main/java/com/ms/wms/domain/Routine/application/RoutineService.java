package com.ms.wms.domain.Routine.application;

import com.ms.wms.domain.Routine.controller.dto.*;
import com.ms.wms.domain.Routine.domain.Routine;
import com.ms.wms.domain.Routine.domain.RoutineRepository;
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

    public void saveRoutine(SaveRoutineDto saveRoutineDto) {

        Routine routine = Routine.createRoutine(saveRoutineDto.getName(), saveRoutineDto.getMemberId());

        for (SaveRoutineExerciseDto saveRoutineExerciseDto : saveRoutineDto.getSaveRoutineExerciseDtoList()) {
            Exercise exercise = exerciseRepository.findById(saveRoutineExerciseDto.getExerciseId()).get();

            routine.addExerciseInfo(exercise, saveRoutineExerciseDto);
        }

        routineRepository.save(routine);
    }

    @Transactional
    public void updateRoutine(UpdateRoutineDto updateRoutineDto) {

        Routine routine = routineRepository.findById(updateRoutineDto.getRoutineId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 routine 입니다."));

        List<SaveRoutineExerciseDto> dtos = updateRoutineDto.getSaveRoutineExerciseDtoList();
        List<RoutineExercise> routineExerciseList = new ArrayList<>();
        for (SaveRoutineExerciseDto dto  : dtos) {
            Exercise exercise = exerciseRepository.findById(dto.getExerciseId()).
                    orElseThrow(() -> new RuntimeException("존재하지 않는 exercise 입니다."));

            RoutineExercise routineExercise = RoutineExercise.createSaveRoutineExercise(routine, exercise, dto.getExerciseSet(), dto.getCount(), dto.getWeight());
            routineExerciseList.add(routineExercise);
        }

        routine.updateRoutineInfo(updateRoutineDto.getName(), routineExerciseList);
    }



    public FindRoutineDetailDto findRoutine(Long routineId) {
        Routine routine = routineRepository.findById(routineId).get();
        List<RoutineExercise> routineExerciseList = routine.getRoutineExerciseList();

        List<FindExerciseDetailInfo> findExerciseDetailInfoList = new ArrayList<>();

        for(RoutineExercise routineExercise : routineExerciseList) {
            FindExerciseDetailInfo findExerciseDetailInfo = FindExerciseDetailInfo.createFindExerciseDetailInfo(routineExercise.getExercise().getId()
                                                                                        , routineExercise.getExercise().getName()
                                                                                        , routineExercise.getCount()
                                                                                        , routineExercise.getWeight()
                                                                                        , routineExercise.getExerciseSet());
            findExerciseDetailInfoList.add(findExerciseDetailInfo);
        }

        return FindRoutineDetailDto.resultRoutineDto(routine.getId(), routine.getName(), findExerciseDetailInfoList);
    }

    public void removeRoutine(Long routineId) {
        routineRepository.deleteById(routineId);
    }

}
