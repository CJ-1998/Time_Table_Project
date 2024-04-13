package com.mysideproject.TimeTableProject.controller;

import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import com.mysideproject.TimeTableProject.repository.WeeklyScheduleRepository;
import com.mysideproject.TimeTableProject.service.WeeklyScheduleService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class WeeklyScheduleController {

    private final WeeklyScheduleService weeklyScheduleService;
    private final WeeklyScheduleRepository weeklyScheduleRepository;

    // 전체 주간 계획표 화면에서 링크 클릭 시 들어오는 요청
    // 요청에 startDate 붙여서 요청 보냄
    // 같이 온 startDate를 가지고 주간 계획표 찾아서 Model에 담아 주간 계획표 화면으로 이동.
    @GetMapping("/timetable/{startDate}")
    public String showWeeklySchedule(@PathVariable("startDate") LocalDate startDate, Model model) {
        WeeklySchedule weeklySchedule = weeklyScheduleRepository.findByStartDate(startDate);
        model.addAttribute("weeklySchedule", weeklySchedule);
        return "timetable/weeklySchedule";
    }

    // 입력된 주간 계획표 저장 버튼 클릭 시 들어오는 요청
    // 주간 계획표 저장하는 요청
    // 주간 계획표 저장 후 다시 주간 계획표 화면 보여줌
    @PostMapping("/timetable/save")
    public String saveWeeklySchedule(@ModelAttribute WeeklyScheduleDTO weeklyScheduleDTO,
                                     RedirectAttributes redirectAttributes) {
        // 받은 데이터 서비스로 넘겨 저장 후 주간 계획표 넘김
        WeeklySchedule weeklySchedule = weeklyScheduleService.saveWeeklySchedule(weeklyScheduleDTO);
        redirectAttributes.addAttribute("startDate", weeklySchedule.getStartDate());
        return "redirect:/timetable/{startDate}";
    }

    // 비어 있는 주간 계획표를 보여주는 컨트롤러.
    // 추후에는 날짜에 따라 주간 계획표 찾아 Model에 담아 보낼 것이라 사용되지 않을 컨트롤러.
    @GetMapping("/timetable")
    public String showFirstWeeklySchedule(Model model) {
        WeeklySchedule weeklySchedule = weeklyScheduleService.initWeeklySchedule(LocalDate.of(2024, 4, 1));
        model.addAttribute("weeklySchedule", weeklySchedule);
        return "timetable/weeklySchedule";
    }

}
