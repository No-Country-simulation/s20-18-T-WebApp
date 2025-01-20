package com.s20_18_T_WebApp.backend.habits.internal.application.service.impl;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitCreationRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.service.HabitService;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.factory.HabitFactory;
import com.s20_18_T_WebApp.backend.habits.internal.infra.persistence.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<Habit> getAllHabits() {
        //TODO implement DTO to return a list of all habits
        return habitRepository.findAll();
    }
}
