package com.s20_18_T_WebApp.backend.habits.internal.infra.web;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitCreationRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitResponseDto;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitSearchCriteria;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitUpdateRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.service.DailyProgressService;
import com.s20_18_T_WebApp.backend.habits.internal.application.service.HabitSearchService;
import com.s20_18_T_WebApp.backend.habits.internal.application.service.HabitService;
import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;
import com.s20_18_T_WebApp.backend.shared.application.dto.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api/habits")
//@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;
    private final HabitSearchService habitSearchService;
    private final DailyProgressService dailyProgressService;

    public HabitController(HabitService habitService, HabitSearchService habitSearchService, DailyProgressService dailyProgressService) {
        this.habitService = habitService;
        this.habitSearchService = habitSearchService;
        this.dailyProgressService = dailyProgressService;
    }

    @PostMapping
    public ResponseEntity<HabitResponseDto> createHabit (@RequestBody HabitCreationRequest request) {
        return ResponseEntity.ok(habitService.createHabit(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitResponseDto> getHabitById (@PathVariable("id") Long id) {
        return ResponseEntity.ok(habitService.getHabitById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HabitResponseDto> updateHabit(@PathVariable("id") Long id, @RequestBody HabitUpdateRequest request) {
        return ResponseEntity.ok(habitService.updateHabit(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHabit(@PathVariable("id") Long id) {
        return ResponseEntity.ok(habitService.deleteHabit(id));
    }

    @PostMapping("/mark-missed-days/{habitId}")
    public ResponseEntity<String> markMissedScheduledDays(@PathVariable("habitId") Long habitId) {
        habitService.markMissedScheduledDays(habitId);
        return ResponseEntity.ok("Missed days marked for habit with id " + habitId);
    }

    @GetMapping("/streaks/{habitId}")
    public ResponseEntity<int[]> getStreaks(@PathVariable("habitId") Long habitId) {
        int[] streaks = habitService.calculateStreaks(habitId);
        return ResponseEntity.ok(streaks);
    }

    @GetMapping("/completion/{habitId}")
    public ResponseEntity<Double> getcompletionPercentage(@PathVariable Long habitId) {
        double percentage = dailyProgressService.calculateCompletionPercentage(habitId);
        return ResponseEntity.ok(percentage);
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<HabitResponseDto>> habitSearch(
            @RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false, value = "type") String type,
            @RequestParam(required = false, value = "scheduleDays") Set<DayOfWeek> scheduleDays,
            @RequestParam(required = false, value = "archived") Boolean archived,
            @RequestParam(required = false, value = "minStreak") Integer minStreak,
            @RequestParam(required = false, value = "maxStreak") Integer maxStreak,
            @RequestParam(required = false, value = "startDateFrom") LocalDate startDateFrom,
            @RequestParam(required = false, value = "startDateTo") LocalDate startDateTo,
            @RequestParam(required = false, value = "endDateFrom") LocalDate endDateFrom,
            @RequestParam(required = false, value = "endDateTo") LocalDate endDateTo,
            Pageable pageable) {

        try {
            // Validar los argumentos de entrada
            if (name != null && name.trim().isEmpty()) {
                throw new IllegalArgumentException("name must not be empty");
            }

            if (type != null && !HabitType.isValid(type)) {
                throw new IllegalArgumentException("type is not valid");
            }

            if (scheduleDays != null && scheduleDays.isEmpty()) {
                throw new IllegalArgumentException("scheduleDays must not be empty");
            }

            if (startDateFrom != null && startDateTo != null && startDateFrom.isAfter(startDateTo)) {
                throw new IllegalArgumentException("startDateFrom must not be after startDateTo");
            }

            if (endDateFrom != null && endDateTo != null && endDateFrom.isAfter(endDateTo)) {
                throw new IllegalArgumentException("endDateFrom must not be after endDateTo");
            }

            // Crear el objeto para la busqueda
            HabitSearchCriteria habitSearchCriteria = new HabitSearchCriteria(name, HabitType.fromString(type), scheduleDays, archived, minStreak, maxStreak, startDateFrom, startDateTo, endDateFrom, endDateTo);

            return ResponseEntity.ok(habitSearchService.pageSearch(habitSearchCriteria, pageable));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{habitId}/archive")
    public ResponseEntity<String> archiveHabit(@PathVariable("habitId") Long habitId) {
        habitService.archiveHabit(habitId);
        return ResponseEntity.ok("Habit archived successfully");
    }

    @PostMapping("/{habitId}/unarchive")
    public ResponseEntity<String> unarchiveHabit(@PathVariable("habitId") Long habitId) {
        habitService.unarchiveHabit(habitId);
        return ResponseEntity.ok("Habit unarchived successfully");
    }
}
