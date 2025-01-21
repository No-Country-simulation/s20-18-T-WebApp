package com.s20_18_T_WebApp.backend.habits.internal.factory;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitCreationRequest;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes.*;
import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.*;

public class HabitFactory {

    /**
     * Creates a habit based on the type specified in the request.
     *
     * @param request The habit creation request containing the information needed to create a new habit.
     * @return A Habit object of the appropriate type.
     */
    public static Habit createHabit(HabitCreationRequest request) {
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
                request.value(),
                PhysicalActivityUnits.fromString(request.units())
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
                request.value(), // Value associated with the habit
                HealthyLivingEnum.fromString(request.units()) // Units of the habit
        );
    }

    /**
     * Creates a Bad Habits Tracking Habit based on the information in the request.
     *
     * @param request The habit creation request containing the information needed to create a new habit.
     * @return A BadHabitsTracking object with the specified information.
     */
    private static BadHabitsTracking createBadHabitsTracking(HabitCreationRequest request) {
        // Create and return a new BadHabitsTracking instance with the specified information.
        return new BadHabitsTracking(
                request.name(), // Name of the habit
                request.type(), // Type of the habit
                request.scheduleDays(), // Scheduled days for the habit
                request.endDate(), // End date for the habit
                request.customNotes() // Custom notes for the habit
        );
    }

    /**
     * Creates a Learning Habit based on the information in the request.
     *
     * This method takes a HabitCreationRequest and extracts the necessary
     * information to create a new LearningHabit instance.
     *
     * @param request The habit creation request containing the information
     *                needed to create a new habit such as name, type,
     *                schedule days, end date, value, and units.
     * @return A LearningHabit object populated with the specified information.
     */
    private static LearningHabit createLearningHabit(HabitCreationRequest request) {
        // Create and return a new LearningHabit instance
        return new LearningHabit(
                request.name(), // Name of the habit
                request.type(), // Type of the habit
                request.scheduleDays(), // Scheduled days for the habit
                request.endDate(),// End date for the habit
                request.value(),
                LearningEnum.valueOf(request.units()) // Units of the habit
        );
    }

    /**
     * Creates a Social Activity Habit based on the information in the request.
     *
     * This method takes a HabitCreationRequest and extracts the necessary
     * information to create a new SocialActivityHabit instance.
     *
     * @param request The habit creation request containing the information
     *                needed to create a new habit such as name, type,
     *                schedule days, end date, value, and units.
     * @return A SocialActivityHabit object populated with the specified information.
     */
    private static SocialActivityHabit createSocialActivityHabit(HabitCreationRequest request) {
        // Create and return a new SocialActivityHabit instance using the data from the request
        return new SocialActivityHabit(
                request.name(), // Name of the habit
                request.type(), // Type of the habit
                request.scheduleDays(), // Scheduled days for the habit
                request.endDate(), // End date for the habit
                request.value(), // Value associated with the habit
                SocialActivityEnum.valueOf(request.units()) // Units of the habit
        );
    }

    /**
     * Creates a Finances Habit based on the information in the request.
     *
     * This method initializes a new instance of a FinancesHabit using the
     * details provided in the HabitCreationRequest.
     *
     * @param request The habit creation request containing the information
     *                needed to create a new habit such as name, type,
     *                schedule days, end date, value, and units.
     * @return A FinancesHabit object populated with the specified information.
     */
    private static FinancesHabit createFinancesHabit(HabitCreationRequest request) {
        // Create and return a new FinancesHabit instance
        return new FinancesHabit(
                request.name(), // Name of the habit
                request.type(), // Type of the habit
                request.scheduleDays(), // Scheduled days for the habit
                request.endDate(), // End date for the habit
                request.value(), // Value associated with the habit
                FinancesEnum.valueOf(request.units()) // Financial units for the habit
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
