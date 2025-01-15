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

    @Column(name = "name", nullable = false)
    private String name;//TODO default name segun tipo de habito.

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;//A menos que se especifique se considera para siempre.

    @Column(nullable = false)
    private boolean isArchived;

    @Column(nullable = false)
    private int longestStreak;//dias de racha mas larga

    @Column(nullable = false)
    private int currentStreak;

    @Column(nullable = false)
    private Double progressPercentage;//porcentaje de seguimiento TODO como calcularlo.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HabitType type;

    @ElementCollection
    @CollectionTable(name = "habit_schedule_days", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "day_of_week")
    private Set<DayOfWeek> scheduleDays;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "habit_id")
    @OrderBy("date DESC")
    private List<DailyProgress> dailyProgress;

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
        this.isArchived = false;
        this.currentStreak = 0;
        this.longestStreak = 0;
        this.progressPercentage = 0.0;
        this.dailyProgress = new ArrayList<>();
    }

    /**
     * Checks if the given date is scheduled for the habit.
     *
     * This method checks if the given date is within the habit's start and end dates and if the day of the week is
     * scheduled for the habit.
     *
     * @param date the date to check
     * @return true if the date is scheduled, false otherwise
     */
    public boolean isDayScheduled(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        // Check if the day of the week is scheduled for the habit
        boolean isScheduledDay = scheduleDays.contains(date.getDayOfWeek());

        // Check if the date is within the habit's start and end dates
        boolean isWithinDates = !date.isBefore(startDate) &&
                (endDate == null || !date.isAfter(endDate));

        // Return true if the date is scheduled and within the habit's start and end dates
        return isScheduledDay && isWithinDates;
    }

    /**
     * Finds the daily progress for the given date.
     *
     * @param date the date for which to find the daily progress
     * @return an optional containing the daily progress if found, empty otherwise
     */
    public Optional<DailyProgress> getProgressForDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }

        // Find the daily progress for the given date, if any
        return dailyProgress.stream()
                .filter(progress -> progress.getDate().equals(date))
                .findFirst();
    }

    /**
     * Adds a new daily progress entry for the specified date.
     *
     * This method ensures that a daily progress entry is created for a date only if:
     * 1. The date is not null.
     * 2. No existing progress entry is present for the date.
     * 3. The date is scheduled for the habit.
     *
     * @param date the date for which to add the daily progress
     * @param completed whether the habit was completed on the specified date
     * @throws IllegalArgumentException if the date is null, if daily progress for the date already exists,
     *                                  or if the date is not scheduled for the habit
     */
    public void addDailyProgress(LocalDate date, boolean completed) {
        // Validate that the provided date is not null
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        // Ensure no existing daily progress for the specified date
        //if (getProgressForDate(date).isPresent()) {
        //    throw new IllegalArgumentException("Daily progress for date " + date + " already exists.");
        //}

        // Check if the date is within the scheduled days for this habit
        boolean scheduled = isDayScheduled(date);
        if (!scheduled) {
            throw new IllegalArgumentException("Day " + date + " is not scheduled for this habit.");
        }

        // Create a new DailyProgress entry with the given date, completion status, and scheduled flag
        DailyProgress progress = new DailyProgress(date, completed, scheduled);

        // Add the new progress entry to the daily progress list
        dailyProgress.add(progress);

        // Update the habit's statistics, such as progress percentage and streak days
        updateStatistics();
    }

    /**
     * Updates the completion status of the daily progress for the given date.
     *
     * This method retrieves the existing daily progress for the specified date
     * and updates its completion status. It also updates the habit's statistics
     * after modifying the progress.
     *
     * @param date the date for which to update the daily progress
     * @param completed the new completion status to set
     * @throws IllegalArgumentException if no daily progress is found for the date
     */
    public void updateProgress(LocalDate date, boolean completed) {
        // Retrieve the existing daily progress for the given date
        DailyProgress progress = getProgressForDate(date)
                .orElseThrow(() -> new IllegalArgumentException("No daily progress found for date: " + date));

        // Update the completion status of the daily progress
        progress.setCompleted(completed);

        // Update the habit's statistics, such as progress percentage and streak days
        updateStatistics();
    }

    /**
     * Updates the habit's statistics, such as progress percentage and streak days.
     * <p>
     * This method should be called after modifying the daily progress.
     */
    public void updateStatistics() {
        // Update the progress percentage
        updateProgressPercentage();

        // Update the streak days
        updateStreakDays();
    }

    /**
     * Updates the habit's progress percentage.
     * <p>
     * This method counts the total scheduled days and the number of completed days,
     * and calculates the progress percentage based on the number of completed days
     * divided by the total scheduled days.
     */
    private void updateProgressPercentage() {
        // Count the total number of scheduled days for the habit
        long totalScheduleDays = dailyProgress.stream()
                .filter(DailyProgress::isScheduled)
                .count();

        // If no scheduled days, set the progress percentage to 0
        if (totalScheduleDays == 0) {
            this.progressPercentage = 0.0;
            return;
        }

        // Count the number of completed days for the habit
        long completedDays = dailyProgress.stream()
                .filter(p -> p.isScheduled() && p.isCompleted())
                .count();

        // Calculate the progress percentage
        long currentDay = LocalDate.now().toEpochDay();
        long habitStartDay = this.getCreatedAt().toLocalDate().toEpochDay();
        long totalDays = currentDay - habitStartDay;
        this.progressPercentage = (completedDays * 100.0 / totalDays);
    }

    /**
     * Updates the habit's longest streak and current streak.
     * <p>
     * This method uses two passes to count the longest streak and the current streak.
     * The first pass counts the current streak, starting from the current date and
     * counting backward. The second pass counts the longest streak by iterating
     * over all the daily progress entries and counting the longest streak.
     */
    private void updateStreakDays() {
        int currentStreak = 0;
        int maxStreak = 0;
        LocalDate currenDate = LocalDate.now();

        // Sort the daily progress entries by date in descending order
        List<DailyProgress> sortedProgress = dailyProgress.stream()
                .sorted(Comparator.comparing(DailyProgress::getDate).reversed())
                .toList();

        // First pass: count the current streak
        for (DailyProgress progress : sortedProgress) {
            if (progress.isScheduled() && progress.isCompleted()) {
                // If the daily progress is completed, increment the current streak
                currentStreak++;
            } else if (progress.isScheduled()) {
                // If the daily progress is scheduled but not completed, break
                break;
            }
        }

        // Second pass: count the longest streak
        int tempStreak = 0;
        for (DailyProgress progress : sortedProgress) {
            if (progress.isScheduled() && progress.isCompleted()) {
                // If the daily progress is completed, increment the temporary streak
                tempStreak++;
                // Update the longest streak if the temporary streak is longer
                maxStreak = Math.max(maxStreak, tempStreak);
            } else if (progress.isScheduled()) {
                // If the daily progress is scheduled but not completed, reset the temporary streak
                tempStreak = 0;
            }
        }

        // Update the habit's longest streak and current streak
        this.currentStreak = currentStreak;
        this.longestStreak = maxStreak;
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
        return !isArchived && getUpdatedAt().plusDays(30).isBefore(LocalDateTime.now());
    }

    /**
     * Archives the habit.
     *
     * This method sets the habit's archived status to true, indicating that the habit
     * is no longer active and should be considered archived.
     */
    public void archive() {
        // Set the habit's archived status to true
        this.isArchived = true;
    }

    /**
     * Activates the habit.
     *
     * This method sets the habit's archived status to false, indicating that the habit
     * is active and should be considered as such.
     */
    public void activate() {
        // Set the habit's archived status to false
        this.isArchived = false;
    }
}
