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

    // todo : convert라는 단어가 말이 안된다. name과 memberId를 갖고 exercise로 변환한다는 뜻이 이상하다
    public static Exercise createExercise(String name, Long memberId) {
        Exercise exercise = new Exercise();
        exercise.name = name;
        exercise.memberId = memberId;

        return exercise;
    }

}
