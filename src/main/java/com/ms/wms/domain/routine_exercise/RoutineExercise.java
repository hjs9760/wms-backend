package com.ms.wms.domain.routine_exercise;

import com.ms.wms.domain.Routine.domain.Routine;
import com.ms.wms.domain.exercise.domain.Exercise;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineExercise {

    @Id
    @GeneratedValue
    @Column(name = "routine_exercise_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private int count;

    private int weight;

    private int exerciseSet;


}
