package com.mysideproject.TimeTableProject.domain;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeeklySchedule {
    private LocalDate startDate;
    private List<DailySchedule> weeklyPlans;
}
