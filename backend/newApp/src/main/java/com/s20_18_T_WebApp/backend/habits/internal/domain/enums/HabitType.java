package com.s20_18_T_WebApp.backend.habits.internal.domain.enums;

public enum HabitType {
    // Podemos añadir mas tipos de habitos: meditation, personal project, free time, financial management.
    //Cada habito tiene que tener un color predefinido.
    //agregar un cumpliento de no comenter malos habitos poco saludables.

    PHYSICAL_ACTIVITY("Actividad Fisica"),//km o minutos.
    HEALTHY_LIVING("Vida Saludable"),//calorias, minutos por dia.
    BAD_HABITS("Malos Habitos"),//malos habitos. //mide la cantidad de dias que estas evitando caer en cada mal habito.
    LEARNING("Aprendizaje"), //minutos por dia. Temas que se quieren aprender.
    FINANCES("Finanzas"),//finanzas //minutos por dia.
    SOCIAL_ACTIVITY("Actividad Social"), //minutos por dia
    OTHER("Otro");

    private final String translation;

    HabitType(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public static HabitType fromString(String value) {
        for (HabitType habitType : HabitType.values()) {
            if (habitType.name().equalsIgnoreCase(value)) {
                return habitType;
            }
        }
        throw new IllegalArgumentException("Invalid HabitType value: " + value);
    }
}
