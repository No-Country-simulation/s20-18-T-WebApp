package com.s20_18_T_WebApp.backend.habits.internal.domain.enums;

public enum HabitType {
    // Podemos añadir mas tipos de habitos: meditation, personal project, free time, financial management.

    PHYSICAL_ACTIVITY("Actividad Fisica"),
    HEALTHY_LIVING("Vida Saludable"),
    WORK("Trabajo"),
    LEARNING("Aprendizaje"),
    READING("Lectura"),
    SOCIAL_ACTIVITY("Actividad Social"),
    TRAVEL("Viaje"),
    HEALTH("Salud"),
    OTHER("Otro");

    private final String translation;

    HabitType(String translation) {
        this.translation = translation;
    }
}
