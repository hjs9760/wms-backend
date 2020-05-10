package com.ms.wms.domain.history.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import com.ms.wms.domain.history.domain.History;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HistoryDetailDto {

    private Long historyId;
    private Long exerciseId;
    private Category category;
    private String exerciseName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer exerciseSet;
    private Double weight;
    private Integer count;

    public static HistoryDetailDto createHistory(History history) {
        HistoryDetailDto dto = new HistoryDetailDto();

        dto.historyId = history.getId();
        dto.exerciseId = history.getExercise().getId();
        dto.category = history.getExercise().getCategory();
        dto.exerciseName = history.getExercise().getName();
        dto.startDate = history.getStartDate();
        dto.endDate = history.getEndDate();
        dto.exerciseSet = history.getExerciseSet();
        dto.weight = history.getWeight();
        dto.count = history.getCount();

        return dto;
    }

}
