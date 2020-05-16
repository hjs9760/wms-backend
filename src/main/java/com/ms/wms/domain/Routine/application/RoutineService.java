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
import java.util.Set;
import java.util.stream.Collectors;

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

        List<UpdateRoutineExerciseDto> dtoList = updateRoutineDto.getRoutineExerciseList();
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

        if (!memberId.equals(routine.getMemberId())) {
            throw new RuntimeException("no permission");
        }

        return this.mapToDto(routine);
    }

    public void removeRoutine(Long memberId, Long routineId) {
        routineRepository.deleteByIdAndMemberId(routineId, memberId);
    }


    public List<FindRoutineDetailDto> findRoutineList(Long memberId) {

        List<Routine> routines = this.routineRepository.findAllByMemberId(memberId);
        List<FindRoutineDetailDto> routineDetailDtoList = new ArrayList<>();

        for (Routine routine : routines) {

            // 운동 정보 맵
            FindRoutineDetailDto dto = mapToDto(routine);
            routineDetailDtoList.add(dto);
        }

        return routineDetailDtoList;
    }

    private FindRoutineDetailDto mapToDto(Routine routine) {

        // 운동 정보 맵
        Set<Exercise> routineExerciseSet = routine.getRoutineExerciseList().stream()
                .map(RoutineExercise::getExercise)
                .collect(Collectors.toSet());

        List<ExerciseInfoDto> exerciseInfoDtoList = new ArrayList<>();

        for (Exercise exercise: routineExerciseSet) {

            ExerciseInfoDto exerciseInfoDto = new ExerciseInfoDto(
                    exercise.getId(),
                    exercise.getName(),
                    exercise.getCategory());

            exerciseInfoDtoList.add(exerciseInfoDto);
        }


        // 운동 상세 정보 맵핑
        for (ExerciseInfoDto exerciseInfoDto : exerciseInfoDtoList) {

            List<ExerciseDetailInfoDto> exerciseDetailInfoDtoList = new ArrayList<>();
            for (RoutineExercise routineExercise : routine.getRoutineExerciseList()) {

                if (!routineExercise.getExercise().getId().equals(exerciseInfoDto.getId())) continue;

                exerciseDetailInfoDtoList.add(new ExerciseDetailInfoDto(
                        routineExercise.getExerciseSet(),
                        routineExercise.getCount(),
                        routineExercise.getWeight()
                ));
            }

            exerciseInfoDto.setExerciseDetailInfoList(exerciseDetailInfoDtoList);
        }

        return FindRoutineDetailDto.createRoutineDto(routine.getId(), routine.getName(), exerciseInfoDtoList);
    }
}
