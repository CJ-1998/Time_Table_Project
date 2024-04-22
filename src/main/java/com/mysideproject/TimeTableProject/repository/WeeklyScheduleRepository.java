package com.mysideproject.TimeTableProject.repository;

import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import java.time.LocalDate;
import java.util.List;

public interface WeeklyScheduleRepository {

    WeeklySchedule save(WeeklySchedule weeklySchedule);

    WeeklySchedule findByStartDate(LocalDate startDate);

    List<WeeklySchedule> findAll();

    WeeklySchedule update(WeeklySchedule weeklySchedule);
}
