package com.s20_18_T_WebApp.backend.habits.internal.domain.enums;

public enum FinancesEnum {

    MINUTES("Minutos"),
    HOURS("Horas"),
    TIMES("Veces");

    private final String translation;

    FinancesEnum(String translation) {
        this.translation = translation;
    }

    private String getTranslation() {
        return translation;
    }

    /**
     * Converts a string to a corresponding {@link FinancesEnum} value.
     * The comparison is case-insensitive.
     * If no matching value is found, an {@link IllegalArgumentException} is thrown.
     *
     * @param value the name of the FinancesEnum to retrieve
     * @return the matching FinancesEnum
     * @throws IllegalArgumentException if no matching value is found
     */
    public static FinancesEnum fromString(String value) {
        // Iterate through all enum values
        for (FinancesEnum financesEnum : FinancesEnum.values()) {
            // Compare the input string with the enum name, ignoring case
            if (financesEnum.name().equalsIgnoreCase(value)) {
                return financesEnum; // Return the matching enum
            }
        }
        // Throw an exception if no match is found
        throw new IllegalArgumentException("Invalid FinancesEnum value: " + value);
    }
}
