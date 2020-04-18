package com.ms.wms.domain.Routine.application;

import com.ms.wms.domain.Routine.controller.*;
import com.ms.wms.domain.Routine.controller.dto.FindRoutineDetailDto;
import com.ms.wms.domain.Routine.controller.dto.SaveRoutineDto;
import com.ms.wms.domain.Routine.controller.dto.SaveRoutineExerciseDto;
import com.ms.wms.domain.Routine.controller.dto.UpdateRoutineDto;
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

        // todo ; orElseThrow하기
        Routine routine = routineRepository.findById(updateRoutineDto.getRoutineId()).get();

        // todo : 루틴 안의 운동 수정 로직 추가하기
        routine.setName(updateRoutineDto.getName());
    }

    public FindRoutineDetailDto findRoutine(Long routineId) {

        // todo ; orElseThrow하기
        Routine routine = routineRepository.findById(routineId).get();

        List<RoutineExercise> routineExerciseList = routine.getRoutineExerciseList();

        List<FindExerciseDetailInfoDto> findExerciseDetailInfoDtoList = new ArrayList<>();

        for (RoutineExercise routineExercise : routineExerciseList) {
            FindExerciseDetailInfoDto dto = FindExerciseDetailInfoDto.createFindExerciseDetailInfo(
                    routineExercise.getExercise().getId()
                    , routineExercise.getExercise().getName()
                    , routineExercise.getCount()
                    , routineExercise.getWeight()
                    , routineExercise.getExerciseSet());

            findExerciseDetailInfoDtoList.add(dto);
        }

        return FindRoutineDetailDto.resultRoutineDto(routine.getId(), routine.getName(), findExerciseDetailInfoDtoList);
    }

    public void removeRoutine(Long routineId) {
        routineRepository.deleteById(routineId);
    }

}
