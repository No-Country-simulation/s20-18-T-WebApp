package com.s20_18_T_WebApp.backend.habits.internal.application.dto;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes.FinancesHabit;

public record FinancesDetailsDTO(
        Integer timeInMinutes
) implements HabitDetailsDto {
    public static FinancesDetailsDTO fromEntity(FinancesHabit habit) {
        return new FinancesDetailsDTO(
                habit.getTimeInMinutes()
        );
    }
}
