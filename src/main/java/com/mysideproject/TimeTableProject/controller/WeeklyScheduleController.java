package com.mysideproject.TimeTableProject.controller;

import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import com.mysideproject.TimeTableProject.service.WeeklyScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class WeeklyScheduleController {

    private final WeeklyScheduleService weeklyScheduleService;

    @PostMapping("/timetable/new")
    public String addTimeTable() {
        return "timetable/newTimeTable";
    }

    @GetMapping("/timetable")
    public String showFirstWeeklySchedule(Model model) {
        WeeklySchedule weeklySchedule = weeklyScheduleService.initWeeklySchedule();
        model.addAttribute("weeklySchedule", weeklySchedule);
        return "timetable/weeklySchedule";
    }
}
