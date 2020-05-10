package com.ms.wms.domain.exercise.controller;

import com.ms.wms.domain.exercise.application.ExerciseService;
import com.ms.wms.domain.exercise.controller.dto.FindExerciseDetailDto;
import com.ms.wms.domain.exercise.controller.dto.SaveExerciseDto;
import com.ms.wms.domain.exercise.controller.dto.UpdateExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/save")
    public void saveExercise(@AuthenticationPrincipal Long memberId, @RequestBody SaveExerciseDto exerciseDto) {
        exerciseService.saveExercise(memberId, exerciseDto);
    }

    @GetMapping("/find/{id}")
    public FindExerciseDetailDto findExercise(@AuthenticationPrincipal Long memberId, @PathVariable Long id) {
        return exerciseService.findExerciseById(memberId, id);
    }

    @GetMapping("/findList/{name}")
    public List<FindExerciseDetailDto> findExerciseByName(@AuthenticationPrincipal Long memberId, @PathVariable String name) {
        return exerciseService.findExerciseByName(memberId, name);
    }

    @PostMapping("/update")
    public void updateExercise(@AuthenticationPrincipal Long memberId, @RequestBody UpdateExerciseDto updateExerciseDto) {
        exerciseService.updateExercise(memberId, updateExerciseDto);
    }

    @PostMapping("/remove/{id}")
    public void removeExercise(@AuthenticationPrincipal Long memberId, @PathVariable Long id) {
        exerciseService.removeExercise(memberId, id);
    }
}
