package com.ms.wms.controller;


import com.ms.wms.domain.Routine;
import com.ms.wms.dto.RoutineDto;
import com.ms.wms.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/routine")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @PostMapping("/save")
    public void saveRoutine(@RequestBody Routine routine) {
        routineService.saveRoutine(routine);
    }

    @PostMapping("/find")
    public Routine findRoutine(@RequestParam Long id) {
        return routineService.findRoutine(id);
    }

    @PostMapping("/remove")
    public void removeRoutine(@RequestBody Routine routine) {
        routineService.removeRoutine(routine);
    }


}
