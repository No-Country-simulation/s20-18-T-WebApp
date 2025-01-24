package com.s20_18_T_WebApp.backend.habits.internal.application.dto.habitTypeDetails;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes.SocialActivityHabit;
import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.SocialActivityEnum;

public record SocialActivityDetailsDTO(
        double value,
        SocialActivityEnum units
) implements HabitDetailsDto {
    public static SocialActivityDetailsDTO fromEntity(SocialActivityHabit habit) {
        return new SocialActivityDetailsDTO(
                habit.getValue(),
                habit.getUnits()
        );
    }
}
