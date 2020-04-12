package com.ms.wms.domain.member.domain;

import com.ms.wms.domain.member.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
