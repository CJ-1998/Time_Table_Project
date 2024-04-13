package com.mysideproject.TimeTableProject.DTO;

import java.util.List;
import lombok.Data;

@Data
public class DailyScheduleDTO {
    private List<PlanDTO> dailyPlans;
}
