package com.ms.wms.domain.history.domain;

import com.ms.wms.domain.exercise.domain.QExercise;
import com.ms.wms.domain.history.controller.dto.HistoryAggregateDetailInfo;
import com.ms.wms.domain.history.controller.dto.HistoryAggregateInfo;
import com.ms.wms.domain.history.controller.dto.HistoryDetailByDateDto;
import com.ms.wms.domain.history.controller.dto.HistoryDetailByDateInfo;
import com.ms.wms.domain.statistics.controller.dto.StatisticsDetailDto;
import com.ms.wms.domain.statistics.controller.dto.StatisticsDetailInfoDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
                , "%Y-%m-%d");

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

    @Override
    public HistoryAggregateInfo findHistoryAggregateInfo(Long memberId, LocalDateTime startDate, LocalDateTime endDate) {
        QHistory qHistory = QHistory.history;
        QExercise qExercise = QExercise.exercise;

        List<HistoryAggregateDetailInfo> historyAggregateDetailInfoList = from(qHistory)
                .join(qExercise).on(qHistory.exercise.id.eq(qExercise.id))
                .where(qHistory.memberId.eq(memberId)
                        .and(qHistory.endDate.between(startDate, endDate)))
                .groupBy(qHistory.exercise.id)
                .orderBy(qHistory.count.count().desc(), qHistory.count.multiply(qHistory.weight).sum().doubleValue().desc())
                .select(Projections.constructor(HistoryAggregateDetailInfo.class, qHistory.exercise.name, qHistory.count.count(), qHistory.count.multiply(qHistory.weight).sum().doubleValue()))
                .fetch();

        return new HistoryAggregateInfo(historyAggregateDetailInfoList);
    }

    @Override
    public HistoryDetailByDateDto findHistoryRepository(Long memberId, LocalDateTime startDate, LocalDateTime endDate) {
        QHistory qHistory = QHistory.history;
        QExercise qExercise = QExercise.exercise;

        List<HistoryDetailByDateInfo> historyDetailByDateList = from(qHistory)
                .join(qExercise).on(qHistory.exercise.id.eq(qExercise.id))
                .where(qHistory.memberId.eq(memberId)
                        .and(qHistory.endDate.between(startDate, endDate)))
                .orderBy(qHistory.endDate.desc(), qHistory.exercise.name.asc(), qHistory.exerciseSet.asc())
                .select(Projections.constructor(HistoryDetailByDateInfo.class, qHistory.startDate, qHistory.endDate, qHistory.exercise.category, qHistory.exercise.name, qHistory.exerciseSet, qHistory.weight, qHistory.count))
                .fetch();

        return new HistoryDetailByDateDto(historyDetailByDateList);
    }

}
