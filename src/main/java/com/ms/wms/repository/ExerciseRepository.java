package com.ms.wms.repository;

import com.ms.wms.domain.Exercise;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ExerciseRepository {

    @PersistenceContext
    private EntityManager em;

    public void saveExercise(Exercise exercise) {
        if(exercise.getId() == null) {
            em.persist(exercise);
        } else {
            em.merge(exercise);
        }
    }

    public Exercise findExercise(Long id) {
        return em.find(Exercise.class, id);
    }

    public void removeExercise(Exercise exercise) {
        em.remove(exercise);
    }
}
