package com.s20_18_T_WebApp.backend.habits.internal.application.dto.habitTypeDetails;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes.PhysicalActivityHabit;

public record PhysicalActivityDetailsDTO(
        Double distanceInKm,
        Integer timeInMinutes
) implements HabitDetailsDto {
    public static PhysicalActivityDetailsDTO fromEntity(PhysicalActivityHabit habit) {
        return new PhysicalActivityDetailsDTO(
                habit.getDisntanceInKm(),
                habit.getDurationInMinutes()
        );
    }
}
