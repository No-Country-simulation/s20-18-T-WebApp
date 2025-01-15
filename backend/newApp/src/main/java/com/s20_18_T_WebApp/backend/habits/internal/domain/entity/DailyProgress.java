package com.s20_18_T_WebApp.backend.habits.internal.domain.entity;

import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;
import com.s20_18_T_WebApp.backend.shared.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "daily_progress")
public class DailyProgress extends BaseEntity {

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    @Column(name = "scheduled", nullable = false)
    private boolean scheduled;

    @Enumerated
    @Column(name = "type", nullable = false)
    private HabitType type;
}
