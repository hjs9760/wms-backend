package com.ms.wms.domain.statistics.controller.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StatisticsDetailInfoDto {

    private LocalDateTime date;
    private Double volume; // 무게 * 총 횟수

    public StatisticsDetailInfoDto(LocalDateTime date, Double volume) {
        this.date = date;
        this.volume = volume;
    }
}
