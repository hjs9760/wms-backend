package com.ms.wms.domain.history.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HistoryDetailByDateInfo {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Category category;
    private String exerciseName;
    private Integer exerciseSet;
    private Double weight;
    private Integer count;

    public HistoryDetailByDateInfo(LocalDateTime startDate, LocalDateTime endDate, Category category, String exerciseName, Integer exerciseSet, Double weight, Integer count) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.exerciseName = exerciseName;
        this.exerciseSet = exerciseSet;
        this.weight = weight;
        this.count = count;
    }
}
