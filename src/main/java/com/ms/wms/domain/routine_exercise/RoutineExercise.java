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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_exercise_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @Column
    private int count;

    @Column
    private int weight;

    @Column
    private int exerciseSet;

    public static RoutineExercise createSaveRoutineExercise(Routine routine, Exercise exercise, int exerciseSet, int count, int weight) {
        RoutineExercise routineExercise = new RoutineExercise();
        routineExercise.routine = routine;
        routineExercise.exercise = exercise;
        routineExercise.exerciseSet = exerciseSet;
        routineExercise.count = count;
        routineExercise.weight = weight;

        return routineExercise;
    }


}
