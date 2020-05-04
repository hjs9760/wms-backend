package com.ms.wms.domain.exercise.application;

import com.ms.wms.domain.exercise.controller.dto.FindExerciseDetailDto;
import com.ms.wms.domain.exercise.controller.dto.SaveExerciseDto;
import com.ms.wms.domain.exercise.controller.dto.UpdateExerciseDto;
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

    public void saveExercise(Long memberId, SaveExerciseDto exerciseDto) {
        if(exerciseRepository.findByNameAndMemberId(exerciseDto.getName(), memberId).size() >= 1) {
            System.out.println("중복이요");
        } else {
            Exercise exercise = Exercise.createExercise(exerciseDto.getName(), memberId, exerciseDto.getCategory());
            exerciseRepository.save(exercise);
        }
    }

    public FindExerciseDetailDto findExerciseById(Long memberId, Long id) {

        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("조회되지 않은 exercise 입니다."));

        if (!memberId.equals(exercise.getMemberId())) {
            throw new RuntimeException("no permission");
        }

        FindExerciseDetailDto findExerciseDetailDto = FindExerciseDetailDto.convertFindExerciseDetailDto(exercise.getId(), exercise.getName(), exercise.getCategory());

        return findExerciseDetailDto;
    }

    public List<FindExerciseDetailDto> findExerciseByName(Long memberId, String name) {
        List<Exercise> exerciseList = exerciseRepository.findByNameContainingAndMemberId(name, memberId);

        for (Exercise exercise : exerciseList) {
            if(!memberId.equals(exercise.getMemberId())) {
                throw new RuntimeException("no permission");
            }
        }

        List<FindExerciseDetailDto> findExerciseDetailDtoList = FindExerciseDetailDto.convertFrom(exerciseList);
        return findExerciseDetailDtoList;
    }

    @Transactional
    public void updateExercise(Long memberId, UpdateExerciseDto updateExerciseDto) {
        Exercise exercise = exerciseRepository.findById(updateExerciseDto.getExerciseId())
                .orElseThrow(() -> new RuntimeException(("존재하지 않는 exercise 입니다.")));

        if (!memberId.equals(exercise.getMemberId())) {
            throw new RuntimeException("no permission");
        }

        exercise.setName(updateExerciseDto.getName());
        exercise.setCategory(updateExerciseDto.getCategory());
    }

    public void removeExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}
