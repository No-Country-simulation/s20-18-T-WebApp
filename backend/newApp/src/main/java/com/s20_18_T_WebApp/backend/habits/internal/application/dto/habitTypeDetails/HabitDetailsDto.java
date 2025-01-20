package com.s20_18_T_WebApp.backend.habits.internal.application.dto.habitTypeDetails;

public sealed interface HabitDetailsDto
        permits BadHabitsTrackingDetailsDTO, FinancesDetailsDTO, HealthyLivingDetailsDTO, LearningDetailsDTO, OtherHabitDetailDTO, PhysicalActivityDetailsDTO, SocialActivityDetailsDTO {
}
