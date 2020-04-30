package com.ms.wms.domain.routine.domain;

import com.ms.wms.domain.routine.controller.dto.SaveRoutineExerciseDto;
import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.routine_exercise.RoutineExercise;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long id;

    @Column
    @Setter
    private String name;

    @Column
    private Long memberId;

    @OneToMany(mappedBy = "routine", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoutineExercise> routineExerciseList = new ArrayList<>();

    public static Routine createRoutine(String name, Long memberId) {
        Routine routine = new Routine();
        routine.name = name;
        routine.memberId = memberId;

        return routine;
    }

    public void addExerciseInfo(Exercise exercise, SaveRoutineExerciseDto saveRoutineExerciseDto) {
        this.routineExerciseList.add(RoutineExercise.createSaveRoutineExercise(this, exercise, saveRoutineExerciseDto.getExerciseSet()
                                                            , saveRoutineExerciseDto.getCount(), saveRoutineExerciseDto.getWeight()));
    }

    public void updateRoutineInfo(String name, List<RoutineExercise> routineExercises) {
        this.name = name;
        this.routineExerciseList.clear();
        this.routineExerciseList.addAll(routineExercises);
    }
}

