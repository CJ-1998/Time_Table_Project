package com.mysideproject.TimeTableProject.controller;

import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import com.mysideproject.TimeTableProject.repository.WeeklyScheduleRepository;
import com.mysideproject.TimeTableProject.service.WeeklyScheduleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class WeeklyScheduleController {

    private final WeeklyScheduleService weeklyScheduleService;
    private final WeeklyScheduleRepository weeklyScheduleRepository;

    // 전체 주간 계획표 화면 달라는 요청
    // 전체 주간 계획표 화면으로 이동
    @GetMapping("/timetables")
    public String showAllWeeklySchedule(Model model) {
        List<WeeklySchedule> allWeeklySchedule = weeklyScheduleRepository.findAll();
        model.addAttribute("allWeeklySchedule", allWeeklySchedule);
        return "timetable/allWeeklySchedule";
    }

    // 전체 주간 계획표 화면에서 추가 버튼 누르면 들어오는 요청.
    // 주간 계획표 추가 화면으로 이동.
    @PostMapping("/timetable/new")
    public String addTimeTable() {
        return "timetable/newTimeTable";
    }

    // 비어 있는 주간 계획표를 보여주는 컨트롤러.
    // 추후에는 날짜에 따라 주간 계획표 찾아 Model에 담아 보낼 것이라 사용되지 않을 컨트롤러.
    @GetMapping("/timetable")
    public String showFirstWeeklySchedule(Model model) {
        WeeklySchedule weeklySchedule = weeklyScheduleService.initWeeklySchedule();
        model.addAttribute("weeklySchedule", weeklySchedule);
        return "timetable/weeklySchedule";
    }
}
