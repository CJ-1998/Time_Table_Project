package com.mysideproject.TimeTableProject.domain;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeeklySchedule {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<DailySchedule> weeklyPlans;

    public WeeklySchedule(LocalDate startDate, LocalDate endDate,
                          List<DailySchedule> weeklyPlans) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.weeklyPlans = weeklyPlans;
    }
}
