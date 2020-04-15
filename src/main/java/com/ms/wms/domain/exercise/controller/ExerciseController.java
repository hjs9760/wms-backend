package com.ms.wms.domain.exercise.controller;

import com.ms.wms.domain.exercise.application.ExerciseService;
import com.ms.wms.domain.exercise.domain.Exercise;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/save")
    public void saveExercise(@RequestBody SaveExerciseDto exerciseDto) {
        exerciseService.saveExercise(exerciseDto);
    }

    @GetMapping("/find/{id}")
    public FindExerciseDetailDto findExercise(@PathVariable Long id) {
        return exerciseService.findExerciseById(id);
    }

    @GetMapping("/findList/{name}")
    public List<FindExerciseDetailDto> findExerciseByName(@PathVariable String name) {
        return exerciseService.findExerciseByName(name);
    }

    @PostMapping("/update")
    public void updateExercise(@RequestBody UpdateExerciseDto updateExerciseDto) {
        exerciseService.updateExercise(updateExerciseDto);
    }

    @PostMapping("/remove/{id}")
    public void removeExercise(@PathVariable Long id) {
        exerciseService.removeExercise(id);
    }
}
