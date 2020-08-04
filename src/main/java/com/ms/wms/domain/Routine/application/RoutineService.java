package com.ms.wms.domain.routine.application;

import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.exercise.domain.ExerciseRepository;
import com.ms.wms.domain.routine.controller.dto.*;
import com.ms.wms.domain.routine.domain.Routine;
import com.ms.wms.domain.routine.domain.RoutineExercise;
import com.ms.wms.domain.routine.domain.RoutineRepository;
import com.ms.wms.global.config.exception.business.NoExistException;
import com.ms.wms.global.config.exception.business.UnAuthorityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final ExerciseRepository exerciseRepository;

    public void saveRoutine(Long memberId, SaveRoutineDto saveRoutineDto) {

        Routine routine = Routine.createRoutine(saveRoutineDto.getName(), memberId);

        for (SaveRoutineExerciseDto dto : saveRoutineDto.getRoutineExerciseList()) {
            Exercise exercise = exerciseRepository.findById(dto.getExerciseId())
                    .orElseThrow(() -> new NoExistException(("존재하지 않는 exercise 입니다.")));

            if(!memberId.equals(exercise.getMemberId())) {
                throw new UnAuthorityException("No permission");
            }

            routine.addExerciseInfo(exercise, dto);

        }

        routineRepository.save(routine);
    }

    @Transactional
    public void updateRoutine(Long memberId, UpdateRoutineDto updateRoutineDto) {

        Routine routine = routineRepository.findById(updateRoutineDto.getRoutineId())
                .orElseThrow(() -> new NoExistException(("존재하지 않는 routine 입니다.")));

        List<UpdateRoutineExerciseDto> dtoList = updateRoutineDto.getUpdateRoutineExerciseList();
        List<RoutineExercise> routineExerciseList = new ArrayList<>();
        for (UpdateRoutineExerciseDto dto  : dtoList) {
            Exercise exercise = exerciseRepository.findById(dto.getExerciseId())
                    .orElseThrow(() -> new NoExistException(("존재하지 않는 exercise 입니다.")));

            if(!memberId.equals(exercise.getMemberId())) {
                throw new UnAuthorityException(("No permission"));
            }

            RoutineExercise routineExercise = RoutineExercise.createRoutineExercise(routine, exercise, dto.getExerciseSet(), dto.getCount(), dto.getWeight());
            routineExerciseList.add(routineExercise);
        }

        routine.updateRoutineInfo(updateRoutineDto.getName(), routineExerciseList);
    }

    public void removeRoutine(Long memberId, Long routineId) {
        routineRepository.deleteByIdAndMemberId(routineId, memberId);
    }

    public List<FindRoutineDetailDto> findMyRoutine(Long memberId) {
        List<Routine> routineList = routineRepository.findByMemberId(memberId);

        List<FindExerciseDetailInfoDto> findExerciseDetailInfoList = new ArrayList<>();

        List<FindRoutineDetailDto> findRoutineDetailDtoList = new ArrayList<>();

        for(Routine routine: routineList) {
            if (!memberId.equals(routine.getMemberId())) {
                throw new UnAuthorityException("No permission");
            }

            List<RoutineExercise> routineExerciseList = routine.getRoutineExerciseList();
            List<RoutineExerciseDetailDto> dtoList = new ArrayList<>();
            FindExerciseDetailInfoDto findExerciseDetailInfo = new FindExerciseDetailInfoDto();

            for (RoutineExercise routineExercise : routineExerciseList) {
                // 첫 운동
                if(routineExerciseList.indexOf(routineExercise) == 0) {
                    findExerciseDetailInfo = FindExerciseDetailInfoDto.createExerciseInfo(routineExercise.getExercise().getId(), routineExercise.getExercise().getName(),routineExercise.getExercise().getCategory());
                    dtoList.add(RoutineExerciseDetailDto.createRoutineExerciseDto(routineExercise.getCount(), routineExercise.getWeight(),routineExercise.getExerciseSet()));
                } else {
                    // 이전 운동과 같은 운동일 떄
                    if(routineExercise.getExercise().getId() == findExerciseDetailInfo.getExerciseId()){
                        dtoList.add(RoutineExerciseDetailDto.createRoutineExerciseDto(routineExercise.getCount(), routineExercise.getWeight(),routineExercise.getExerciseSet()));
                    }
                    // 새로운 운동 일떄
                    else {
                        findExerciseDetailInfoList.add(FindExerciseDetailInfoDto.createFindExerciseDetailInfo(findExerciseDetailInfo, dtoList));
//                        dtoList.removeAll(dtoList);
                        dtoList = new ArrayList<>();
                        findExerciseDetailInfo = FindExerciseDetailInfoDto.createExerciseInfo(routineExercise.getExercise().getId(), routineExercise.getExercise().getName(),routineExercise.getExercise().getCategory());
                        dtoList.add(RoutineExerciseDetailDto.createRoutineExerciseDto(routineExercise.getCount(), routineExercise.getWeight(),routineExercise.getExerciseSet()));
                    }
                }

                // 마지막 운동정보(routineExercise)일 떄
                if(routineExerciseList.indexOf(routineExercise) == routineExerciseList.size()-1) {
                    findExerciseDetailInfoList.add(FindExerciseDetailInfoDto.createFindExerciseDetailInfo(findExerciseDetailInfo, dtoList));
                    dtoList = new ArrayList<>();
                    findRoutineDetailDtoList.add(FindRoutineDetailDto.createRoutineDto(routine.getId(), routine.getName(), findExerciseDetailInfoList));
                    findExerciseDetailInfoList = new ArrayList<>();
                }
            }

        }

        return findRoutineDetailDtoList;
    }
}
