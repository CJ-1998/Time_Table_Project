package com.mysideproject.TimeTableProject.repository;

import com.mysideproject.TimeTableProject.domain.DailySchedule;
import com.mysideproject.TimeTableProject.domain.Plan;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemoryDailyScheduleRepository implements DailyScheduleRepository {
    // 일간 계획표 저장하는 Map. key는 일간 계획표의 날짜.
    private static final Map<LocalDate, DailySchedule> dailyScheduleRepository = new HashMap<>();

    private final MemoryPlanRepository memoryPlanRepository;

    // 일간 계획표 저장 메서드
    @Override
    public DailySchedule save(DailySchedule dailySchedule) {
        dailyScheduleRepository.put(dailySchedule.getDate(), dailySchedule);
        return dailySchedule;
    }

    // 특정 날짜와 일치하는 일간 계획표 검색하는 메서드
    @Override
    public DailySchedule findByDate(LocalDate date) {
        return dailyScheduleRepository.get(date);
    }

    // 일간 계획표 업데이트 메서드
    @Override
    public void update(DailySchedule dailySchedule, DailySchedule changedDailySchedule) {
        List<Plan> dailyPlans = memoryPlanRepository.findByDate(dailySchedule.getDate());

        List<Plan> changedDailyPlans = changedDailySchedule.getDailyPlans();

        for (int index = 0; index < dailyPlans.size(); index++) {
            Plan plan = dailyPlans.get(index);
            Plan changedPlan = changedDailyPlans.get(index);

            plan.setPlanContent(changedPlan.getPlanContent());
        }
    }
}
