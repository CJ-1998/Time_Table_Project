package com.mysideproject.TimeTableProject.service;

import static com.mysideproject.TimeTableProject.service.DateTimeConstants.DAY_COUNT;
import static com.mysideproject.TimeTableProject.service.DateTimeConstants.INTERVAL_MINUTE;
import static com.mysideproject.TimeTableProject.service.DateTimeConstants.MINUTES_IN_HOUR;
import static com.mysideproject.TimeTableProject.service.DateTimeConstants.TOTAL_HOURS_IN_WEEK;

import com.mysideproject.TimeTableProject.domain.DailySchedule;
import com.mysideproject.TimeTableProject.domain.Plan;
import com.mysideproject.TimeTableProject.domain.PlanContentTime;
import com.mysideproject.TimeTableProject.domain.WeeklySchedule;
import com.mysideproject.TimeTableProject.repository.WeeklyScheduleRepository;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeCalculationService {

    private final WeeklyScheduleRepository weeklyScheduleRepository;

//    public List<PlanContentTime> calculateWeeklyScheduleTime(LocalDate startDate) {
//        List<PlanContentTime> planContentTimes;
//
//        WeeklySchedule weeklySchedule = weeklyScheduleRepository.findByStartDate(startDate);
//        List<DailySchedule> weeklyPlans = weeklySchedule.getWeeklyPlans();
//
//        Map<String, PlanContentTime> planContentHashMap = new HashMap<>();
//
//        int index = 0;
//        for (DailySchedule dailySchedule : weeklyPlans) {
//            List<Plan> dailyPlans = dailySchedule.getDailyPlans();
//
//            for (Plan plan : dailyPlans) {
//                String planContent = plan.getPlanContent();
//                if (planContentHashMap.containsKey(planContent)) {
//                    PlanContentTime planContentTime = planContentHashMap.get(planContent);
//                    addTimeInPlanContentTime(index, planContentTime);
//                } else {
//                    PlanContentTime planContentTime = initPlanContentTime(planContent);
//                    addTimeInPlanContentTime(index, planContentTime);
//                }
//            }
//            index++;
//        }
//
//        planContentTimes = new ArrayList<>(planContentHashMap.values());
//
//        return planContentTimes;
//    }

    // 주간 계획표 받아서 전체 시간을 계산하는 메서드
    public List<PlanContentTime> calculateWeeklyScheduleTime(LocalDate startDate) {

        WeeklySchedule weeklySchedule = weeklyScheduleRepository.findByStartDate(startDate);
        List<DailySchedule> weeklyPlans = weeklySchedule.getWeeklyPlans();

        Map<String, PlanContentTime> planContentHashMap = new HashMap<>();

        processWeeklyPlans(weeklyPlans, planContentHashMap);

        List<PlanContentTime> planContentTimes = new ArrayList<>(planContentHashMap.values());

        finalizePlanContentTimes(planContentTimes);

        return planContentTimes;
    }

    // 일간 계획표마다 시간 계산하는 메서드
    private void processWeeklyPlans(List<DailySchedule> weeklyPlans, Map<String, PlanContentTime> planContentHashMap) {
        int index = 0;
        for (DailySchedule dailySchedule : weeklyPlans) {
            processDailyPlans(dailySchedule.getDailyPlans(), index, planContentHashMap);
            index++;
        }
    }

    // 각 계획마다 planContent 보고 Map에 있는지 없는지 확인해서 없으면 PlanContentTime 생성하고 있으면 시간 더하는 메서드
    private void processDailyPlans(List<Plan> dailyPlans, int index, Map<String, PlanContentTime> planContentHashMap) {
        for (Plan plan : dailyPlans) {
            String planContent = plan.getPlanContent();
            if (planContent == null || planContent.equals("")) {
                planContent = "계획 없음";
            }
            PlanContentTime planContentTime = planContentHashMap.getOrDefault(planContent,
                    initPlanContentTime(planContent));
            addTimeInPlanContentTime(index, planContentTime);
            planContentHashMap.put(planContent, planContentTime);
        }
    }

    // PlanContentTime 초기화 하는 메서드
    private PlanContentTime initPlanContentTime(String planContent) {
        Duration totalHoursInWeek = Duration.ofMinutes(0);
        Double totalPercentInWeek = 0.0;
        List<Duration> hoursPerDayOfWeek = new ArrayList<>();

        for (int i = 0; i < DAY_COUNT; i++) {
            Duration duration = Duration.ofMinutes(0);
            hoursPerDayOfWeek.add(duration);
        }

        return new PlanContentTime(planContent, totalHoursInWeek, totalPercentInWeek, hoursPerDayOfWeek);
    }

    // PlanContentTime의 리스트에 특정 날짜를 의미하는 index에 시간 더하는 메서드
    private void addTimeInPlanContentTime(int index, PlanContentTime planContentTime) {
        List<Duration> hoursPerDayOfWeek = planContentTime.getHoursPerDayOfWeek();
        Duration hoursInDay = hoursPerDayOfWeek.get(index);
        hoursInDay.plusMinutes(INTERVAL_MINUTE);
    }

    // 각 PlanContentTime의 전체 시간, 퍼센트를 계산하는 메서드
    private void finalizePlanContentTimes(List<PlanContentTime> planContentTimes) {
        for (PlanContentTime planContentTime : planContentTimes) {
            calculateTimeInPlanContentTime(planContentTime);
        }
    }

    // 각 planContent의 총 시간 계산하는 메서드
    private void calculateTimeInPlanContentTime(PlanContentTime planContentTime) {
        Duration totalTime = Duration.ofMinutes(0);
        List<Duration> hoursPerDayOfWeek = planContentTime.getHoursPerDayOfWeek();

        for (Duration duration : hoursPerDayOfWeek) {
            totalTime.plus(duration);
        }

        planContentTime.setTotalHoursInWeek(totalTime);

        Double totalPercentInWeek = calculatePercentInPlanContentTime(planContentTime.getTotalHoursInWeek());

        planContentTime.setTotalPercentInWeek(totalPercentInWeek);
    }

    // 각 planContent의 퍼센트를 계산하는 메서드
    private Double calculatePercentInPlanContentTime(Duration totalHoursInWeek) {
        Double hours = totalHoursInWeek.toMinutes() / (double) MINUTES_IN_HOUR;

        return (hours / (double) TOTAL_HOURS_IN_WEEK) * 100;
    }
}
