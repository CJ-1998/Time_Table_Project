package com.mysideproject.TimeTableProject.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanContentTime {
    private String planContent;
    private Double totalHoursInWeek;
    private Double totalPercentInWeek;
    private List<Double> hoursPerDayOfWeek;

    public PlanContentTime(String planContent, Double totalHoursInWeek, Double totalPercentInWeek,
                           List<Double> hoursPerDayOfWeek) {
        this.planContent = planContent;
        this.totalHoursInWeek = totalHoursInWeek;
        this.totalPercentInWeek = totalPercentInWeek;
        this.hoursPerDayOfWeek = hoursPerDayOfWeek;
    }
}
