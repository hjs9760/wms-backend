package com.ms.wms.domain.history.domain;

import com.ms.wms.domain.history.controller.dto.HistoryAggregateInfo;
import com.ms.wms.domain.history.controller.dto.HistoryDetailByDateDto;
import com.ms.wms.domain.statistics.controller.dto.StatisticsDetailDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface HistoryRepositoryQuery {

    StatisticsDetailDto findHistoryList(Long memberId, Long exerciseId, LocalDate startDate, LocalDate endDate);

    HistoryAggregateInfo findHistoryAggregateInfo(Long memberId, LocalDateTime startDate, LocalDateTime endDate);

    HistoryDetailByDateDto findHistoryRepository(Long memberId, LocalDateTime startDate, LocalDateTime endDate);
}
