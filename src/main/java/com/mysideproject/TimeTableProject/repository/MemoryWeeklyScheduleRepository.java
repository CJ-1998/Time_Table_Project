package com.mysideproject.TimeTableProject.repository;

import com.mysideproject.TimeTableProject.domain.DailySchedule;
import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemoryWeeklyScheduleRepository implements WeeklyScheduleRepository {

    // 주간 계획표 저장하는 Map. key는 주간 계획표의 시작 날짜.
    private static final Map<LocalDate, WeeklySchedule> weeklyScheduleRepository = new HashMap<>();
    private final MemoryDailyScheduleRepository memoryDailyScheduleRepository;

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

    @Override
    public List<WeeklySchedule> findAll() {
        return new ArrayList<>(weeklyScheduleRepository.values());
    }

    // 주간 계획표 업데이트하는 메서드
    @Override
    public WeeklySchedule update(WeeklySchedule changedWeeklySchedule) {
        WeeklySchedule weeklySchedule = findByStartDate(changedWeeklySchedule.getStartDate());

        List<DailySchedule> weeklyPlans = weeklySchedule.getWeeklyPlans();
        List<DailySchedule> changedWeeklyPlans = changedWeeklySchedule.getWeeklyPlans();

        for (int index = 0; index < changedWeeklyPlans.size(); index++) {
            memoryDailyScheduleRepository.update(weeklyPlans.get(index), changedWeeklyPlans.get(index));
        }
        return weeklySchedule;
    }
}