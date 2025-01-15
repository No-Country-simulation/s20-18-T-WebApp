package com.s20_18_T_WebApp.backend.habits.internal.domain.entity;

import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;
import com.s20_18_T_WebApp.backend.habits.internal.domain.vo.WeekDayProgress;
import com.s20_18_T_WebApp.backend.shared.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "habits")
@Inheritance(strategy = InheritanceType.JOINED)
public class Habit extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;//TODO default name segun tipo de habito.

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;//A menos que se especifique se considera para siempre.

    @Column(nullable = false)
    private boolean isArchived = false;

    @Column(nullable = false)
    private Integer streakDays;//dias de racha

    @Column(nullable = false, length = 100)
    private Double progressPercentage;//porcentaje de seguimiento TODO como calcularlo.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HabitType type;

    @ElementCollection
    private Set<DayOfWeek> scheduleDays;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<DailyProgress> dailyProgress;

    public Habit (String name, LocalDate startDate, HabitType type, Set<DayOfWeek> scheduleDays) {
        this.name = name;
        this.startDate = startDate;
        this.type = type;
        this.scheduleDays = new HashSet<>(scheduleDays);
        this.isArchived = false;
        this.streakDays = 0;
        this.progressPercentage = 0.0;
        this.dailyProgress = new ArrayList<>();
    }

    /**
     * Checks if the given date is scheduled for the habit.
     *
     * @param date the date to check
     * @return true if the date is scheduled, false otherwise
     */
    public boolean isDayScheduled(LocalDate date) {
        // Check if the day of the given date is in the schedule days
        return scheduleDays.contains(date.getDayOfWeek());
    }

    public void addDailyProgress (LocalDate date, boolean completed) {
        DailyProgress progress = new DailyProgress(date, completed, isDayScheduled(date));
        dailyProgress.add(progress);
        updateStatistics();
    }

    public void updateStatistics() {
        updateProgressPercentage();
        updateStreakeDays();
    }

    private void updateProgressPercentage() {
        long totalScheduleDays = dailyProgress.stream()
                .filter(DailyProgress::isScheduled)
                .count();

        if (totalScheduleDays == 0) {
            this.progressPercentage = 0.0;
            return;
        }

        long completedDays = dailyProgress.stream()
                .filter(p -> p.isScheduled() && p.isCompleted())
                .count();

        this.progressPercentage = (completedDays * 100.0) / totalScheduleDays;
    }

    public boolean shouldBeArchived() {
        return !isArchived && getUpdatedAt().plusDays(30).isBefore(LocalDateTime.now());
    }

    public void archive() {
        this.isArchived = true;
    }

    public void activate() {
        this.isArchived = false;
    }

    public void updateProgress() {
        if (weekDays == null || weekDays.isEmpty()) {
            this.progressPercentage = 0.0;
            return;
        }

        long completedDays = weekDays.stream()
                .filter(WeekDayProgress::isCompleted)
                .count();

        long selectedDays = weekDays.stream()
                .filter(WeekDayProgress::isSelected)
                .count();

        this.progressPercentage = selectedDays > 0 ? (completedDays * 100.0 /selectedDays) : 0.0;
    }

    /**
     * Updates the streak days for the habit by counting the number of completed days in the week.
     * This is used to keep track of the user's streak of completing the habit.
     */
    public void updateStreakeDays() {
        // Count the number of completed days in the week
        long completedDays = weekDays.stream()
                .filter(WeekDayProgress::isCompleted)
                .count();

        // Update the streak days with the number of completed days
        this.streakDays = (int) completedDays;
    }
}
