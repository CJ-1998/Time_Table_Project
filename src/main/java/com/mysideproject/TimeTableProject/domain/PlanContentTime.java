package com.mysideproject.TimeTableProject.domain;

import java.time.Duration;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanContentTime {
    private String planContent;
    private Duration totalHoursInWeek;
    private Double totalPercentInWeek;
    private List<Duration> hoursPerDayOfWeek;

    public PlanContentTime(String planContent, Duration totalHoursInWeek, Double totalPercentInWeek,
                           List<Duration> hoursPerDayOfWeek) {
        this.planContent = planContent;
        this.totalHoursInWeek = totalHoursInWeek;
        this.totalPercentInWeek = totalPercentInWeek;
        this.hoursPerDayOfWeek = hoursPerDayOfWeek;
    }
}
