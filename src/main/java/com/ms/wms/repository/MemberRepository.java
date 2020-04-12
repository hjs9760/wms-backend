package com.ms.wms.repository;

import com.ms.wms.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
