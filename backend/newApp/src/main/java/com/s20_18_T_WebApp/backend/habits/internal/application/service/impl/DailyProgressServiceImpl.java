package com.s20_18_T_WebApp.backend.habits.internal.application.service.impl;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.DailyProgress;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.ProgressStatus;
import com.s20_18_T_WebApp.backend.habits.internal.infra.persistence.DailyProgressRepository;
import com.s20_18_T_WebApp.backend.habits.internal.infra.persistence.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyProgressServiceImpl {

    private final DailyProgressRepository dailyProgressRepository;
    private final HabitRepository habitRepository;

    /**
     * Records the daily progress of a habit by the given habit id and date.
     * <p>
     * The method first retrieves the habit from the database by the given id.
     * Then it checks if the given date is null or if it is after the current date.
     * If so, it throws an {@link IllegalArgumentException}.
     * </p>
     * <p>
     * Finally, it creates a new {@link DailyProgress} object with the habit,
     * date and status (scheduled or completed), and saves it to the database.
     * </p>
     *
     * @param habitId  the id of the habit.
     * @param date     the date to record the daily progress.
     * @param completed true if the habit is completed, false otherwise.
     * @throws IllegalArgumentException if the habit is not found, or if the date is null or after the current date.
     */
    public void recordDailyProgress(Long habitId, LocalDate date, boolean completed) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found with id " + habitId));

        if (date == null || date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid date provided.");
        }

        DailyProgress dailyProgress = new DailyProgress();
        dailyProgress.setHabit(habit);
        dailyProgress.setDate(date);
        dailyProgress.setStatus(completed ? ProgressStatus.COMPLETED : ProgressStatus.SCHEDULED);

        dailyProgressRepository.save(dailyProgress);
    }

    //todo ver como hacer la comprobacion automatica para poner el habito como fallido igaulemente si no se ha completado y el dia ya a concluido.

    public double calculateCompletionPercentage(Long habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found with id " + habitId));

        if (habit.getScheduleDays().isEmpty()) {
            throw new IllegalArgumentException("Habit schedule days cannot be empty.");
        }

        long totalDays = dailyProgressRepository.countByHabit(habit);
        long completedDays = dailyProgressRepository.countByHabitAndStatus(habit, ProgressStatus.COMPLETED);

        return (double) completedDays / totalDays * 100;
    }

}
