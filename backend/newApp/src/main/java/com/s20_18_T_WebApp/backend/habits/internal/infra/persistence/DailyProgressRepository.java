package com.s20_18_T_WebApp.backend.habits.internal.infra.persistence;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.DailyProgress;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.ProgressStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyProgressRepository extends JpaRepository<DailyProgress, Long> {
    Page<DailyProgress> findByHabit (Habit habit, Pageable pageable);

    Page<DailyProgress> findByHabitIdAndDateBetween(Long habitId, LocalDate startDate, Pageable pageable);

    long countByHabitAndStatus(Habit habit, ProgressStatus status);

    long countByHabit (Habit habit);

    DailyProgress findByHabitAndDate(Habit habit, LocalDate date);
}
