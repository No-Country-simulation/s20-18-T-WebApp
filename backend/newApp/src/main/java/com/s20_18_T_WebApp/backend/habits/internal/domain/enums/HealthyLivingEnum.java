package com.s20_18_T_WebApp.backend.habits.internal.domain.enums;

public enum HealthyLivingEnum {

    CALORIES("Calorias"),
    HOURS("Horas"),
    MINUTES("Minutos");

    private final String translation;

    HealthyLivingEnum(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    /**
     * Converts a string to a corresponding {@link HealthyLivingEnum} value.
     * The comparison is case-insensitive.
     * If no matching value is found, an {@link IllegalArgumentException} is thrown.
     *
     * @param value the name of the HealthyLivingEnum to retrieve
     * @return the matching HealthyLivingEnum
     * @throws IllegalArgumentException if no matching value is found
     */
    public static HealthyLivingEnum fromString(String value) {
        // Iterate through all enum values
        for (HealthyLivingEnum healthyLivingEnum : HealthyLivingEnum.values()) {
            // Compare the input string with the enum name, ignoring case
            if (healthyLivingEnum.name().equalsIgnoreCase(value)) {
                return healthyLivingEnum; // Return the matching enum
            }
        }
        // Throw an exception if no match is found
        throw new IllegalArgumentException("Invalid HealthyLivingEnum value: " + value);
    }
}
