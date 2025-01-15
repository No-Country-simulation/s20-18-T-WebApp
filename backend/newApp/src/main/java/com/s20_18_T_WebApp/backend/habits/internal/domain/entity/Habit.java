package com.s20_18_T_WebApp.backend.habits.internal.domain.entity;

import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;
import com.s20_18_T_WebApp.backend.habits.internal.domain.vo.WeekDayProgress;
import com.s20_18_T_WebApp.backend.shared.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @Embedded
    private HabitDetails details;

    public Habit (String name, LocalDate startDate, HabitType type) {
        this.name = name;
        this.startDate = startDate;
        this.type = type;
        this.isArchived = false;
        this.streakDays = 0;
        this.progressPercentage = 0.0;
        this.weekDays = new HashSet<>();
        initializeWeekDays();
    }

    /**
     * Initializes the week days for the habit by adding each day of the week as an unselected and not completed day.
     * This is used to initialize the habit with all days of the week available.
     */
    private void initializeWeekDays() {
        // Iterate over each day of the week
        for (DayOfWeek day : DayOfWeek.values()) {
            // Add the day to the set of week days with the day as an unselected and not completed day
            this.weekDays.add(new WeekDayProgress(day));
        }
    }

    public boolean shouldBeArchived() {
        return !isArchived && getUpdatedAt().plusDays(30).isBefore(LocalDateTime.now());
    }

    public void archive() {
        this.isArchived = true;
    }

    public void activate() {
        this.isArchived = false;
    }

    public void updateProgress() {
        if (weekDays == null || weekDays.isEmpty()) {
            this.progressPercentage = 0.0;
            return;
        }

        long completedDays = weekDays.stream()
                .filter(WeekDayProgress::isCompleted)
                .count();

        long selectedDays = weekDays.stream()
                .filter(WeekDayProgress::isSelected)
                .count();

        this.progressPercentage = selectedDays > 0 ? (completedDays * 100.0 /selectedDays) : 0.0;
    }

    /**
     * Updates the streak days for the habit by counting the number of completed days in the week.
     * This is used to keep track of the user's streak of completing the habit.
     */
    public void updateStreakeDays() {
        // Count the number of completed days in the week
        long completedDays = weekDays.stream()
                .filter(WeekDayProgress::isCompleted)
                .count();

        // Update the streak days with the number of completed days
        this.streakDays = (int) completedDays;
    }
}
