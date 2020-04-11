package com.ms.wms.controller;

import com.ms.wms.domain.Exercise;
import com.ms.wms.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/save")
    public void saveExercise(@RequestBody Exercise exercise) {
        exerciseService.saveExercise(exercise);
    }

    @PostMapping("/find")
    public Exercise findExercise(@RequestParam Long id) {
        return exerciseService.findExercise(id);
    }

    @PostMapping("/remove")
    public void removeExercise(@RequestBody Exercise exercise) {
        exerciseService.removeExercise(exercise);
    }
}
