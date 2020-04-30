package com.ms.wms.domain.Routine.domain;

import com.ms.wms.domain.Routine.controller.dto.SaveRoutineExerciseDto;
import com.ms.wms.domain.Routine.controller.dto.UpdateRoutineDto;
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

    // todo :  불필요한 메소드 제거
    public static Routine convertUpdateRoutine(Routine routine, UpdateRoutineDto updateRoutineDto) {
        routine.setName(updateRoutineDto.getName());

        return routine;
    }

    public void addExerciseInfo(Exercise exercise, SaveRoutineExerciseDto saveRoutineExerciseDto) {

        // todo: 딱 봐도 코드 읽기가 어려움 (아래 주석 달아놓은 것처럼 만들면 한눈에 들어와서 읽기 편함)
        this.routineExerciseList.add(RoutineExercise.createSaveRoutineExercise(this, exercise, saveRoutineExerciseDto.getExerciseSet()
                , saveRoutineExerciseDto.getCount(), saveRoutineExerciseDto.getWeight()));


//        RoutineExercise routineExercise = RoutineExercise.createSaveRoutineExercise(
//                this,
//                exercise,
//                saveRoutineExerciseDto.getExerciseSet()
//                , saveRoutineExerciseDto.getCount(),
//                saveRoutineExerciseDto.getWeight());
//
//        this.routineExerciseList.add(routineExercise);
    }
}

