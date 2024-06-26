package com.mysideproject.TimeTableProject.controller;

import com.mysideproject.TimeTableProject.DTO.WeeklyScheduleDTO;
import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import com.mysideproject.TimeTableProject.repository.WeeklyScheduleRepository;
import com.mysideproject.TimeTableProject.service.WeeklyScheduleService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

//    // DTO 개발 위해 테스트 해본 컨트롤러 1
//    @PostMapping("/timetable/save")
//    public String test(@RequestBody String temp) {
//        System.out.println(temp);
//        return "redirect:/timetables";
//    }

//    // DTO 개발 위해 테스트 해본 컨트롤러 2
//    @PostMapping("/timetable/save")
//    public String test(@ModelAttribute WeeklyScheduleDTO weeklyScheduleDTO) {
//        System.out.println(weeklyScheduleDTO.getStartDate());
//
//        int cnt = 1;
//        for (DailyScheduleDTO weeklyPlan : weeklyScheduleDTO.getWeeklyPlans()) {
//            int t = 1;
//            for (PlanDTO dailyPlan : weeklyPlan.getDailyPlans()) {
//                System.out.println(cnt + "일차 " + t + " " + dailyPlan.getPlanContent());
//                t++;
//            }
//            cnt++;
//            System.out.println("---------------");
//        }
//
//        return "redirect:/timetables";
//    }

    // 입력된 주간 계획표 저장 버튼 클릭 시 들어오는 요청
    // 주간 계획표 저장하는 요청
    // 주간 계획표 저장 후 다시 주간 계획표 화면 보여줌
    @PostMapping("/timetable/save")
    public String saveWeeklySchedule(@ModelAttribute WeeklyScheduleDTO weeklyScheduleDTO,
                                     RedirectAttributes redirectAttributes) {
        // 받은 데이터 서비스로 넘겨 저장 후 주간 계획표 넘김
        WeeklySchedule weeklySchedule = weeklyScheduleService.saveWeeklySchedule(weeklyScheduleDTO);

        // URL에 LocalDate 들어있으면 인코딩 시 이상하게 될 수 있어 아래처럼 format을 정해야 한다. 
        LocalDate startDate = weeklySchedule.getStartDate();
        redirectAttributes.addAttribute("startDate", startDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        //redirectAttributes.addAttribute("startDate", weeklySchedule.getStartDate());

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
