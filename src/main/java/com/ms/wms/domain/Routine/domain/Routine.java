package com.ms.wms.domain.Routine.domain;

import com.ms.wms.domain.Routine.controller.UpdateRoutineDto;
import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.routine_exercise.RoutineExercise;
import com.ms.wms.domain.member.domain.Member;
import com.ms.wms.domain.routine_exercise.controller.FindRoutineExerciseDetailDto;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "routine", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RoutineExercise> routineExerciseList = new ArrayList<>();

    public static Routine convertSaveRoutine(String name, Member member) {
        Routine routine = new Routine();
        routine.name = name;
        routine.member = member;

        return routine;
    }

    public static Routine convertUpdateRoutine(Routine routine, UpdateRoutineDto updateRoutineDto) {
        routine.setName(updateRoutineDto.getName());

        return routine;
    }

}
