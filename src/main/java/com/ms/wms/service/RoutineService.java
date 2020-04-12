package com.ms.wms.service;

import com.ms.wms.domain.Member;
import com.ms.wms.domain.Routine;
import com.ms.wms.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;
    //private final ModelMapper modelMapper;

    public void saveRoutine(Routine routine) {
//        routineRepository.saveRoutine(routine);
    }

    public Routine findRoutine(Long id) {
        Routine routine = routineRepository.findById(id).get();
        return routine;
    }

    public void removeRoutine(Routine routine) {
//        routineRepository.removeRoutine(routine);
    }

}
