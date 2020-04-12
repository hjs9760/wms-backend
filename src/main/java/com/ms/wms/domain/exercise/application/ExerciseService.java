package com.ms.wms.domain.exercise.application;

import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.exercise.domain.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public void saveExercise(Exercise exercise) {
        exerciseRepository.saveExercise(exercise);
    }

    public Exercise findExercise(Long id) {
        return exerciseRepository.findExercise(id);
    }

    public void removeExercise(Exercise exercise) {
        exerciseRepository.removeExercise(exercise);
    }
}
