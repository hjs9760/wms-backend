package com.ms.wms.domain.statistics.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StatisticsDto {

    private LocalDate sdate;
    private LocalDate edate;
    private Long exerciseId;



}
