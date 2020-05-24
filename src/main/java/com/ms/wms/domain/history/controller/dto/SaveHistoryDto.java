package com.ms.wms.domain.history.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class SaveHistoryDto {

    @NotNull
    private Long exerciseId;
    @NotNull
    private Integer exerciseSet;
    @NotNull
    private Double weight;
    @NotNull
    private Integer count;
    @NotBlank
    private LocalDateTime startDate;
    @NotBlank
    private LocalDateTime endDate;

}
