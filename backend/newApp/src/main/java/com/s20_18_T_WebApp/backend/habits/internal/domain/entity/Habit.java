package com.s20_18_T_WebApp.backend.habits.internal.domain.entity;

import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;
import com.s20_18_T_WebApp.backend.habits.internal.domain.vo.WeekDayProgress;
import com.s20_18_T_WebApp.backend.shared.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "habits")
public class Habit extends BaseEntity {
    /*
      nombre, fecha, dias que se va a repetir(duracion del habito, puede ser para siempre o por un valor predefinido de dias. Si se termina de utilizar o no se usa por determinado tiempo se archiva.)
    , dias de la semana. tipo de habito.
    porcentaje de seguimiento,
    dias de racha.
    archivado: true o false.
     */

    @Column(name = "name", nullable = false)
    private String name;//TODO default name segun tipo de habito.

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;//A menos que se especifique se considera para siempre.

    @Column(nullable = false)
    private boolean isArchived = false;

    @Column(nullable = false)
    private int streakDays;//dias de racha

    @Column(nullable = false, length = 100)
    private Double progressPercentage;//porcentaje de seguimiento TODO como calcularlo.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HabitType type;

    @ElementCollection
    @CollectionTable(name = "habit_week_days", joinColumns = @JoinColumn(name = "id"))
    private Set<WeekDayProgress> weekDays;//dias de la semana en las que se va a repetir el habito.


}
