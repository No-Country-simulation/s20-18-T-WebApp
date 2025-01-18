package com.s20_18_T_WebApp.backend.habits.internal.domain.entity.habitTypes;


import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PhysicalActivityHabit extends Habit {

    private static final String DEFAULT_ICON = "iconoBadHabit";//TODO Definir icono a almacenar ? almacenarlo en clodinary.
    private static final String DEFAULT_COLOR = "#000000";//TODO definir colores de cada habito.

    @Column(name = "distance_in_km")
    private Double disntanceInKm;

    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;

    @Override
    public String getIcon() {
        return DEFAULT_ICON;
    }

    @Override
    public String getColor() {
        return DEFAULT_COLOR;
    }
}
