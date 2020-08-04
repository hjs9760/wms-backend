package com.ms.wms.domain.history.controller;

import com.ms.wms.domain.history.application.HistoryService;
import com.ms.wms.domain.history.controller.dto.FindHistoryDto;
import com.ms.wms.domain.history.controller.dto.HistoryDetailDto;
import com.ms.wms.domain.history.controller.dto.SaveHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping("/save")
    public void saveHistory(@AuthenticationPrincipal Long memberId, @RequestBody List<SaveHistoryDto> historyDtoList) {
        historyService.saveHistory(memberId, historyDtoList);
    }


    // 기간에 대한 운동 이력 조회
    @PostMapping("/findByDate")
    public HistoryDetailDto findByDate(@AuthenticationPrincipal Long memberId, @RequestBody FindHistoryDto findHistoryDto){
        return historyService.findByDate(memberId, findHistoryDto);
    }

}
