package com.s20_18_T_WebApp.backend.habits.internal.application.dto;

import com.s20_18_T_WebApp.backend.habits.internal.domain.enums.HabitType;

public class HabitTypeDTO {
    private String value;
    private String translation;

    public HabitTypeDTO(String value, String translation) {
        this.value = value;
        this.translation = translation;
    }

    public String getValue() {
        return value;
    }

    public String getTranslation() {
        return translation;
    }
}
