package com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;
import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.LearningEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LearningHabit extends Habit {

    private static final String DEFAULT_ICON = "LeaningHabitIcon";//TODO Definir icono a almacenar ? almacenarlo en clodinary.
    private static final String DEFAULT_COLOR = "#000000";//TODO definir colores de cada habito.

    @Column(name = "time_in_minutes")
    private Double value;

    private LearningEnum units;

    /*
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "learning_topics", joinColumns = @JoinColumn(name = "habit_id"))
    @Column(name = "topic")
    private Set<String> topics = new HashSet<>();
     */

    public LearningHabit(String name, HabitType type, Set<DayOfWeek> dayOfWeeks, LocalDate localDate, Double value, LearningEnum units) {
        super(name, type, dayOfWeeks, localDate);
        this.value = value;
        this.units = units;
    }

    public Double getValue() {
        return value;
    }

    public LearningEnum getUnits() {
        return units;
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
