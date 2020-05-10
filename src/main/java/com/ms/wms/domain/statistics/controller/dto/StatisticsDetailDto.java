package com.ms.wms.domain.statistics.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StatisticsDetailDto {

    private List<StatisticsDetailInfoDto> statisticsInfoList;

    public StatisticsDetailDto(List<StatisticsDetailInfoDto> statisticsInfoList) {
        this.statisticsInfoList = statisticsInfoList;
    }
}
