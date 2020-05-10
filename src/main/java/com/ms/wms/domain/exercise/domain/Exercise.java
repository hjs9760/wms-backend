package com.ms.wms.domain.exercise.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long id;

    @Column
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column
    @Setter
    private Category category;

    @Setter
    @Column
    private String name;

    public static Exercise createExercise(String name, Long memberId, Category category) {
        Exercise exercise = new Exercise();
        exercise.name = name;
        exercise.memberId = memberId;
        exercise.category = category;

        return exercise;
    }

    public void updateInfo(String name, Category category) {
        this.name = name;
        this.category = category;
    }
}
