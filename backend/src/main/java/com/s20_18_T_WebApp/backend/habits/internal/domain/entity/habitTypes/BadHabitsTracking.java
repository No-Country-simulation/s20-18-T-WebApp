package com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes;


import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BadHabitsTracking extends Habit {

    private static final String DEFAULT_ICON = "BadHabitIcon";//TODO Definir icono a almacenar ? almacenarlo en clodinary.
    private static final String DEFAULT_COLOR = "#000000";//TODO definir colores de cada habito.

    @Column(name = "days_avoided", nullable = false)
    private String comment;//Hablar con Cesar, el puso tipo de dato minuto, horas, veces.

    public BadHabitsTracking(String name, HabitType type, Set<DayOfWeek> dayOfWeeks, LocalDate endDate, String comment) {
        super(name, type, dayOfWeeks, endDate);
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String getIcon() {
        return DEFAULT_ICON;
    }

    @Override
    public String getColor() {
        return DEFAULT_COLOR;
    }
}
