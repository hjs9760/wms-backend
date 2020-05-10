package com.ms.wms.domain.history.domain;

import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.history.controller.dto.SaveHistoryDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Column
    private Long memberId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @Column
    private Integer count;

    @Column
    private Double weight;

    @Column
    private Integer exerciseSet;

    @Column(updatable = false)
    private LocalDateTime sdate;

    @Column(updatable = false)
    private LocalDateTime edate;

    @QueryProjection
    public History(Long id, Long memberId, Exercise exercise, Integer count, Double weight, Integer exerciseSet, LocalDateTime sdate, LocalDateTime edate) {
        this.id = id;
        this.memberId = memberId;
        this.exercise = exercise;
        this.count = count;
        this.weight = weight;
        this.exerciseSet = exerciseSet;
        this.sdate = sdate;
        this.edate = edate;
    }

    public static History createHistory(Long memberId, Exercise exercise, SaveHistoryDto dto) {
        History history =new History();

        history.memberId = memberId;
        history.exercise = exercise;
        history.exerciseSet = dto.getExerciseSet();
        history.weight = dto.getWeight();
        history.count = dto.getCount();
        history.sdate = dto.getSdate();
        history.edate = dto.getEdate();

        return history;

    }
}
