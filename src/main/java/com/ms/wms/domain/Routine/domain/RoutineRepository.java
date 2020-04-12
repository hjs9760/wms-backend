package com.ms.wms.domain.Routine.domain;

<<<<<<< HEAD:src/main/java/com/ms/wms/domain/Routine/domain/RoutineRepository.java
import com.ms.wms.domain.member.domain.Member;
=======
import com.ms.wms.domain.Member;
import com.ms.wms.domain.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> 29c2fae6b87e8f5c1b2fa4deb30afc60f81d1fb6:src/main/java/com/ms/wms/repository/RoutineRepository.java
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

//    @PersistenceContext
//    private EntityManager em;
//
//    public void saveRoutine(Routine routine) {
//        if(routine.getId() == null) {
//            em.persist(routine);
//        } else {
//            em.merge(routine);
//        }
//    }
//
//    public Routine findRoutine(Long id) {
//        return em.find(Routine.class, id);
//    }
//
//    public void removeRoutine(Routine routine) {
//        em.remove(routine);
//    }
//
//    public Member findMember(Long id) {
//        return em.find(Member.class, id);
//    }
}
