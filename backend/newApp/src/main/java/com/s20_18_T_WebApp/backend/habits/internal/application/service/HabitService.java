package com.s20_18_T_WebApp.backend.habits.internal.application.service;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitCreationRequest;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import org.springframework.http.ResponseEntity;

public interface HabitService {

    /*
    TODO createNewHabit ,getAllHabts, getHabitDetailsById, getHabitByName, updateHabit, archiveHabit, unarchiveHabit, deleteHabit
     */
     Habit createHabit (HabitCreationRequest request);
}
