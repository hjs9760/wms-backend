package com.ms.wms.domain.statistics.application;

import com.ms.wms.domain.history.domain.HistoryRepository;
import com.ms.wms.domain.statistics.controller.dto.StatisticsDetailDto;
import com.ms.wms.domain.statistics.controller.dto.StatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final HistoryRepository historyRepository;

    public StatisticsDetailDto findHistoryList(Long memberId, StatisticsDto statisticsDto) {
        return historyRepository.findHistoryList(memberId, statisticsDto.getExerciseId(), statisticsDto.getStartDate(), statisticsDto.getEndDate());
    }
}
