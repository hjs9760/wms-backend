package com.ms.wms.domain.exercise.controller;

import com.ms.wms.domain.exercise.application.ExerciseService;
import com.ms.wms.domain.exercise.controller.dto.ExerciseListByCategoryDto;
import com.ms.wms.domain.exercise.controller.dto.FindExerciseDetailDto;
import com.ms.wms.domain.exercise.controller.dto.SaveExerciseDto;
import com.ms.wms.domain.exercise.controller.dto.UpdateExerciseDto;
import com.ms.wms.domain.exercise.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
@Validated
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/save")
    public void saveExercise(@AuthenticationPrincipal Long memberId, @RequestBody @Valid SaveExerciseDto exerciseDto) {
        exerciseService.saveExercise(memberId, exerciseDto);
    }

    @GetMapping("/find/{id}")
    public FindExerciseDetailDto findExercise(@AuthenticationPrincipal Long memberId, @PathVariable Long id) {
        return exerciseService.findExerciseById(memberId, id);
    }

    @GetMapping("/findList")
    public List<ExerciseListByCategoryDto> findExerciseByName(@AuthenticationPrincipal Long memberId) {

        Map<Category, List<FindExerciseDetailDto>> map = exerciseService.findExercise(memberId).stream()
                .collect(Collectors.groupingBy(FindExerciseDetailDto::getCategory));

        List<ExerciseListByCategoryDto> result = new ArrayList<>();
        for (Category category : map.keySet()) {
            ExerciseListByCategoryDto exerciseListByCategoryDto =
                    new ExerciseListByCategoryDto(category, map.get(category));

            result.add(exerciseListByCategoryDto);
        }

        return result;
    }

    @PostMapping("/update")
    public void updateExercise(@AuthenticationPrincipal Long memberId, @RequestBody @Valid UpdateExerciseDto updateExerciseDto) {
        exerciseService.updateExercise(memberId, updateExerciseDto);
    }

    @PostMapping("/remove/{id}")
    public void removeExercise(@AuthenticationPrincipal Long memberId, @PathVariable Long id) {
        exerciseService.removeExercise(memberId, id);
    }

    @GetMapping("/findMyList")
    public List<ExerciseListByCategoryDto> findExerciseByMemberId(@AuthenticationPrincipal Long memberId) {
        Map<Category, List<FindExerciseDetailDto>> map = exerciseService.findExercise(memberId).stream()
                .collect(Collectors.groupingBy(FindExerciseDetailDto::getCategory));

        List<ExerciseListByCategoryDto> result = new ArrayList<>();
        for (Category category : map.keySet()) {
            ExerciseListByCategoryDto exerciseListByCategoryDto =
                    new ExerciseListByCategoryDto(category, map.get(category));

            result.add(exerciseListByCategoryDto);
        }

        return result;
    }
}
