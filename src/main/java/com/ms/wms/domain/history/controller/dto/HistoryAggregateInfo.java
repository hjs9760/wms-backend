package com.ms.wms.domain.history.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class HistoryAggregateInfo {
    private List<HistoryAggregateDetailInfo> historyAggregateInfoList;

    public HistoryAggregateInfo(List<HistoryAggregateDetailInfo> historyAggregateInfoList) {
        this.historyAggregateInfoList = historyAggregateInfoList;
    }

}
