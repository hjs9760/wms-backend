package com.ms.wms.domain.exercise.application;

import com.ms.wms.domain.exercise.controller.FindExerciseDetailDto;
import com.ms.wms.domain.exercise.controller.SaveExerciseDto;
import com.ms.wms.domain.exercise.controller.UpdateExerciseDto;
import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.exercise.domain.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public void saveExercise(SaveExerciseDto exerciseDto) {

        List<Exercise> exercises = exerciseRepository.findByName(exerciseDto.getName());
        if (exercises.size() >= 1) {
            throw new RuntimeException("이미 중복된 exercise 이름입니다.");
        }


        Exercise exercise = Exercise.createExercise(exerciseDto.getName(), exerciseDto.getMemberId());
        exerciseRepository.save(exercise);
    }

    public FindExerciseDetailDto findExerciseById(Long id) {

        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 exercise입니다."));

        FindExerciseDetailDto findExerciseDetailDto = FindExerciseDetailDto.create(exercise.getId(), exercise.getName());
        return findExerciseDetailDto;
    }

    public List<FindExerciseDetailDto> findExerciseByName(String name) {

        List<Exercise> exerciseList = exerciseRepository.findByNameContaining(name);
        List<FindExerciseDetailDto> findExerciseDetailDtoList = FindExerciseDetailDto.convertFrom(exerciseList);
        return findExerciseDetailDtoList;
    }

    @Transactional
    public void updateExercise(UpdateExerciseDto updateExerciseDto) {

        Exercise exercise = exerciseRepository.findById(updateExerciseDto.getExerciseId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 exercise입니다."));

        exercise.setName(updateExerciseDto.getName());
    }

    public void removeExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}
