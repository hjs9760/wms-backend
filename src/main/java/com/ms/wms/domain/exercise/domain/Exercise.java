package com.ms.wms.domain.exercise.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exercise {

    @Id
    @GeneratedValue
    @Column(name = "exercise_id")
    private Long id;

    @Column
    @NotNull
    private Long memberId;

    @Setter
    @Column
    @NotNull
    private String name;

    public static Exercise convertSaveExercise(String name, Long memberId) {
        Exercise exercise = new Exercise();
        exercise.name = name;
        exercise.memberId = memberId;

        return exercise;
    }
}
