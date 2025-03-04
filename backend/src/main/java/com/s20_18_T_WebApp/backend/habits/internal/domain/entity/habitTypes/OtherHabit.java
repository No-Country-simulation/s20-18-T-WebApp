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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OtherHabit extends Habit {

    private static final String DEFAULT_ICON = "OtherHabitIcon";//TODO Definir icono a almacenar ? almacenarlo en clodinary.
    private static final String DEFAULT_COLOR = "#000000";//TODO definir colores de cada habito.

    @Column (name = "custom_notes", length = 500)
    private String customNotes;

    public OtherHabit(String name, HabitType type, Set<DayOfWeek> dayOfWeeks, LocalDate localDate, String s) {
        super(name, type, dayOfWeeks, localDate);
        this.customNotes = s;
    }

    public String getCustomNotes() {
        return customNotes;
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
