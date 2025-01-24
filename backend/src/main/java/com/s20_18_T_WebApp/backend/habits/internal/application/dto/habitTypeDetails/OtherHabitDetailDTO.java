package com.s20_18_T_WebApp.backend.habits.internal.application.dto.habitTypeDetails;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes.OtherHabit;

public record OtherHabitDetailDTO(
    String customNotes
) implements HabitDetailsDto {
    public static OtherHabitDetailDTO fromEntity(OtherHabit habit) {
        return new OtherHabitDetailDTO(
                habit.getCustomNotes()
        );
    }
}
