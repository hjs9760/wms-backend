package com.ms.wms.domain.history.domain;

import com.ms.wms.domain.exercise.domain.QExercise;
import com.ms.wms.domain.statistics.controller.dto.StatisticsDetailDto;
import com.ms.wms.domain.statistics.controller.dto.StatisticsDetailInfoDto;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.List;

public class HistoryRepositoryImpl extends QuerydslRepositorySupport implements HistoryRepositoryQuery {

    public HistoryRepositoryImpl() {
        super(History.class);
    }

    @Override
    public StatisticsDetailDto findHistoryList(Long memberId, Long exerciseId, LocalDate startDate, LocalDate endDate) {
        StringTemplate formattedDate = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})"
                , endDate
                , ConstantImpl.create("%Y-%m-%d"));

        QHistory qHistory = QHistory.history;
        QExercise qExercise = QExercise.exercise;

        List<StatisticsDetailInfoDto> statisticsDetailInfoList = from(qHistory)
                .join(qExercise).on(qHistory.exercise.id.eq(qExercise.id))
                .where(qHistory.exercise.id.eq(exerciseId)
                        .and(qHistory.memberId.eq(memberId))
                        .and(qHistory.endDate.between(startDate.atStartOfDay(), endDate.atStartOfDay())))
                .groupBy(formattedDate, qHistory.endDate)
                .orderBy(qHistory.endDate.asc())
                .select(Projections.constructor(StatisticsDetailInfoDto.class, qHistory.endDate.as("date"), qHistory.weight.multiply(qHistory.count).sum()))
                .fetch();

        return new StatisticsDetailDto(statisticsDetailInfoList);
    }

}
