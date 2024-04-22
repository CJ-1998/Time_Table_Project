package com.mysideproject.TimeTableProject.repository;

import com.mysideproject.TimeTableProject.domain.DailySchedule;
import java.time.LocalDate;

public interface DailyScheduleRepository {

    DailySchedule save(DailySchedule dailySchedule);

    DailySchedule findByDate(LocalDate date);

    void update(DailySchedule dailySchedule, DailySchedule changedDailySchedule1);
}
