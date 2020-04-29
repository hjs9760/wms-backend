package com.ms.wms.domain.exercise.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByName(String name);
    List<Exercise> findByMemberIdAndNameLike(Long memberId, String name);
    Exercise findByIdAndMemberId(Long id, Long memberId);
    void deleteByIdAndMemberId(Long id, Long memberId);
    List<Exercise> findByCategory(Category category);
}
