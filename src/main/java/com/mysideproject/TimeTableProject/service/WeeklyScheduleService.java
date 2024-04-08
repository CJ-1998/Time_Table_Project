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

    public static final int DAY_COUNT = 7;
    public static final int START_HOUR = 0;
    public static final int START_MINUTE = 0;
    public static final int HOUR_COUNT = 24;
    public static final int INTERVAL_MINUTE = 30;

    public WeeklySchedule initWeeklySchedule() {
        LocalDate date = LocalDate.of(2024, 4, 1);
        List<DailySchedule> weeklyPlans = getWeeklyPlans(date);

        WeeklySchedule weeklySchedule = new WeeklySchedule(date, weeklyPlans);
        return weeklySchedule;
    }

    private List<DailySchedule> getWeeklyPlans(LocalDate date) {
        List<DailySchedule> weeklyPlans = new ArrayList<>();

        int dayOfMonth = date.getDayOfMonth();

        for (int day = 0; day < DAY_COUNT; day++) {
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

        // 시간별 계획 리스트 생성
        for (LocalTime startTime : getTimeIntervals()) {
            LocalTime endTime = startTime.plusMinutes(INTERVAL_MINUTE);
            Plan plan = new Plan(0L, date, startTime, endTime, null);
            dailyPlans.add(plan);
        }

        return dailyPlans;
    }

    // 시간 간격을 반환하는 유틸리티 메서드
    private List<LocalTime> getTimeIntervals() {
        List<LocalTime> timeIntervals = new ArrayList<>();
        LocalTime startTime = LocalTime.of(START_HOUR, START_MINUTE);

        for (int timeCount = 0; timeCount < HOUR_COUNT * 2; timeCount++) {
            timeIntervals.add(startTime);
            startTime = startTime.plusMinutes(INTERVAL_MINUTE);
        }

        return timeIntervals;
    }


}
