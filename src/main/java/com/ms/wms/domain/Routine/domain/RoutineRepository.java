package com.ms.wms.domain.Routine.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

    Routine findByIdAndMemberId(Long id, Long memberId);

    void deleteByIdAndMemberId(Long routineId, Long memberId);
}
