package com.s20_18_T_WebApp.backend.habits.internal.domain.enums;

public enum PhysicalActivityEnum {
    METERS("Metros"),
    KILOMETERS("Kilometros"),
    MINUTES("Minutos"),
    HOURS("Horas"),
    TIMES("Veces"),
    CALORIES("Calorias");

    private final String translation;

    PhysicalActivityEnum(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    /**
     * Returns the PhysicalActivityUnits enum value associated with the given string.
     * The comparison is case-insensitive.
     * If no matching value is found, an {@link IllegalArgumentException} is thrown.
     * @param value the name of the PhysicalActivityUnits enum value to retrieve
     * @return the matching PhysicalActivityUnits enum value
     * @throws IllegalArgumentException if no matching value is found
     */
    public static PhysicalActivityEnum fromString(String value) {
        for (PhysicalActivityEnum unit : PhysicalActivityEnum.values()) {
            if (unit.name().equalsIgnoreCase(value)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid PhysicalActivityUnits value: " + value);
    }
}
