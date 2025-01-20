package com.s20_18_T_WebApp.backend.habits.internal.factory;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitCreationRequest;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes.*;

public class HabitFactory {

    /**
     * Creates a habit based on the type specified in the request.
     *
     * @param request The habit creation request containing the information needed to create a new habit.
     * @return A Habit object of the appropriate type.
     */
    public static Habit createhabit(HabitCreationRequest request) {
        // Determine the type of habit and create the corresponding habit object
        return switch (request.type()) {
            case PHYSICAL_ACTIVITY -> createPhysicalActivityHabit(request); // Create a Physical Activity Habit
            case HEALTHY_LIVING -> createHealthyLivingHabit(request); // Create a Healthy Living Habit
            case BAD_HABITS -> createBadHabitsTracking(request); // Create a Bad Habits Tracking Habit
            case LEARNING -> createLearningHabit(request); // Create a Learning Habit
            case SOCIAL_ACTIVITY -> createSocialActivityHabit(request); // Create a Social Activity Habit
            case FINANCES -> createFinancesHabit(request); // Create a Finances Habit
            case OTHER -> createOtherHabit(request); // Create an Other type of Habit
        };
    }

    /**
     * Creates a Physical Activity Habit based on the information in the request.
     *
     * @param request The habit creation request containing the information needed to create a new habit.
     * @return A PhysicalActivityHabit object with the specified information.
     */
    private static PhysicalActivityHabit createPhysicalActivityHabit(HabitCreationRequest request) {
        return new PhysicalActivityHabit(
                request.name(),
                request.type(),
                request.scheduleDays(),
                request.endDate(),
                request.distanceInKm(),
                request.timeInMinutes()
        );
    }

    /**
     * Creates a Healthy Living Habit based on the information in the request.
     *
     * @param request The habit creation request containing the information needed to create a new habit.
     * @return A HealthyLivingHabit object with the specified information.
     */
    private static HealthyLivingHabit createHealthyLivingHabit(HabitCreationRequest request) {
        // Create and return a new HealthyLivingHabit instance
        return new HealthyLivingHabit(
                request.name(), // Name of the habit
                request.type(), // Type of the habit
                request.scheduleDays(), // Scheduled days for the habit
                request.endDate(), // End date for the habit
                request.calories(), // Calories associated with the habit
                request.timeInMinutes() // Time in minutes for the habit
        );
    }

    /**
     * Creates a Bad Habits Tracking Habit based on the information in the request.
     *
     * @param request The habit creation request containing the information needed to create a new habit.
     * @return A BadHabitsTracking object with the specified information.
     */
    private static BadHabitsTracking createBadHabitsTracking(HabitCreationRequest request) {
        return new BadHabitsTracking(
                request.name(),
                request.type(),
                request.scheduleDays(),
                request.endDate(),
                request.daysAvoided()
        );
    }

    /**
     * Creates a Learning Habit based on the information in the request.
     *
     * @param request The habit creation request containing the information needed to create a new habit.
     * @return A LearningHabit object with the specified information.
     */
    private static LearningHabit createLearningHabit(HabitCreationRequest request) {
        // Create and return a new LearningHabit instance
        return new LearningHabit(
                request.name(), // Name of the habit
                request.type(), // Type of the habit
                request.scheduleDays(), // Scheduled days for the habit
                request.endDate(), // End date for the habit
                request.timeInMinutes() // Time in minutes for the habit
        );
    }

    /**
     * Creates a Social Activity Habit based on the information in the request.
     *
     * @param request The habit creation request containing the information needed to create a new habit.
     * @return A SocialActivityHabit object with the specified information.
     */
    private static SocialActivityHabit createSocialActivityHabit(HabitCreationRequest request) {
        // Create and return a new SocialActivityHabit instance
        return new SocialActivityHabit(
                request.name(), // Name of the habit
                request.type(), // Type of the habit
                request.scheduleDays(), // Scheduled days for the habit
                request.endDate(), // End date for the habit
                request.timeInMinutes() // Time in minutes for the habit
        );
    }

    /**
     * Creates a Finances Habit based on the information in the request.
     *
     * @param request The habit creation request containing the information needed to create a new habit.
     * @return A FinancesHabit object with the specified information.
     */
    private static FinancesHabit createFinancesHabit(HabitCreationRequest request) {
        // Create and return a new FinancesHabit instance
        return new FinancesHabit(
                request.name(), // Name of the habit
                request.type(), // Type of the habit
                request.scheduleDays(), // Scheduled days for the habit
                request.endDate(), // End date for the habit
                request.timeInMinutes() // Time in minutes for the habit
        );
    }

    /**
     * Creates an Other Habit based on the information in the request.
     *
     * @param request The habit creation request containing the information needed to create a new habit.
     * @return An OtherHabit object with the specified information.
     */
    private static OtherHabit createOtherHabit(HabitCreationRequest request) {
        // Create and return a new OtherHabit instance
        return new OtherHabit(
                request.name(), // Name of the habit
                request.type(), // Type of the habit
                request.scheduleDays(), // Scheduled days for the habit
                request.endDate(), // End date for the habit
                request.customNotes() // Custom notes for the habit
        );
    }
}
