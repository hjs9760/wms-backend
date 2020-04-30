package com.ms.wms.domain.exercise.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByName(String name);

    // todo : Containing을 사용하는 것이 좋지 않을까 싶다. spring data jpa의 장점이 고도의 추상화인데, 이 메소드를 사용하는 곳에서 % 문자를 사용해야 한다는 단점이 있다.
    // 참고바람 -> https://stackoverflow.com/questions/25362540/like-query-in-spring-jparepository

    // 그리고 %가 하나 일때는 아래와 같은 jpa 키워드를 사용하면 됌
    // %name : startsWith
    // name% : endsWith
    List<Exercise> findByNameLike(String name);
}
