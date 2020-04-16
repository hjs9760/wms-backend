package com.ms.wms.domain.Routine.domain;

import com.ms.wms.domain.Routine.controller.SaveRoutineExerciseDto;
import com.ms.wms.domain.Routine.controller.UpdateRoutineDto;
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

    public static Routine createSaveRoutine(String name, Long memberId) {
        Routine routine = new Routine();
        routine.name = name;
        routine.memberId = memberId;

        return routine;
    }

    public static Routine convertUpdateRoutine(Routine routine, UpdateRoutineDto updateRoutineDto) {
        routine.setName(updateRoutineDto.getName());

        return routine;
    }

    public void addExerciseInfo(Exercise exercise, SaveRoutineExerciseDto saveRoutineExerciseDto) {
        this.routineExerciseList.add(RoutineExercise.createSaveRoutineExercise(this, exercise, saveRoutineExerciseDto.getExerciseSet()
                                                            , saveRoutineExerciseDto.getCount(), saveRoutineExerciseDto.getWeight()));
    }
}

