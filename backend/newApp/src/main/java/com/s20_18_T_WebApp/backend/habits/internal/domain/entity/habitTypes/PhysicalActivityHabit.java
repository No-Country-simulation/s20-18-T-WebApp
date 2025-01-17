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

    @Column(name = "distance_in_km")
    private Double disntanceInKm;

    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;
}
