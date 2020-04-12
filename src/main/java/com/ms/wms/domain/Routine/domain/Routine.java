package com.ms.wms.domain.Routine.domain;

import com.ms.wms.domain.routine_exercise.RoutineExercise;
import com.ms.wms.domain.member.domain.Member;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long id;

    @Setter
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;

    @OneToMany(mappedBy = "routine", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RoutineExercise> exerciseList = new ArrayList<>();

}
