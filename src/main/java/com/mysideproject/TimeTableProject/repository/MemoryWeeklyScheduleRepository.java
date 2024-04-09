package com.mysideproject.TimeTableProject.repository;

import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryWeeklyScheduleRepository implements WeeklyScheduleRepository {

    // 주간 계획표 저장하는 Map. key는 주간 계획표의 시작 날짜.
    private static final Map<LocalDate, WeeklySchedule> weeklyScheduleRepository = new HashMap<>();

    // 주간 계획표 저장 메서드
    @Override
    public WeeklySchedule save(WeeklySchedule weeklySchedule) {
        weeklyScheduleRepository.put(weeklySchedule.getStartDate(), weeklySchedule);
        return weeklySchedule;
    }

    // 특정 시작 날짜와 일치하는 주간 계획표 검색하는 메서드
    @Override
    public WeeklySchedule findByStartDate(LocalDate startDate) {
        return weeklyScheduleRepository.get(startDate);
    }
}