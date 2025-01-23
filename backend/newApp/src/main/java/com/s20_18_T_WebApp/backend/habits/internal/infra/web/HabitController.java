package com.s20_18_T_WebApp.backend.habits.internal.infra.web;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitCreationRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitResponseDto;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitUpdateRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.service.DailyProgressService;
import com.s20_18_T_WebApp.backend.habits.internal.application.service.HabitSearchService;
import com.s20_18_T_WebApp.backend.habits.internal.application.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;
    private final HabitSearchService habitSearchService;
    private final DailyProgressService dailyProgressService;

    @PostMapping
    public ResponseEntity<HabitResponseDto> createHabit (@RequestBody HabitCreationRequest request) {
        return ResponseEntity.ok(habitService.createHabit(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitResponseDto> getHabitById (@PathVariable Long id) {
        return ResponseEntity.ok(habitService.getHabitById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<HabitResponseDto> updateHabit(@PathVariable Long id, @RequestBody HabitUpdateRequest request) {
        return ResponseEntity.ok(habitService.updateHabit(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHabit(@PathVariable Long id) {
        return ResponseEntity.ok(habitService.deleteHabit(id));
    }
}
