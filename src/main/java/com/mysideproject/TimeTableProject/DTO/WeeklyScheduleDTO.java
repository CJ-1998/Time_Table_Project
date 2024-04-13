package com.mysideproject.TimeTableProject.DTO;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class WeeklyScheduleDTO {
    private LocalDate startDate;
    private List<DailyScheduleDTO> weeklyPlans;
}
