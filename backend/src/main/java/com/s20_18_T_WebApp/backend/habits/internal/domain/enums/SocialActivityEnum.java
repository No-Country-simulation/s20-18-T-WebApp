package com.s20_18_T_WebApp.backend.habits.internal.domain.enums;

public enum SocialActivityEnum {

    MINUTES("Minutos"),
    HOURS("Horas"), //minutos por dia. Temas que se quieren aprender.
    TIMES("Veces");

    private final String translation;

    SocialActivityEnum(String translation) {
        this.translation = translation;
    }

    private String getTranslation() {
        return translation;
    }

    /**
     * This method takes a string and returns the {@link SocialActivityEnum} that matches the string.
     * The comparison is case-insensitive.
     * If no matching value is found, an {@link IllegalArgumentException} is thrown.
     * @param value the name of the {@link SocialActivityEnum} to retrieve
     * @return the matching {@link SocialActivityEnum}
     * @throws IllegalArgumentException if no matching value is found
     */
    private static SocialActivityEnum fromString(String value) {
        for (SocialActivityEnum unit : SocialActivityEnum.values()) {
            if (unit.name().equalsIgnoreCase(value)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid SocialActivityEnum value: " + value);
    }
}
