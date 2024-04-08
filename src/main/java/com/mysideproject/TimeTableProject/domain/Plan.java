package com.mysideproject.TimeTableProject.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plan {

    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String planContent;

    public Plan(Long id, LocalDate date, LocalTime startTime, LocalTime endTime, String planContent) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.planContent = planContent;
    }
}
