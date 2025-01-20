package com.s20_18_T_WebApp.backend.habits.internal.application.dto;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes.HealthyLivingHabit;

public record HealthyLivingDetailsDTO(
        Integer calories,
        Integer timeInMinutes
) implements HabitDetailsDto {
    public static HealthyLivingDetailsDTO fromEntity(HealthyLivingHabit habit) {
        return new HealthyLivingDetailsDTO(
                habit.getCalories(),
                habit.getTimeInMinutes()
        );
    }
}
