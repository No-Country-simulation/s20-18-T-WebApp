package com.s20_18_T_WebApp.backend.habits.internal.application.service.impl;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitCreationRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.service.HabitService;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes.*;
import com.s20_18_T_WebApp.backend.habits.internal.infra.persistence.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;


    @Override
    public Habit createHabit(HabitCreationRequest request) {
        Habit habit;

        switch (request.type()) {
            case PHYSICAL_ACTIVITY:
            habit = new PhysicalActivityHabit(
                    request.name(),
                    request.type(),
                    request.scheduleDays(),
                    (request.endDate() == null) ? request.endDate() : null,
                    (request.distanceInKm() == null) ? request.distanceInKm() : null,
                    (request.timeInMinutes() == null) ? request.timeInMinutes() : null
            );
            break;
            case HEALTHY_LIVING:
                habit = new HealthyLivingHabit(
                        request.name(),
                        request.type(),
                        request.scheduleDays(),
                        (request.endDate() == null) ? request.endDate() : null,
                        (request.calories() == null) ? request.calories() : null,
                        (request.timeInMinutes() == null) ? request.timeInMinutes() : null
                );
                break;
            case BAD_HABITS:
                habit = new BadHabitsTracking(
                        request.name(),
                        request.type(),
                        request.scheduleDays(),
                        (request.endDate() == null) ? request.endDate() : null,
                        (request.daysAvoided() == null) ? request.daysAvoided() : null
                );
                break;
            case LEARNING:
                habit = new LearningHabit(
                        request.name(),
                        request.type(),
                        request.scheduleDays(),
                        (request.endDate() == null) ? request.endDate() : null,
                        (request.timeInMinutes() == null) ? request.timeInMinutes() : null
                );
                break;
            case FINANCES:
                habit = new FinancesHabit(
                        request.name(),
                        request.type(),
                        request.scheduleDays(),
                        (request.endDate() == null) ? request.endDate() : null,
                        (request.timeInMinutes() == null) ? request.timeInMinutes() : null
                );
                break;
            case SOCIAL_ACTIVITY:
                habit = new SocialActivityHabit(
                        request.name(),
                        request.type(),
                        request.scheduleDays(),
                        (request.endDate() == null) ? request.endDate() : null,
                        (request.timeInMinutes() == null) ? request.timeInMinutes() : null
                );
                break;
            case OTHER:
                habit = new OtherHabit(
                        request.name(),
                        request.type(),
                        request.scheduleDays(),
                        (request.endDate() == null) ? request.endDate() : null,
                        (request.customNotes() == null) ? request.customNotes() : null
                );
                break;
            default:
                throw new IllegalArgumentException("Habit type not supported: " + request.type());
        }

        return habitRepository.save(habit);
    }
}
