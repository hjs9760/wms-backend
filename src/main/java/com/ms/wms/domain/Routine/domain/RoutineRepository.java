package com.ms.wms.domain.Routine.domain;

import com.ms.wms.domain.member.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoutineRepository {

    @PersistenceContext
    private EntityManager em;

    public void saveRoutine(Routine routine) {
        if(routine.getId() == null) {
            em.persist(routine);
        } else {
            em.merge(routine);
        }
    }

    public Routine findRoutine(Long id) {
        return em.find(Routine.class, id);
    }

    public void removeRoutine(Routine routine) {
        em.remove(routine);
    }

    public Member findMember(Long id) {
        return em.find(Member.class, id);
    }
}
