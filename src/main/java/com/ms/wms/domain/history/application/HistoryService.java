package com.ms.wms.domain.history.application;

import com.ms.wms.domain.exercise.domain.Exercise;
import com.ms.wms.domain.exercise.domain.ExerciseRepository;
import com.ms.wms.domain.history.controller.dto.*;
import com.ms.wms.domain.history.domain.History;
import com.ms.wms.domain.history.domain.HistoryRepository;
import com.ms.wms.global.config.exception.business.NoExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                    .orElseThrow(() -> new NoExistException(("존재하지 않는 exercise 입니다.")));

            historyList.add(History.createHistory(memberId, exercise, dto));
        }

        historyRepository.saveAll(historyList);
    }

    public HistoryDetailDto findByDate(Long memberId, FindHistoryDto findHistoryDto) {

        List<History> historyList = historyRepository.findByMemberIdAndEndDateBetween(memberId, findHistoryDto.getStartDate(), findHistoryDto.getEndDate());
        HistoryAggregateInfo historyAggregateInfo =  historyRepository.findHistoryAggregateInfo(memberId, findHistoryDto.getStartDate(), findHistoryDto.getEndDate());
        HistoryDetailByDateDto historyDetailByDateDto = historyRepository.findHistoryRepository(memberId, findHistoryDto.getStartDate(), findHistoryDto.getEndDate());



        HistoryDetailDto dto = HistoryDetailDto.createHistory(historyList, historyAggregateInfo, historyDetailByDateDto);


        return dto;
    }

}
