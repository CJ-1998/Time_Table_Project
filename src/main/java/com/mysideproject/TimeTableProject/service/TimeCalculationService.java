package com.mysideproject.TimeTableProject.service;

import com.mysideproject.TimeTableProject.domain.PlanContentTime;
import com.mysideproject.TimeTableProject.repository.WeeklyScheduleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeCalculationService {

    private final WeeklyScheduleRepository weeklyScheduleRepository;

    public List<PlanContentTime> calculateWeeklyScheduleTime() {
        return null;
    }
}
