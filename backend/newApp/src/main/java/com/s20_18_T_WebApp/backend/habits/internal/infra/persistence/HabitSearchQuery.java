package com.s20_18_T_WebApp.backend.habits.internal.infra.persistence;

import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitResponseDto;
import com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitSearchCriteria;
import com.s20_18_T_WebApp.backend.shared.application.dto.PageResponse;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class HabitSearchQuery {
    private static final Logger log = LoggerFactory.getLogger(HabitSearchQuery.class);
    private final EntityManager entityManager;

    public PageResponse<HabitResponseDto> pageSearch(HabitSearchCriteria searchCriteria, Pageable pageable) {
        String baseQuery = " FROM Habit h";
        StringBuilder queryBuilder = new StringBuilder("SELECT new com.s20_18_T_WebApp.backend.habits.internal.application.dto.HabitSearchResult(")
                .append("h.id, h.name, h.type, h.scheduleDays, h.currentStreak, h.longestStreak, h.archived, ")
                .append("h.startDate, h.endDate, h.icon, h.color)").append(baseQuery);
        StringBuilder countBuilder = new StringBuilder("SELECT COUNT(h)").append(baseQuery);
        Map<String, Object> params = new HashMap<>();
        List<String> conditions = new ArrayList<>();

        buildSearchConditions(searchCriteria, conditions, params);

        if (!conditions.isEmpty()) {
            String whereClause = String.join(" AND ", conditions);
            queryBuilder.append(" WHERE ").append(whereClause);
            countBuilder.append(" WHERE ").append(whereClause);
        }

        try {
            var countJpqlQuery = entityManager.createQuery(countBuilder.toString(), Long.class);
            var dataJpqlQuery = entityManager.createQuery(queryBuilder.toString() + buildOrderByClause(pageable, "h"), HabitResponseDto.class);

            params.forEach((key, value) -> {
                countJpqlQuery.setParameter(key, value);
                dataJpqlQuery.setParameter(key, value);
            });

            Long total = countJpqlQuery.getSingleResult();
            dataJpqlQuery.setFirstResult((int) pageable.getOffset());
            dataJpqlQuery.setMaxResults(pageable.getPageSize());

            List<HabitResponseDto> results = dataJpqlQuery.getResultList();
            return PageResponse.from(new PageImpl<>(results, pageable, total));
        } catch (Exception e) {
            log.error("Error executing habit search query", e);
            throw new IllegalArgumentException("Error executing habit search query", e);
            // new DomainException(INVALID_DATA, e.getMessage()); TODO create error habdling
        }
    }

    private void buildSearchConditions(HabitSearchCriteria criteria, List<String> conditions, Map<String, Object> params) {
        if (criteria.name() != null) {
            conditions.add("UPPER(h.name) LIKE UPPER(:name)");
            params.put("name", "%" + criteria.name() + "%");
        }

        if (criteria.type() != null) {
            conditions.add("h.type = :type");
            params.put("type", criteria.type());
        }

        if (criteria.scheduleDays() != null && !criteria.scheduleDays().isEmpty()) {
            conditions.add("h.scheduleDays IN (:scheduleDays)");
            params.put("scheduleDays", criteria.scheduleDays());
        }

        if (criteria.archived() != null) {
            conditions.add("h.archived = :archived");
            params.put("archived", criteria.archived());
        }

        if (criteria.minStreak() != null) {
            conditions.add("h.currentStreak >= :minStreak");
            params.put("minStreak", criteria.minStreak());
        }

        if (criteria.maxStreak() != null) {
            conditions.add("h.currentStreak <= :maxStreak");
            params.put("maxStreak", criteria.maxStreak());
        }

        if (criteria.startDateFrom() != null) {
            conditions.add("h.startDate >= :startDateFrom");
            params.put("startDateFrom", criteria.startDateFrom());
        }

        if (criteria.startDateTo() != null) {
            conditions.add("h.startDate <= :startDateTo");
            params.put("startDateTo", criteria.startDateTo());
        }

        if (criteria.endDateFrom() != null) {
            conditions.add("h.endDate >= :endDateFrom");
            params.put("endDateFrom", criteria.endDateFrom());
        }

        if (criteria.endDateTo() != null) {
            conditions.add("h.endDate <= :endDateTo");
            params.put("endDateTo", criteria.endDateTo());
        }
    }

    private String buildOrderByClause(Pageable pageable, String entityName) {
        if (pageable.getSort().isSorted()) {
            return pageable.getSort().stream()
                    .map(order -> entityName + "." + order.getProperty() + " " + order.getDirection())
                    .collect(Collectors.joining(", ", " ORDER BY ", ""));
        }
        return "";
    }
}
