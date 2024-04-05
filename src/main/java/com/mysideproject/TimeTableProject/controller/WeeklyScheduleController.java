package com.mysideproject.TimeTableProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeeklyScheduleController {

    @GetMapping("/timetable")
    public String showFirstWeeklySchedule(Model model) {

        return "timetable/weeklySchedule";
    }
}
