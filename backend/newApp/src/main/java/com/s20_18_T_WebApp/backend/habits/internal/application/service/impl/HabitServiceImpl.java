package com.s20_18_T_WebApp.backend.habits.internal.application.service.impl;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitCreationRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitResponseDto;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitUpdateRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.service.HabitService;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;
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

    /**
     * Archives a habit in the database by its id.
     *
     * @param id The id of the habit to archive.
     * @throws RuntimeException if no habit is found with the given id.
     */
    @Override
    public void archiveHabit(Long id) {
        // Retrieve the habit from the repository
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found with id. " + id));

        // Archive the habit
        habit.archive();
    }

    /**
     * Unarchives a habit in the database by its id.
     *
     * @param id The id of the habit to unarchive.
     * @throws RuntimeException if no habit is found with the given id.
     */
    @Override
    public void unarchiveHabit(Long id) {
        // Retrieve the habit from the repository
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found with id. " + id));

        // Activate the habit (set archived status to false)
        habit.activate();
    }
    /**
     * Retrieves all habits of a given type from the database and converts them to a list of HabitResponseDto.
     *
     * @param type The type of habit to retrieve.
     * @return A list of HabitResponseDto representing all habits of the given type.
     */
    public List<HabitResponseDto> getHabitByType(HabitType type) {
        // Fetch all habits of the given type from the repository
        List<Habit> habits = habitRepository.findByType(type);

        // Convert each Habit entity to a HabitResponseDto
        List<HabitResponseDto> habitResponseDtos = new ArrayList<>();
        for (Habit habit : habits) {
            habitResponseDtos.add(HabitResponseDto.fromEntity(habit));
        }

        // Return the list of HabitResponseDto
        return habitResponseDtos;
    }

    /**
     * Updates a habit in the database by its id.
     *
     * @param id     The id of the habit to update.
     * @param request The request containing the information needed to update the habit.
     */
    @Override
    public void updateHabit(Long id, HabitUpdateRequest request) { //TODO fix setValue and setUnit
        // Retrieve the habit from the repository
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found with id " + id));

        // Update the habit with the given request
        habit.setName(request.name());
        habit.setScheduleDays(request.scheduleDays());
        habit.setEndDate(request.endDate());
        // Save the updated habit
        habitRepository.save(habit);
    }

    //TODO CALCULO DE PORCENTAJE

    //TODO devolver estatus por dia. (pendiente, completado, fallado)

    //TODO GETALLCATEGORIAS

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
