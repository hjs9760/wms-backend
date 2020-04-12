package com.ms.wms.domain.Routine.application;

import com.ms.wms.domain.member.domain.Member;
import com.ms.wms.domain.Routine.domain.Routine;
import com.ms.wms.domain.Routine.domain.RoutineRepository;
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
        routineRepository.saveRoutine(routine);
    }

    public Routine findRoutine(Long id) {
        Routine routine = routineRepository.findRoutine(id);
        Member member = routineRepository.findMember(routine.getMember().getId());
        //Long memberId = member.getId();
        routine.setMember(member);
        return routine;
    }

    public void removeRoutine(Routine routine) {
        routineRepository.removeRoutine(routine);
    }

}
