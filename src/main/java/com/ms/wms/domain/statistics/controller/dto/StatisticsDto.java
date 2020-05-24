package com.ms.wms.domain.statistics.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class StatisticsDto {

    @NotNull
    private Long exerciseId;
    @NotBlank
    private LocalDate startDate;
    @NotBlank
    private LocalDate endDate;

}
