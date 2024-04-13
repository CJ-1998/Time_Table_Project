package com.mysideproject.TimeTableProject.controller;

import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import com.mysideproject.TimeTableProject.repository.WeeklyScheduleRepository;
import com.mysideproject.TimeTableProject.service.WeeklyScheduleService;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AllWeeklyScheduleController {

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
    @GetMapping("/timetable/new")
    public String newTimeTable() {
        return "timetable/addWeeklySchedule";
    }

    // 주간 계획표 추가 화면에서 전송 버튼 누르면 들어오는 요청.
    // 날짜 보내서 이 날짜 가지고 새로운 주간 계획표 생성 후 전체 주간 계획표 화면으로 리다이렉트.
    @PostMapping("/timetable/add")
    public String addTimeTable(@RequestParam("date") LocalDate date) {
        WeeklySchedule weeklySchedule = weeklyScheduleService.initWeeklySchedule(date);
        return "redirect:/timetables";
    }

    @PostConstruct
    public void init() {
        LocalDate date = LocalDate.of(1998, 1, 1);
        weeklyScheduleService.initWeeklySchedule(date);
    }
}
