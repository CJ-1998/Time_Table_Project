package com.mysideproject.TimeTableProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DailyScheduleController {

    @GetMapping("/timetable/dailySchedule/{date}")
    public String showDailySchedule() {
        return null;
    }
}
