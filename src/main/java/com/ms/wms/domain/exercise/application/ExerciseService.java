package com.ms.wms.domain.exercise.application;

import com.ms.wms.domain.exercise.controller.dto.FindExerciseDetailDto;
import com.ms.wms.domain.exercise.controller.dto.SaveExerciseDto;
import com.ms.wms.domain.exercise.controller.dto.UpdateExerciseDto;
import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.exercise.domain.ExerciseRepository;
import com.ms.wms.domain.history.domain.History;
import com.ms.wms.domain.history.domain.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public void saveExercise(Long memberId, SaveExerciseDto exerciseDto) {
        if(exerciseRepository.findByName(exerciseDto.getName()).size() >= 1) {
            System.out.println("중복이요");
        } else {
            Exercise exercise = Exercise.convertSaveExercise(exerciseDto.getName(), memberId, exerciseDto.getCategory());
            exerciseRepository.save(exercise);
        }
    }

    public FindExerciseDetailDto findExerciseById(Long memberId, Long id) {

        Exercise exercise = exerciseRepository.findByIdAndMemberId(id, memberId);

        if (!memberId.equals(exercise.getMemberId())) {
            throw new RuntimeException("no permission");
        }

        FindExerciseDetailDto findExerciseDetailDto = FindExerciseDetailDto.convertFindExerciseDetailDto(exercise.getId(), exercise.getName(), exercise.getCategory());

        return findExerciseDetailDto;
    }

    public List<FindExerciseDetailDto> findExerciseByName(Long memberId, String name) {
        List<Exercise> exerciseList = exerciseRepository.findByMemberIdAndNameLike(memberId, "%" + name + "%");

        for (Exercise exercise : exerciseList) {
            if(!memberId.equals(exercise.getMemberId())) {
                throw new RuntimeException("no permission");
            }
        }

        List<FindExerciseDetailDto> findExerciseDetailDtoList = FindExerciseDetailDto.convertFindListExerciseDetailDto(exerciseList);
        return findExerciseDetailDtoList;
    }

    @Transactional
    public void updateExercise(Long memberId, UpdateExerciseDto updateExerciseDto) {
        Exercise exercise = exerciseRepository.findByIdAndMemberId(updateExerciseDto.getExerciseId(), memberId);

        if (!memberId.equals(exercise.getMemberId())) {
            throw new RuntimeException("no permission");
        }

        exercise.setName(updateExerciseDto.getName());
        exercise.setCategory(updateExerciseDto.getCategory());
    }

    public void removeExercise(Long memberId, Long id) {
        exerciseRepository.deleteByIdAndMemberId(id, memberId);
    }
}
