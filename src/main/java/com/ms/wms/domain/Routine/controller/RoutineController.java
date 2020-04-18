package com.ms.wms.domain.Routine.controller;


import com.ms.wms.domain.Routine.application.RoutineService;
import com.ms.wms.domain.Routine.controller.dto.FindRoutineDetailDto;
import com.ms.wms.domain.Routine.controller.dto.SaveRoutineDto;
import com.ms.wms.domain.Routine.controller.dto.UpdateRoutineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/routine")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @PostMapping("/save")
    public void saveRoutine(@RequestBody SaveRoutineDto saveRoutineDto) {
        routineService.saveRoutine(saveRoutineDto);
    }

    @PostMapping("/update")
    public void updateRoutine(@RequestBody UpdateRoutineDto updateRoutineDto) {
        routineService.updateRoutine(updateRoutineDto);
    }

    @GetMapping("/find/{routineId}")
    public FindRoutineDetailDto findRoutine(@PathVariable Long routineId) {
        return routineService.findRoutine(routineId);
    }

    @PostMapping("/{routineId}")
    public void removeRoutine(@PathVariable Long routineId) {
        routineService.removeRoutine(routineId);
    }
}
