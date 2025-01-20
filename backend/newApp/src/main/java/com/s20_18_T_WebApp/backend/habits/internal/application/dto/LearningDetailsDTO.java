package com.s20_18_T_WebApp.backend.habits.internal.application.dto;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes.LearningHabit;

public record LearningDetailsDTO(
        Integer timeInMinutes
) implements HabitDetailsDto {
    public static LearningDetailsDTO fromEntity(LearningHabit habit) {
        return new LearningDetailsDTO(
                habit.getTimeInMinutes()
        );
    }
}
