package com.ms.wms.domain.exercise.application;

import com.ms.wms.domain.exercise.controller.dto.FindExerciseDetailDto;
import com.ms.wms.domain.exercise.controller.dto.SaveExerciseDto;
import com.ms.wms.domain.exercise.controller.dto.UpdateExerciseDto;
import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.exercise.domain.ExerciseRepository;
import com.ms.wms.global.config.slack.SlackMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    @Autowired
    private final SlackMessage slackMessage;

    public void saveExercise(Long memberId, SaveExerciseDto exerciseDto) {
        if(exerciseRepository.findByNameAndMemberId(exerciseDto.getName(), memberId).size() >= 1) {
            new RuntimeException("중복되는 운동명이 있습니다.");
            slackMessage.sendSlackMessage("Error", "member_id가 "+ memberId+ "가 등록할 '" + exerciseDto.getName() +"' 는(은) 중복되는 운동명 입니다.");
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

        FindExerciseDetailDto findExerciseDetailDto = new FindExerciseDetailDto(exercise.getId(), exercise.getName(), exercise.getCategory());

        return findExerciseDetailDto;
    }

    public List<FindExerciseDetailDto> findExerciseByName(Long memberId, String name) {
        List<Exercise> exerciseList = exerciseRepository.findByNameContainingAndMemberId(name, memberId);

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

        exercise.updateInfo(updateExerciseDto.getName(), updateExerciseDto.getCategory());
    }

    public void removeExercise(Long memberId, Long id) {
        exerciseRepository.deleteByIdAndMemberId(id, memberId);
    }

}
