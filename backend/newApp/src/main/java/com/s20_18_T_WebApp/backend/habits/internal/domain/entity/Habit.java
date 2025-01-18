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
@Inheritance(strategy = InheritanceType.JOINED)//Crea una tabla base para la entidad habit y las subclases heredan de ella
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Habit extends BaseEntity {

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

    @Column(nullable = false)
    private boolean archived = false;

    @Column(name = "end_date")
    private LocalDate endDate;//A menos que se especifique se considera para siempre.

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "icon", nullable = false)
    private String icon;


    public Habit (String name, HabitType type, Set<DayOfWeek> scheduleDays, String icon, String color) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Habit name cannot be null or empty.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Habit type cannot be null.");
        }
        if (scheduleDays == null || scheduleDays.isEmpty()) {
            throw new IllegalArgumentException("Habit schedule days cannot be null or empty.");
        }
        if (icon == null || icon.trim().isEmpty()) {
            throw new IllegalArgumentException("Habit icon cannot be null or empty.");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Habit color cannot be null or empty.");
        }

        this.name = name;
        this.type = type;
        this.scheduleDays = new HashSet<>(scheduleDays);
        this.archived = false;
        this.currentStreak = 0;
        this.longestStreak = 0;
        this.icon = icon;
        this.color = color;
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

/*
Consulta SQL para traer toda la informacion de los habitos.
SELECT h.*, pah.distance_in_km, pah.time_in_minutes, hlh.calories, hlh.time_in_minutes,
       bht.days_avoided, lh.topics, sah.time_in_minutes, oh.custom_notes
FROM habit h
LEFT JOIN physical_activity_habit pah ON h.id = pah.id
LEFT JOIN healthy_living_habit hlh ON h.id = hlh.id
LEFT JOIN bad_habits_tracking bht ON h.id = bht.id
LEFT JOIN learning_habit lh ON h.id = lh.id
LEFT JOIN social_activity_habit sah ON h.id = sah.id
LEFT JOIN other_habit oh ON h.id = oh.id;
 */