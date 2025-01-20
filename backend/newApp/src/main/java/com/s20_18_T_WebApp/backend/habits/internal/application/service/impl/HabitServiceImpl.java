package com.s20_18_T_WebApp.backend.habits.internal.application.service.impl;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitCreationRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitResponseDto;
import com.s20_18_T_WebApp.backend.habits.internal.application.service.HabitService;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.factory.HabitFactory;
import com.s20_18_T_WebApp.backend.habits.internal.infra.persistence.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;


    /**
     * Creates a new habit based on the given request and saves it to the database.
     *
     * @param request The request containing the information needed to create a new habit.
     * @return The created habit.
     */
    @Override
    public Habit createHabit(HabitCreationRequest request) {
        Habit habit = HabitFactory.createHabit(request);
        return habitRepository.save(habit);
    }

    /**
     * Retrieves all habits from the database and converts them to a list of HabitResponseDto.
     *
     * @return A list of HabitResponseDto representing all habits.
     */
    public List<HabitResponseDto> getAllHabits() {
        // Fetch all habits from the repository
        List<Habit> habits = habitRepository.findAll();

        // Convert each Habit entity to a HabitResponseDto
        List<HabitResponseDto> habitResponseDtos = new ArrayList<>();
        for (Habit habit : habits) {
            habitResponseDtos.add(HabitResponseDto.fromEntity(habit));
        }

        // Return the list of HabitResponseDto
        return habitResponseDtos;
    }
}
