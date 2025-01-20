package com.s20_18_T_WebApp.backend.habits.internal.application.dto;

public sealed interface HabitDetailsDto
        permits HealthyLivingDetailsDTO, PhysicalActivityDetailsDTO {
}
