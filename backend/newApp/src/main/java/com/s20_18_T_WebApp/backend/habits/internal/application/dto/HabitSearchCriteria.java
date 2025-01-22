package com.s20_18_T_WebApp.backend.habits.internal.application.dto;

import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public record HabitSearchCriteria(
        String name,
        HabitType type,
        Set<DayOfWeek> scheduleDays,
        Boolean archived,
        Integer minStreak,
        Integer maxStreak,
        LocalDate startDateFrom,
        LocalDate startDateTo,
        LocalDate endDateFrom,
        LocalDate endDateTo
) {
}
