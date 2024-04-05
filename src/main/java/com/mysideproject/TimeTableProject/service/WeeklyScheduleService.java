package com.mysideproject.TimeTableProject.service;

import com.mysideproject.TimeTableProject.domain.DailySchedule;
import com.mysideproject.TimeTableProject.domain.Plan;
import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WeeklyScheduleService {

    public WeeklySchedule initWeeklySchedule() {
        LocalDate date = LocalDate.of(2024, 4, 1);
        List<DailySchedule> weeklyPlans = getWeeklyPlans(date);

        WeeklySchedule weeklySchedule = new WeeklySchedule(date, weeklyPlans);
        return weeklySchedule;
    }

    private List<DailySchedule> getWeeklyPlans(LocalDate date) {
        List<DailySchedule> weeklySchedule = new ArrayList<>();

        int dayOfMonth = date.getDayOfMonth();

        for (int day = 0; day < 7; day++) {
            LocalDate newDate = date.withDayOfMonth(dayOfMonth + day);
            DailySchedule dailySchedule = initDailySchedule(newDate);
            weeklySchedule.add(dailySchedule);
        }

        return weeklySchedule;
    }

    private DailySchedule initDailySchedule(LocalDate date) {
        List<Plan> dailyPlans = getDailyPlans(date);

        DailySchedule dailySchedule = new DailySchedule(date, dailyPlans);
        return dailySchedule;
    }

}
