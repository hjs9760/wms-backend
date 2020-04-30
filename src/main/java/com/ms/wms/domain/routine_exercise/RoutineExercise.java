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

    // todo : 메소드 이름 중 save라는 건 필요 없다. 객체를 생성한다는 뜻만 있어도 충분. 이 객체를 save하려고 만드는지를 RoutineExercise 클래스가 왜 알아야함?
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
