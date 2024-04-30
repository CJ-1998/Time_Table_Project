package com.mysideproject.TimeTableProject.controller;

import com.mysideproject.TimeTableProject.domain.PlanContentTime;
import com.mysideproject.TimeTableProject.service.TimeCalculationService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class TimeCalculationController {

    private final TimeCalculationService timeCalculationService;

    @GetMapping("/timetable/timeCalculation/{startDate}")
    public String calculateWeeklyScheduleTime(@PathVariable("startDate") LocalDate startDate, Model model) {
        List<PlanContentTime> planContentTimes = timeCalculationService.calculateWeeklyScheduleTime(startDate);
        model.addAttribute("planContentTimes", planContentTimes);
        return "timetable/timeCalculation";
    }
}
