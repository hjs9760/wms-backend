package com.ms.wms.domain.history.controller;

import com.ms.wms.domain.exercise.domain.Category;
import com.ms.wms.domain.history.application.HistoryService;
import com.ms.wms.domain.history.controller.dto.HistoryDetailDto;
import com.ms.wms.domain.history.controller.dto.SaveHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping("/save")
    public void saveHistory(@AuthenticationPrincipal Long memberId, @RequestBody List<SaveHistoryDto> historyDtoList) {
        historyService.saveHistory(memberId, historyDtoList);
    }

    // 운동에 대한 이력 조회
    @GetMapping("findByExercise/{exerciseId}")
    public List<HistoryDetailDto> findHistoryByExerciseId(@AuthenticationPrincipal Long memberId, @PathVariable Long exerciseId) {
        return historyService.findByExerciseId(memberId, exerciseId);
    }

    // 회원에 대한 전체 운동 이력 조회
    @GetMapping("findByMember")
    public List<HistoryDetailDto> findHistoryByMemberId(@AuthenticationPrincipal Long memberId) {
        return historyService.findByMemberId(memberId);
    }

    // 카테고리에 대한 운동 이력 조회
    @GetMapping("findByCategory/{category}")
    public List<HistoryDetailDto> findByCategory(@AuthenticationPrincipal Long memberId, @PathVariable Category category){
        return historyService.findByCategory(memberId, category);
    }

    // 기간에 대한 운동 이력 조회
    @GetMapping("findByDate")
    public List<HistoryDetailDto> findByDate(@AuthenticationPrincipal Long memberId, @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate){
        return historyService.findByDate(memberId, startDate, endDate);
    }

}
