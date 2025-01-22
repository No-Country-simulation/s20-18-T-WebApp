package com.s20_18_T_WebApp.backend.habits.internal.domain.enums;

public enum ProgressStatus {
    SCHEDULED("Agendado"),
    COMPLETED("Completado"),
    FAILED("Fallido");

    private final String translation;

    ProgressStatus(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public static ProgressStatus fromString(String value) {
        // Iterate through all enum values
        for (ProgressStatus progressStatus : ProgressStatus.values()) {
            // Compare the input string with the enum name, ignoring case
            if (progressStatus.name().equalsIgnoreCase(value)) {
                return progressStatus; // Return the matching enum
            }
        }
        // Throw an exception if no match is found
        throw new IllegalArgumentException("Invalid ProgressStatus value: " + value);
    }
}
