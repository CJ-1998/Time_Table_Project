package com.mysideproject.TimeTableProject.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailySchedule {
    private LocalDate date;
    private DayOfWeek weekDay;
    private List<Plan> dailyPlans;

    public DailySchedule(LocalDate date, DayOfWeek weekDay,
                         List<Plan> dailyPlans) {
        this.date = date;
        this.weekDay = weekDay;
        this.dailyPlans = dailyPlans;
    }
}
