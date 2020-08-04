package com.ms.wms.domain.history.controller.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FindHistoryDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
