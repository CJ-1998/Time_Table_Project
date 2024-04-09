package com.mysideproject.TimeTableProject.repository;

import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import java.time.LocalDate;

public interface WeeklyScheduleRepository {

    WeeklySchedule save(WeeklySchedule weeklySchedule);

    WeeklySchedule findByStartDate(LocalDate startDate);
}
