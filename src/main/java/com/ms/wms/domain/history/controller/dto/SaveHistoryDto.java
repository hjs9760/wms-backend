package com.ms.wms.domain.history.controller.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SaveHistoryDto {

    private Long exerciseId;
    private Integer exerciseSet;
    private Double weight;
    private Integer count;
    private LocalDateTime sdate;
    private LocalDateTime edate;

}
