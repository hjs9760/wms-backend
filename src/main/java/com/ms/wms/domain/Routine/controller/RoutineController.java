package com.ms.wms.domain.Routine.controller;


import com.ms.wms.domain.Routine.application.RoutineService;
import com.ms.wms.domain.Routine.controller.dto.FindRoutineDetailDto;
import com.ms.wms.domain.Routine.controller.dto.SaveRoutineDto;
import com.ms.wms.domain.Routine.controller.dto.UpdateRoutineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/routine")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @PostMapping("/save")
    public void saveRoutine(@AuthenticationPrincipal Long memberId, @RequestBody SaveRoutineDto saveRoutineDto) {
        routineService.saveRoutine(memberId, saveRoutineDto);
    }

    @PostMapping("/update")
    public void updateRoutine(@AuthenticationPrincipal Long memberId, @RequestBody UpdateRoutineDto updateRoutineDto) {
        routineService.updateRoutine(memberId, updateRoutineDto);
    }

    @GetMapping("/find/{routineId}")
    public FindRoutineDetailDto findRoutine(@AuthenticationPrincipal Long memberId, @PathVariable Long routineId) {
        return routineService.findRoutine(memberId, routineId);
    }

    @PostMapping("/{routineId}")
    public void removeRoutine(@AuthenticationPrincipal Long memberId, @PathVariable Long routineId) {
        routineService.removeRoutine(memberId, routineId);
    }
}
