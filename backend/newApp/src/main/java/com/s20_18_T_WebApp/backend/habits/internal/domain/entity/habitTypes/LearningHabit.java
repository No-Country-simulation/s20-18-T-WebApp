package com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LearningHabit extends Habit {

    private static final String DEFAULT_ICON = "iconoBadHabit";//TODO Definir icono a almacenar ? almacenarlo en clodinary.
    private static final String DEFAULT_COLOR = "#000000";//TODO definir colores de cada habito.

    @Column(name = "time_in_minutes")
    private Integer timeInMinutes;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "learning_topics", joinColumns = @JoinColumn(name = "habit_id"))
    @Column(name = "topic")
    private Set<String> topics = new HashSet<>();

    @Override
    public String getIcon() {
        return DEFAULT_ICON;
    }

    @Override
    public String getColor() {
        return DEFAULT_COLOR;
    }
}
