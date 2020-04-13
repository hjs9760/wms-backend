package com.ms.wms.domain.Routine.application;

import com.ms.wms.domain.Routine.controller.FindRoutineDetailDto;
import com.ms.wms.domain.Routine.controller.SaveRoutineDto;
import com.ms.wms.domain.Routine.controller.UpdateRoutineDto;
import com.ms.wms.domain.Routine.domain.Routine;
import com.ms.wms.domain.Routine.domain.RoutineRepository;
import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.routine_exercise.RoutineExercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;

    public void saveRoutine(SaveRoutineDto saveRoutineDto) {
        Routine routine = Routine.convertSaveRoutine(saveRoutineDto.getName(), saveRoutineDto.getMember());
        routineRepository.save(routine);
    }

    public void updateRoutine(UpdateRoutineDto updateRoutineDto) {
        Routine routine = routineRepository.findById(updateRoutineDto.getId()).get();
        routine = Routine.convertUpdateRoutine(routine, updateRoutineDto);
        routineRepository.save(routine);
    }

    public FindRoutineDetailDto findRoutine(Long routineId) {
        Routine routine = routineRepository.findById(routineId).get();
        List<RoutineExercise> routineExerciseList= routine.getRoutineExerciseList();
        List<Exercise> exerciseList = null;
        for (RoutineExercise routineExercise : routineExerciseList) {
            exerciseList.add(routineExercise.getExercise());
        }

        return FindRoutineDetailDto.resultRoutinDto(routine.getId(), routine.getName(), exerciseList, routineExerciseList);
    }

    public void removeRoutine(Long routineId) {
        routineRepository.deleteById(routineId);
    }

}
