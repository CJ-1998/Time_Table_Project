package com.mysideproject.TimeTableProject.controller;

import com.mysideproject.TimeTableProject.domain.DailySchedule;
import com.mysideproject.TimeTableProject.repository.DailyScheduleRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class DailyScheduleController {

    private final DailyScheduleRepository dailyScheduleRepository;

    @GetMapping("/timetable/dailySchedule/{date}")
    public String showDailySchedule(@PathVariable("date") LocalDate date, Model model) {
        DailySchedule dailySchedule = dailyScheduleRepository.findByDate(date);
        model.addAttribute("dailySchedule", dailySchedule);
        return "timetable/dailySchedule";
    }
}
