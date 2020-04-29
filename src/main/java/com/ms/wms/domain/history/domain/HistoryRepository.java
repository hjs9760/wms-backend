package com.ms.wms.domain.history.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findByMemberIdAndExerciseId(Long memberId, Long exerciseId);

    List<History> findByMemberIdAndExerciseIdIn(Long memberId, List<Long> exerciseIdList);

    List<History> findByMemberId(Long memberId);

    List<History> findByMemberIdAndEdateBetween(Long memberId, LocalDateTime sdate, LocalDateTime edate);
}
