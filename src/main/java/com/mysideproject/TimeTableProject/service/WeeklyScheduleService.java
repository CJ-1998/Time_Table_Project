package com.mysideproject.TimeTableProject.service;

import com.mysideproject.TimeTableProject.domain.DailySchedule;
import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import java.time.LocalDate;
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


}
