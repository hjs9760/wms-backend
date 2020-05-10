package com.ms.wms.domain.history.domain;

import com.ms.wms.domain.statistics.controller.dto.StatisticsDetailDto;

import java.time.LocalDate;

public interface HistoryRepositoryQuery {

    StatisticsDetailDto findHistoryList(Long memberId, Long exerciseId, LocalDate startDate, LocalDate endDate);
}
