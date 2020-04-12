package com.ms.wms.domain.member.domain;

<<<<<<< HEAD:src/main/java/com/ms/wms/domain/member/domain/MemberRepository.java
import com.ms.wms.domain.member.domain.Member;
=======
import com.ms.wms.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> 29c2fae6b87e8f5c1b2fa4deb30afc60f81d1fb6:src/main/java/com/ms/wms/repository/MemberRepository.java
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
