package com.s20_18_T_WebApp.backend.habits.internal.application.service;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitCreationRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitResponseDto;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HabitService {

    /*
    TODO createNewHabit ,getAllHabts, getHabitDetailsById, getHabitByName, updateHabit, archiveHabit, unarchiveHabit, deleteHabit
     */
     Habit createHabit (HabitCreationRequest request);

    List<HabitResponseDto> getAllHabits();

    HabitResponseDto getHabitById(Long id);

    void archiveHabit(Long id);

    void unarchiveHabit(Long id);

    void deleteHabit(Long id);
}
