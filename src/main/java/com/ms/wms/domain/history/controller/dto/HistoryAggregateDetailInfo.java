package com.ms.wms.domain.history.controller.dto;

import lombok.Getter;

@Getter
public class HistoryAggregateDetailInfo {

    private String name;
    private Long count;
    private Double volume;

    public HistoryAggregateDetailInfo(String name, Long count, Double volume) {
        this.name = name;
        this.count = count;
        this.volume = volume;
    }
}
