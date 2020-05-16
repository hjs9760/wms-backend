package com.ms.wms.domain.routine.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
    void deleteByIdAndMemberId(Long routineId, Long memberId);
    List<Routine> findAllByMemberId(Long memberId);
}
