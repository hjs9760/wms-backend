package com.ms.wms.domain.history.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class HistoryDetailByDateDto {

    private List<HistoryDetailByDateInfo> historyDetailByDateInfoList;

    public HistoryDetailByDateDto(List<HistoryDetailByDateInfo> historyDetailByDateInfoList) {
        this.historyDetailByDateInfoList = historyDetailByDateInfoList;
    }
}
