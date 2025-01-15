package com.s20_18_T_WebApp.backend.habits.internal.domain.entity;

import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;
import com.s20_18_T_WebApp.backend.shared.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "habits")
public class Habit extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;//TODO default name segun tipo de habito.

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private HabitType type;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "habit_schedule_days", joinColumns = @JoinColumn(name = "habit_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private Set<DayOfWeek> scheduleDays = new HashSet<>();

    @Column(name = "current_streak", nullable = false)
    private int currentStreak;

    @Column(name = "longest_streak", nullable = false)
    private int longestStreak;//dias de racha mas larga

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DailyProgress> dailyProgress = new HashSet<>();

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;//A menos que se especifique se considera para siempre.

    @Column(nullable = false)
    private boolean archived = false;


    @Column(nullable = false)
    private Double progressPercentage;


    public Habit (String name, HabitType type, Set<DayOfWeek> scheduleDays) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Habit name cannot be null or empty.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Habit type cannot be null.");
        }
        if (scheduleDays == null || scheduleDays.isEmpty()) {
            throw new IllegalArgumentException("Habit schedule days cannot be null or empty.");
        }

        this.name = name;
        this.type = type;
        this.startDate = LocalDate.now();
        this.scheduleDays = new HashSet<>(scheduleDays);
        this.archived = false;
        this.currentStreak = 0;
        this.longestStreak = 0;
        this.progressPercentage = 0.0;
    }

    /**
     * Updates the streak days based on the daily progress data.
     */
    public void updateStreakDays(Set<DailyProgress> progressData) {
        if (progressData == null || progressData.isEmpty()) {
            this.currentStreak = 0;
            return;
        }

        int current = 0;
        int longest = 0;
        boolean isConsecutive = true;

        // Example streak calculation logic
        for (DailyProgress progress : progressData) {
            if (progress.isCompleted()) {
                if (isConsecutive) {
                    current++;
                } else {
                    current = 1;
                    isConsecutive = true;
                }
                longest = Math.max(longest, current);
            } else {
                isConsecutive = false;
            }
        }

        this.currentStreak = current;
        this.longestStreak = longest;
    }

    /**
     * Checks if the habit should be archived.
     * <p>
     * A habit should be archived if it is not archived, and the last update date is 30 days ago or more.
     *
     * @return true if the habit should be archived, false otherwise
     */
    public boolean shouldBeArchived() {
        // Check if the habit is not archived and the last update date is 30 days ago or more
        return !archived && getUpdatedAt().plusDays(30).isBefore(LocalDateTime.now());
    }

    /**
     * Archives the habit.
     *
     * This method sets the habit's archived status to true, indicating that the habit
     * is no longer active and should be considered archived.
     */
    public void archive() {
        // Set the habit's archived status to true
        this.archived = true;
    }

    /**
     * Activates the habit.
     *
     * This method sets the habit's archived status to false, indicating that the habit
     * is active and should be considered as such.
     */
    public void activate() {
        // Set the habit's archived status to false
        this.archived = false;
    }
}
