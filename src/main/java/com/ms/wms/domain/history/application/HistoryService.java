package com.ms.wms.domain.history.application;

import com.ms.wms.domain.exercise.domain.Category;
import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.exercise.domain.ExerciseRepository;
import com.ms.wms.domain.history.controller.dto.HistoryDetailDto;
import com.ms.wms.domain.history.controller.dto.SaveHistoryDto;
import com.ms.wms.domain.history.domain.History;
import com.ms.wms.domain.history.domain.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final ExerciseRepository exerciseRepository;

    public void saveHistory(Long memberId, List<SaveHistoryDto> historyDtoList) {
        List<History> historyList = new ArrayList<>();

        for (SaveHistoryDto dto : historyDtoList) {
            Exercise exercise = exerciseRepository.findById(dto.getExerciseId())
                    .orElseThrow(() -> new RuntimeException(("존재하지 않는 exercise 입니다.")));
            historyList.add(History.createHistory(memberId, exercise, dto));
        }

        historyRepository.saveAll(historyList);
    }

    // 운동에대한 이력 조회
    public List<HistoryDetailDto> findByExerciseId(Long memberId, Long exerciseId) {
        List<History> historyList = historyRepository.findByMemberIdAndExerciseId(memberId, exerciseId);
        List<HistoryDetailDto> historyDetailDtoList = new ArrayList<>();

        for(History history : historyList) {

            if(!memberId.equals(history.getMemberId())) {
                throw new RuntimeException("no permission");
            }

            HistoryDetailDto dto = HistoryDetailDto.createHistory(history);
            historyDetailDtoList.add(dto);
        }

        return historyDetailDtoList;
    }

    // 회원에 대한 운동이력 조회
    public List<HistoryDetailDto> findByMemberId(Long memberId) {
        List<History> historyList =  historyRepository.findByMemberId(memberId);
        List<HistoryDetailDto> historyDetailDtoList = new ArrayList<>();

        for(History history : historyList) {

            if(!memberId.equals(history.getMemberId())) {
                throw new RuntimeException("no permission");
            }

            HistoryDetailDto dto = HistoryDetailDto.createHistory(history);
            historyDetailDtoList.add(dto);
        }

        return historyDetailDtoList;

    }

    public List<HistoryDetailDto> findByCategory(Long memberId, Category category) {
        List<Exercise> exerciseList = exerciseRepository.findByCategoryAndMemberId(category, memberId);
        List<Long> exerciseIdList = new ArrayList<>();
        for(Exercise exercise : exerciseList) {
            exerciseIdList.add(exercise.getId());
        }
        List<History> historyList = historyRepository.findByMemberIdAndExerciseIdIn(memberId, exerciseIdList);
        List<HistoryDetailDto> historyDetailDtoList = new ArrayList<>();

        for(History history : historyList) {

            if(!memberId.equals(history.getMemberId())) {
                throw new RuntimeException("no permission");
            }

            HistoryDetailDto dto = HistoryDetailDto.createHistory(history);
            historyDetailDtoList.add(dto);
        }

        return historyDetailDtoList;
    }

    public List<HistoryDetailDto> findByDate(Long memberId, LocalDateTime sdate, LocalDateTime edate) {
        List<History> historyList =  historyRepository.findByMemberIdAndEdateBetween(memberId, sdate, edate);

        List<HistoryDetailDto> historyDetailDtoList = new ArrayList<>();

        for(History history : historyList) {

            if(!memberId.equals(history.getMemberId())) {
                throw new RuntimeException("no permission");
            }

            HistoryDetailDto dto = HistoryDetailDto.createHistory(history);
            historyDetailDtoList.add(dto);
        }

        return historyDetailDtoList;
    }
}
