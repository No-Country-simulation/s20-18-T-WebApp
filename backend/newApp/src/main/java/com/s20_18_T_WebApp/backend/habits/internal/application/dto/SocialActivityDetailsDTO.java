package com.s20_18_T_WebApp.backend.habits.internal.application.dto;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes.SocialActivityHabit;

public record SocialActivityDetailsDTO(
    Integer timeInMinutes
) implements HabitDetailsDto {
    public static SocialActivityDetailsDTO fromEntity(SocialActivityHabit habit) {
        return new SocialActivityDetailsDTO(
            habit.getTimeInMinutes()
        );
    }
}
