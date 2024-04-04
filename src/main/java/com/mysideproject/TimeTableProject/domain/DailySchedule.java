package com.mysideproject.TimeTableProject.domain;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailySchedule {
    private LocalDate date;
    private List<Plan> dailyPlans;

}
