package com.mysideproject.TimeTableProject.service;

import com.mysideproject.TimeTableProject.domain.DailySchedule;
import com.mysideproject.TimeTableProject.domain.Plan;
import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import java.time.LocalDate;
import java.time.LocalTime;
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
        List<DailySchedule> weeklyPlans = new ArrayList<>();

        int dayOfMonth = date.getDayOfMonth();

        for (int day = 0; day < 7; day++) {
            LocalDate newDate = date.withDayOfMonth(dayOfMonth + day);
            DailySchedule dailySchedule = initDailySchedule(newDate);
            weeklyPlans.add(dailySchedule);
        }

        return weeklyPlans;
    }

    private DailySchedule initDailySchedule(LocalDate date) {
        List<Plan> dailyPlans = getDailyPlans(date);

        DailySchedule dailySchedule = new DailySchedule(date, dailyPlans);
        return dailySchedule;
    }

    private List<Plan> getDailyPlans(LocalDate date) {
        List<Plan> dailyPlans = new ArrayList<>();

        LocalTime startOfDayTime = LocalTime.of(0, 0);
        LocalTime startTime = startOfDayTime;

        for (int timeCount = 0; timeCount < 24 * 2; timeCount++) {
            LocalTime endTime = startTime.plusMinutes(30);
            Plan plan = new Plan(0L, date, startTime, endTime, "");
            dailyPlans.add(plan);
            startTime = endTime;
        }

        return dailyPlans;
    }

}
