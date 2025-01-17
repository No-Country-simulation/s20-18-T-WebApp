package com.s20_18_T_WebApp.backend.habits.internal.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WeekDayProgress {

    private DayOfWeek day;

    private boolean isSelected;

    private boolean isCompleted;

    private boolean isPending;

    private boolean isFailed;

    public WeekDayProgress(DayOfWeek day) {
        this.day = day;
        this.isSelected = false;
        this.isCompleted = false;
        this.isPending = false;
        this.isFailed = false;
    }

    /**
     * Marks the day as pending if it is selected and not completed.
     */
    public void markAsPending() {
        // Check if the day is selected and not completed
        if (isSelected && !isCompleted) {
            // Mark the day as pending
            this.isPending = true;
        }
    }

    /**
     * Marks the day as completed if it is selected and pending.
     */
    public void markAsCompleted() {
        // Check if the day is selected
        if (isSelected && isPending) {
            // Mark the day as completed
            this.isCompleted = true;
            this.isPending = false;
        }
    }

    /**
     * Resets the day to its initial state.
     */
    public void reset() {
        this.isPending = false;
        this.isCompleted = false;
    }

    /**
     * Sets the selected flag of the day.
     *
     * @param selected true if the day is selected, false otherwise.
     */
    public void setSelected(boolean selected) {
        // Set the selected flag of the day
        this.isSelected = selected;

        // If the day is not selected, reset its state
        if (!selected) {
            reset();
        }
    }

    /**
     * Checks if the day is active.
     *
     * A day is considered active if it is selected and either pending or completed.
     *
     * @return true if the day is active, false otherwise.
     */
    public boolean isActive() {
        // Return true if the day is selected and either pending or completed
        return isSelected && (isPending || isCompleted);
    }

    /**
     * Marks the day as failed if it is pending.
     *
     * A day is considered failed if it is pending and has not been completed.
     * TODO agregar una falla si el dia ya a pasado y no ha sido marcado como completodo.
     */
    public void markAsFailed() {
        // Check if the day is pending
        if (isPending) {
            // Mark the day as failed
            this.isPending = false;
            this.isCompleted = false;
            this.isFailed = true;
        }
    }

    /**
     * Gets the status of the week day progress.
     *
     * The status can be one of the following:
     *   - Completed: if the day is completed.
     *   - Pending: if the day is pending.
     *   - Failed: if the day is failed.
     *   - Not selected: if the day is not selected.
     *   - Not started: if the day is selected but not started.
     *
     * @return the status of the week day progress.
     */
    public String getStatus() {
        if (isCompleted) {
            return "Completed";
        } else if (isPending) {
            return "Pending";
        } else if (isFailed) {
            return "Failed";
        } else if (!isSelected) {
            return "Not selected";
        } else {
            return "Not started";
        }
    }

    /**
     * Converts the WeekDayProgress object to a string representation.
     *
     * This includes the day and its current status.
     *
     * @return a string representation of the WeekDayProgress.
     */
    @Override
    public String toString() {
        // Create a string representation with the day and status
        return String.format("WeekDayProgress{day=%s, status=%s}", day, getStatus());
    }

}
