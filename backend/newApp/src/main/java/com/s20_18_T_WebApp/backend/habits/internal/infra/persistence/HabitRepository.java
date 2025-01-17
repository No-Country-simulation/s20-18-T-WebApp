package com.s20_18_T_WebApp.backend.habits.internal.infra.persistence;

import com.s20_18_T_WebApp.backend.habits.internal.domain.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

}
