package com.s20_18_T_WebApp.backend.habits.internal.application.dto;

import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;


public record HabitCreationRequest (

        String name,
        HabitType type,//TODO CATEGORY ID??
        Set<DayOfWeek> scheduleDays,//TODO: VA A LLEGAR COMO UN ARRAY [0, 1, 3]
        LocalDate endDate,
        Double distanceInKm,
        Integer timeInMinutes,
        Integer calories,
        Integer daysAvoided,
        String customNotes
) {


}
