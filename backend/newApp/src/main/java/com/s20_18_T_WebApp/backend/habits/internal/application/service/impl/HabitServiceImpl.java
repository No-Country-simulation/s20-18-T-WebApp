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
    @Override
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

    /**
     * Retrieves a habit from the database by its id and converts it to a HabitResponseDto.
     *
     * @param id The id of the habit to retrieve.
     * @return A HabitResponseDto representing the retrieved habit.
     * @throws RuntimeException if no habit is found with the given id.
     */
    @Override
    public HabitResponseDto getHabitById(Long id) {
        // Retrieve the habit from the repository
        Habit searchedHabit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found with id " + id));

        // Convert the Habit entity to a HabitResponseDto
        return HabitResponseDto.fromEntity(searchedHabit);
    }

    //TODO getHabitByName
    public HabitResponseDto getHabitByName(String name) {

    }

    //TODO archiveHabit

    //TODO unarchiveHabit

    //TODO getHabitByType

    //TODO updateHabit

    /**
     * Deletes a habit from the database by its id.
     *
     * @param id The id of the habit to delete.
     */
    @Override
    public void deleteHabit(Long id) {
        // Delete the habit from the repository
        habitRepository.deleteById(id);
    }
}
