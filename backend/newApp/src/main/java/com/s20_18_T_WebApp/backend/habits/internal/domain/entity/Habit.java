package com.s20_18_T_WebApp.backend.habits.internal.domain.entity;

import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;
import com.s20_18_T_WebApp.backend.shared.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)//Crea una tabla base para la entidad habit y las subclases heredan de ella
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Habit extends BaseEntity { //TODO ALMACENAR LAS METAS A ALCANZAR PARA CADA HABITO(EJ ACTIVICAD FISICA CORRER 2KM POR DIA) y tipo de unidad en un string.
    //TODO INCORPORAR EL ID DEL HABITO EN TODO DTO.
    //TODO tabla de fechas de cumplimiento. Ej: fecha - completado

    @Column(name = "name", nullable = false, length = 100)
    private String name;//TODO default name segun tipo de habito.

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private HabitType type;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "habit_schedule_days", joinColumns = @JoinColumn(name = "habit_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private Set<DayOfWeek> scheduleDays = new HashSet<>();

    @Column(name = "current_streak", nullable = false)
    private int currentStreak;

    @Column(name = "longest_streak", nullable = false)
    private int longestStreak;//dias de racha mas larga

    @Column(nullable = false)
    private boolean archived = false;

    @Column(name = "end_date")
    private LocalDate endDate;//A menos que se especifique se considera para siempre.

    //TODO Agregar alguna propiedad que almacene los dias y si estos fueron cumplidos o fallados. Tambien tengo que hacer un metodo que calacule que dias del futuro van a marcarcarse como pendientes y cuales no. Para mostrarlos en un calendario.
    List<DailyProgress> progress = new ArrayList<>();

    public Habit (String name, HabitType type, Set<DayOfWeek> scheduleDays) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Habit name cannot be null or empty.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Habit type cannot be null.");
        }
        if (scheduleDays == null || scheduleDays.isEmpty()) {
            throw new IllegalArgumentException("Habit schedule days cannot be null or empty.");
        }

        this.name = name;
        this.type = type;
        this.scheduleDays = new HashSet<>(scheduleDays);
        this.archived = false;
        this.currentStreak = 0;
        this.longestStreak = 0;
    }
    /**
     * Checks if the habit should be archived.
     * <p>
     * A habit should be archived if it is not archived, and the last update date is 30 days ago or more.
     *
     * @return true if the habit should be archived, false otherwise
     */
    public boolean shouldBeArchived() {
        // Check if the habit is not archived and the last update date is 30 days ago or more
        return !archived && getUpdatedAt().plusDays(30).isBefore(LocalDateTime.now());
    }

    /**
     * Archives the habit.
     *
     * This method sets the habit's archived status to true, indicating that the habit
     * is no longer active and should be considered archived.
     */
    public void archive() {
        // Set the habit's archived status to true
        this.archived = true;
    }

    /**
     * Activates the habit.
     *
     * This method sets the habit's archived status to false, indicating that the habit
     * is active and should be considered as such.
     */
    public void activate() {
        // Set the habit's archived status to false
        this.archived = false;
    }

    public abstract String getIcon();

    public abstract String getColor();
}

/*
Consulta SQL para traer toda la informacion de los habitos.
SELECT h.*, pah.distance_in_km, pah.time_in_minutes, hlh.calories, hlh.time_in_minutes,
       bht.days_avoided, lh.topics, sah.time_in_minutes, oh.custom_notes
FROM habit h
LEFT JOIN physical_activity_habit pah ON h.id = pah.id
LEFT JOIN healthy_living_habit hlh ON h.id = hlh.id
LEFT JOIN bad_habits_tracking bht ON h.id = bht.id
LEFT JOIN learning_habit lh ON h.id = lh.id
LEFT JOIN social_activity_habit sah ON h.id = sah.id
LEFT JOIN other_habit oh ON h.id = oh.id;
 */