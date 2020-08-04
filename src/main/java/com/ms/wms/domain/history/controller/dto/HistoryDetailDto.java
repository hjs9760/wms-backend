package com.ms.wms.domain.history.controller.dto;

import com.ms.wms.domain.history.domain.History;
import lombok.Getter;

import java.util.List;

@Getter
public class HistoryDetailDto {

    private TotalExerciseKind totalExerciseKind; // 총 운동 갯수(카테고리별)
    private HistoryAggregateInfo historyAggregateInfo; // 가장 많게,적게 한 운동
    private HistoryDetailByDateDto historyDetailList; // 일별 운동 이력 리스트

    public static HistoryDetailDto createHistory(List<History> historyList, HistoryAggregateInfo historyAggregateInfo, HistoryDetailByDateDto historyDetailByDateDto) {

        HistoryDetailDto dto = new HistoryDetailDto();
        TotalExerciseKind totalExerciseKind = new TotalExerciseKind();

        for(History history: historyList) {
            totalExerciseKind.createTotalExerciseKind(history.getExercise().getName(), history.getExercise().getCategory(), history.getCount());
            dto.totalExerciseKind = totalExerciseKind;
        }

        dto.historyAggregateInfo = historyAggregateInfo;
        dto.historyDetailList = historyDetailByDateDto;

        return dto;
    }

}
