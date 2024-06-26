package com.mysideproject.TimeTableProject.service;

import static com.mysideproject.TimeTableProject.service.DateTimeConstants.DAY_COUNT;
import static com.mysideproject.TimeTableProject.service.DateTimeConstants.HOUR_COUNT;
import static com.mysideproject.TimeTableProject.service.DateTimeConstants.INTERVAL_MINUTE;
import static com.mysideproject.TimeTableProject.service.DateTimeConstants.INTERVAL_PER_HOUR;
import static com.mysideproject.TimeTableProject.service.DateTimeConstants.START_HOUR;
import static com.mysideproject.TimeTableProject.service.DateTimeConstants.START_MINUTE;

import com.mysideproject.TimeTableProject.DTO.DailyScheduleDTO;
import com.mysideproject.TimeTableProject.DTO.PlanDTO;
import com.mysideproject.TimeTableProject.DTO.WeeklyScheduleDTO;
import com.mysideproject.TimeTableProject.domain.DailySchedule;
import com.mysideproject.TimeTableProject.domain.Plan;
import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import com.mysideproject.TimeTableProject.repository.DailyScheduleRepository;
import com.mysideproject.TimeTableProject.repository.PlanRepository;
import com.mysideproject.TimeTableProject.repository.WeeklyScheduleRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeeklyScheduleService {

    private final WeeklyScheduleRepository weeklyScheduleRepository;
    private final DailyScheduleRepository dailyScheduleRepository;
    private final PlanRepository planRepository;

    //주간 계획표 초기화 메서드
    public WeeklySchedule initWeeklySchedule(LocalDate date) {
        // 초기 날짜 설정
//        LocalDate startDate = LocalDate.of(2024, 4, 1);
        LocalDate startDate = date;

        // 초기 날짜 + 6일 후 날짜 설정
        LocalDate endDate = startDate.plusDays(DAY_COUNT - 1);

        // 일간 계획표 리스트 생성
        List<DailySchedule> weeklyPlans = getWeeklyPlans(startDate);

        // 주간 계획표 객체 생성 및 반환
        WeeklySchedule weeklySchedule = new WeeklySchedule(startDate, endDate, weeklyPlans);

        // 주간 계획표 저장소에 주간 계획표 객체 저장
        weeklyScheduleRepository.save(weeklySchedule);

        return weeklySchedule;
    }

    // 일간 계획표 리스트 생성 메서드
    private List<DailySchedule> getWeeklyPlans(LocalDate date) {
        // 일간 계획표 리스트 생성
        List<DailySchedule> weeklyPlans = new ArrayList<>();

        // 시작 날짜의 일(dayOfMonth)을 기준으로 DAY_COUNT(7)일 동안 반복하여 일간 계획표 생성
        int dayOfMonth = date.getDayOfMonth();

        for (int day = 0; day < DAY_COUNT; day++) {
            // 날짜 내가 직접 더해서 생성해서 오류 발생!!!!!!!!!
            // LocalDate newDate = date.withDayOfMonth(dayOfMonth + day);

            // 날짜 더할 때에는 plusDays 메서드 사용해야 함.
            LocalDate newDate = date.plusDays(day);

            //일간 계획표 초기화
            DailySchedule dailySchedule = initDailySchedule(newDate);
            weeklyPlans.add(dailySchedule); //일간 계획표 리스트에 일간 계획표 추가
        }

        return weeklyPlans;
    }

    //일간 계획표 초기화 메서드
    private DailySchedule initDailySchedule(LocalDate date) {
        // 시간별 계획 리스트 생성
        List<Plan> dailyPlans = getDailyPlans(date);

        // 일간 계획표의 요일 획득
        DayOfWeek weekDay = date.getDayOfWeek();

        // 일간 계획표 객체 생성 및 반환
        DailySchedule dailySchedule = new DailySchedule(date, weekDay, dailyPlans);

        // 일간 계획표 저장소에 일간 계획표 객체 저장
        dailyScheduleRepository.save(dailySchedule);

        return dailySchedule;
    }

    // 시간별 계획 생성 메서드
    private List<Plan> getDailyPlans(LocalDate date) {
        // 시간별 계획 리스트 생성
        List<Plan> dailyPlans = new ArrayList<>();

        // 시간 간격만큼 반복해 시간별 계획 생성
        for (LocalTime startTime : getTimeIntervals()) {
            LocalTime endTime = startTime.plusMinutes(INTERVAL_MINUTE);

            // Plan 객체 생성 (planContent는 null로 설정)
            Plan plan = new Plan(0L, date, startTime, endTime, "");
            dailyPlans.add(plan);   // 일간 계획표에 시간별 계획 추가

            // 계획 저장소에 계획 객체 저장
            planRepository.save(plan);
        }

        return dailyPlans;
    }

    // 시간 간격을 반환하는 유틸리티 메서드
    private List<LocalTime> getTimeIntervals() {
        // 시간 간격 리스트 생성
        List<LocalTime> timeIntervals = new ArrayList<>();

        // 시작 시간 설정 (0시 0분)
        LocalTime startTime = LocalTime.of(START_HOUR, START_MINUTE);

        // 시간 간격만큼 반복하여 시간 리스트에 추가
        for (int timeCount = 0; timeCount < HOUR_COUNT * INTERVAL_PER_HOUR; timeCount++) {
            timeIntervals.add(startTime);
            startTime = startTime.plusMinutes(INTERVAL_MINUTE);
        }

        return timeIntervals;   // 시간 간격 리스트 반환
    }


    public WeeklySchedule saveWeeklySchedule(WeeklyScheduleDTO weeklyScheduleDTO) {
        WeeklySchedule convertedWeeklySchedule = convertWeeklyScheduleDTO(weeklyScheduleDTO);
        return weeklyScheduleRepository.update(convertedWeeklySchedule);
    }

    // WeeklyScheduleDTO를 WeeklySchedule로 변환하는 메서드
    private WeeklySchedule convertWeeklyScheduleDTO(WeeklyScheduleDTO weeklyScheduleDTO) {
        LocalDate startDate = weeklyScheduleDTO.getStartDate();
        LocalDate endDate = startDate.plusDays(DAY_COUNT - 1);
        List<DailySchedule> weeklyPlans = new ArrayList<>();

        int count = 0;
        for (DailyScheduleDTO dailyScheduleDTO : weeklyScheduleDTO.getWeeklyPlans()) {
            LocalDate date = startDate.plusDays(count++);
            DailySchedule convertedDailySchedule = convertDailyScheduleDTO(date, dailyScheduleDTO);
            weeklyPlans.add(convertedDailySchedule);
        }

        return new WeeklySchedule(startDate, endDate, weeklyPlans);
    }

    // DayilyScheduleDTO를 DailySchedule로 변환하는 메서드
    private DailySchedule convertDailyScheduleDTO(LocalDate date, DailyScheduleDTO dailyScheduleDTO) {
        DayOfWeek weekDay = date.getDayOfWeek();
        List<Plan> dailyPlans = new ArrayList<>();

        for (PlanDTO planDTO : dailyScheduleDTO.getDailyPlans()) {
            Plan convertedPlan = convertPlanDTO(date, planDTO);
            dailyPlans.add(convertedPlan);
        }

        return new DailySchedule(date, weekDay, dailyPlans);
    }

    // PlanDTO를 Plan로 변환하는 메서드
    private Plan convertPlanDTO(LocalDate date, PlanDTO planDTO) {
        return new Plan(0L, date, null, null, planDTO.getPlanContent());
    }
}
