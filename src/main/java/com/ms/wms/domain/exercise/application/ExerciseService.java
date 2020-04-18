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
        if(exerciseRepository.findByName(exerciseDto.getName()).size() >= 1) {
            System.out.println("중복이요");
        } else {
            Exercise exercise = Exercise.convertSaveExercise(exerciseDto.getName(), exerciseDto.getMemberId());
            exerciseRepository.save(exercise);
        }
    }

    public FindExerciseDetailDto findExerciseById(Long id) {
        Exercise exercise = exerciseRepository.findById(id).get();
        FindExerciseDetailDto findExerciseDetailDto = FindExerciseDetailDto.convertFindExerciseDetailDto(exercise.getId(), exercise.getName());

        return findExerciseDetailDto;
    }

    public List<FindExerciseDetailDto> findExerciseByName(String name) {
        List<Exercise> exerciseList = exerciseRepository.findByNameLike("%" + name + "%");
        List<FindExerciseDetailDto> findExerciseDetailDtoList = FindExerciseDetailDto.convertFindListExerciseDetailDto(exerciseList);
        return findExerciseDetailDtoList;
    }

    @Transactional
    public void updateExercise(UpdateExerciseDto updateExerciseDto) {
        Exercise exercise = exerciseRepository.findById(updateExerciseDto.getExerciseId())
                .orElseThrow(() -> new RuntimeException("조회된 exercise가 없습니다."));
        exercise.setName(updateExerciseDto.getName());
    }

    public void removeExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}
