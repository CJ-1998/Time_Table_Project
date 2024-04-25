package com.mysideproject.TimeTableProject.service;

import com.mysideproject.TimeTableProject.domain.DailySchedule;
import com.mysideproject.TimeTableProject.domain.PlanContentTime;
import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import com.mysideproject.TimeTableProject.repository.WeeklyScheduleRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeCalculationService {

    private final WeeklyScheduleRepository weeklyScheduleRepository;

    public List<PlanContentTime> calculateWeeklyScheduleTime(LocalDate startDate) {
        List<PlanContentTime> planContentTimes = new ArrayList<>();

        WeeklySchedule weeklySchedule = weeklyScheduleRepository.findByStartDate(startDate);
        List<DailySchedule> weeklyPlans = weeklySchedule.getWeeklyPlans();

        for (DailySchedule dailySchedule : weeklyPlans) {
            PlanContentTime planContentTime = calculateDailyScheduleTime(dailySchedule);
            planContentTimes.add(planContentTime);
        }

        return planContentTimes;
    }

    private PlanContentTime calculateDailyScheduleTime(DailySchedule dailySchedule) {

        return null;
    }
}
