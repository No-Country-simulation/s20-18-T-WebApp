package com.s20_18_T_WebApp.backend.habits.internal.application.service;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitResponseDto;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitSearchCriteria;
import com.s20_18_T_WebApp.backend.shared.application.dto.PageResponse;
import org.springframework.data.domain.Pageable;

public interface HabitSearchService {

    PageResponse<HabitResponseDto> pageSearch(HabitSearchCriteria searchCriteria, Pageable pageable);
}
