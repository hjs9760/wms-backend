package com.ms.wms.domain.exercise.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByNameAndMemberId(String name, Long memberId);
    List<Exercise> findByCategoryAndMemberId(Category category, Long memberId);
    void deleteByIdAndMemberId(Long id, Long memberId);
    List<Exercise> findAllByMemberId(Long memberId);
}
