package com.s20_18_T_WebApp.backend.habits.internal.application.dto;

import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record HabitResponseDto(
    Long id,
    String name,
    HabitType type,
    Set<DayOfWeek> scheduleDays,
    int currentStreak,
    int longestStreak,
    boolean archived,
    LocalDate endDate,
    String icon,
    String color,
    HabitDetailsDto details
) {
    public static HabitResponseDto fromEntity(Habit habit) {
        return new HabitResponseDto(
                habit.getId(),
                habit.getName(),
                habit.getType(),
                habit.getScheduleDays(),
                habit.getCurrentStreak(),
                habit.getLongestStreak(),
                habit.isArchived(),
                habit.getEndDate(),
                habit.getIcon(),
                habit.getColor(),
                HabitDetailsDto.fromEntity(habit)
        );
    }
}
