package com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.FinancesEnum;
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
public class FinancesHabit extends Habit {

    private static final String DEFAULT_ICON = "FinancesHabitIcon";//TODO Definir icono a almacenar ? almacenarlo en clodinary.
    private static final String DEFAULT_COLOR = "#000000";//TODO definir colores de cada habito.

    @Column (name = "time_in_minutes", nullable = false)
    private Double value;

    @Column(name = "units", nullable = false)
    private FinancesEnum units;

    public FinancesHabit(String name, HabitType type, Set<DayOfWeek> dayOfWeeks, LocalDate localDate, Double value, FinancesEnum financesEnum) {
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
