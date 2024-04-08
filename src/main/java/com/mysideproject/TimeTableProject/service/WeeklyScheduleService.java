package com.mysideproject.TimeTableProject.service;

import com.mysideproject.TimeTableProject.domain.DailySchedule;
import com.mysideproject.TimeTableProject.domain.Plan;
import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WeeklyScheduleService {

    // 상수 정의
    public static final int DAY_COUNT = 7;
    public static final int START_HOUR = 0;
    public static final int START_MINUTE = 0;
    public static final int HOUR_COUNT = 24;
    public static final int INTERVAL_MINUTE = 30;

    //주간 계획표 초기화 메서드
    public WeeklySchedule initWeeklySchedule() {
        // 초기 날짜 설정
        LocalDate startDate = LocalDate.of(2024, 4, 1);

        // 초기 날짜 + 6일 후 날짜 설정
        LocalDate endDate = startDate.plusDays(DAY_COUNT - 1);

        // 일간 계획표 리스트 생성
        List<DailySchedule> weeklyPlans = getWeeklyPlans(startDate);

        // 주간 계획표 객체 생성 및 반환
        WeeklySchedule weeklySchedule = new WeeklySchedule(startDate, endDate, weeklyPlans);
        return weeklySchedule;
    }

    // 일간 계획표 리스트 생성 메서드
    private List<DailySchedule> getWeeklyPlans(LocalDate date) {
        // 일간 계획표 리스트 생성
        List<DailySchedule> weeklyPlans = new ArrayList<>();

        // 시작 날짜의 일(dayOfMonth)을 기준으로 DAY_COUNT(7)일 동안 반복하여 일간 계획표 생성
        int dayOfMonth = date.getDayOfMonth();

        for (int day = 0; day < DAY_COUNT; day++) {
            LocalDate newDate = date.withDayOfMonth(dayOfMonth + day);

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
            Plan plan = new Plan(0L, date, startTime, endTime, null);
            dailyPlans.add(plan);   // 일간 계획표에 시간별 계획 추가
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
        for (int timeCount = 0; timeCount < HOUR_COUNT * 2; timeCount++) {
            timeIntervals.add(startTime);
            startTime = startTime.plusMinutes(INTERVAL_MINUTE);
        }

        return timeIntervals;   // 시간 간격 리스트 반환
    }


}
