package com.s20_18_T_WebApp.backend.habits.internal.application.service.impl;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitCreationRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitResponseDto;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitTypeDTO;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitUpdateRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.service.HabitService;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.DailyProgress;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;
import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.ProgressStatus;
import com.s20_18_T_WebApp.backend.habits.internal.factory.HabitFactory;
import com.s20_18_T_WebApp.backend.habits.internal.infra.persistence.DailyProgressRepository;
import com.s20_18_T_WebApp.backend.habits.internal.infra.persistence.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final DailyProgressRepository dailyProgressRepository;

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

    /**
     * Marks missed scheduled days for a habit as failed.
     * <p>
     * This method iterates over all days from the habit's creation date up to the current date.
     * It checks if each day is a scheduled day for the habit. If a scheduled day does not have
     * a recorded daily progress, it creates a new failed daily progress record for that day.
     * </p>
     *
     * @param habitId The id of the habit for which to mark missed scheduled days.
     * @throws RuntimeException if no habit is found with the given id.
     */
    @Override
    public void markMissedScheduledDays(Long habitId) {
        // Retrieve the habit from the repository
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found with id " + habitId));

        // Get the scheduled days for the habit
        Set<DayOfWeek> scheduleDays = habit.getScheduleDays();

        // Define the start date as the habit's creation date and the end date as the current date
        LocalDate startDate = LocalDate.from(habit.getCreatedAt());
        LocalDate actualDate = LocalDate.now();

        // Iterate through each day from the start date to the current date
        for (LocalDate date = startDate; !date.isAfter(actualDate); date = date.plusDays(1)) {
            // Check if the current day is a scheduled day for the habit
            if (scheduleDays.contains(date.getDayOfWeek())) {
                // Check if there's already a daily progress record for the current day
                DailyProgress missedProgress = dailyProgressRepository.findByHabitAndDate(habit, date);
                if (missedProgress == null) {
                    // Create and save a new failed daily progress record for the current day
                    DailyProgress dailyProgress = new DailyProgress();
                    dailyProgress.setHabit(habit);
                    dailyProgress.setDate(date);
                    dailyProgress.setStatus(ProgressStatus.FAILED);
                    dailyProgressRepository.save(dailyProgress);
                }
            }
        }
    }

    /**
     * Calculates the current and longest streak for a given habit.
     * A streak is a sequence of completed daily progress records.
     * The current streak is the length of the current sequence of completed daily progress records.
     * The longest streak is the maximum length of any sequence of completed daily progress records
     * for the given habit.
     *
     * @param habitId The id of the habit for which to calculate the streaks.
     * @return An array of two integers, where the first element is the current streak and the second element is the longest streak.
     * @throws RuntimeException if no habit is found with the given id.
     */
    @Override
    public int[] calculateStreaks(Long habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found with id " + habitId));

        List<DailyProgress> progressList = dailyProgressRepository.findByHabitOrderByDateAsc(habit);

        int currentStreak = 0;
        int maxStreak = 0;
        int tempStreak = 0;

        for (int i = progressList.size() - 1; i >= 0; i--) {
            DailyProgress progress = progressList.get(i);
            if (progress.getStatus() == ProgressStatus.COMPLETED) {
                tempStreak++;
                currentStreak = tempStreak;
                maxStreak = Math.max(maxStreak, tempStreak);
            } else {
                tempStreak = 0;
            }
        }
        return new int[]{currentStreak, maxStreak};
    }

    @Override
    public Stream<HabitTypeDTO> getAllHabitTypes() {
        return Arrays.stream(HabitType.values())
                .map(habitType -> new HabitTypeDTO(habitType.toString(), habitType.getTranslation()));
    }

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
