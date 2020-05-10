package com.ms.wms.domain.statistics.controller;

import com.ms.wms.domain.statistics.application.StatisticsService;
import com.ms.wms.domain.statistics.controller.dto.StatisticsDetailDto;
import com.ms.wms.domain.statistics.controller.dto.StatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @PostMapping("/analysis")
    public StatisticsDetailDto statisticsByExercise(@AuthenticationPrincipal Long memberId, @RequestBody StatisticsDto statisticsDto) {
        return statisticsService.findHistoryList(memberId, statisticsDto);
    }
}
