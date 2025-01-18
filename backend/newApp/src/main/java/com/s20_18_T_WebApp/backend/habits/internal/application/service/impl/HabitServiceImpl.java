package com.s20_18_T_WebApp.backend.habits.internal.application.service.impl;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitCreationRequest;
import com.s20_18_T_WebApp.backend.habits.internal.application.service.HabitService;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes.PhysicalActivityHabit;
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
                    request.scheduleDays())
        }
    }
}
