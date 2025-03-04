package com.s20_18_T_WebApp.backend.habits.internal.application.dto.habitTypeDetails;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes.BadHabitsTracking;

public record BadHabitsTrackingDetailsDTO(
        String comments
) implements HabitDetailsDto {

    public static BadHabitsTrackingDetailsDTO fromEntity(BadHabitsTracking habit) {
        return new BadHabitsTrackingDetailsDTO(
                habit.getComment()
        );
    }
}
