package com.s20_18_T_WebApp.backend.habits.internal.domain.enums;

public enum LearningEnum {

    MINUTES("Minutos"),
    HOURS("Horas"),
    TIMES("Veces"),
    PAGES("Paginas"),
    CHAPTERS("Capitulos");

    private final String translation;

    LearningEnum(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    /**
     * This method takes a string and returns the {@link LearningEnum} that matches the string.
     * The method is case-insensitive.
     * If no matching value is found, an {@link IllegalArgumentException} is thrown.
     * @param value the name of the {@link LearningEnum} to retrieve
     * @return the matching {@link LearningEnum}, or throws an {@link IllegalArgumentException} if no matching value is found
     */
    public static LearningEnum fromString(String value) {
        for (LearningEnum learningEnum : LearningEnum.values()) {
            if (learningEnum.name().equalsIgnoreCase(value)) {
                return learningEnum;
            }
        }
        throw new IllegalArgumentException("Invalid LearningEnum value: " + value);
    }
}
