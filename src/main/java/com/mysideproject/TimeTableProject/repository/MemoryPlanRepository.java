package com.mysideproject.TimeTableProject.repository;

import com.mysideproject.TimeTableProject.domain.Plan;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryPlanRepository implements PlanRepository {

    // 계획 저장하는 리스트
    private static final List<Plan> plans = new ArrayList<>();

    // 고유 식별자를 생성하기 위한 시퀀스 
    private static long sequence = 0L;

    // 계획 저장 메서드
    @Override
    public Plan save(Plan plan) {
        // 고유 식별자를 설정하고 리스트에 추가
        plan.setId(++sequence);
        plans.add(plan);
        return plan;
    }

    // 특정 날짜와 일치하는 계획을 검색하는 메서드
    @Override
    public List<Plan> findByDate(LocalDate date) {
        List<Plan> datePlans = new ArrayList<>();

        // 저장된 모든 계획을 반복하며 해당 날짜와 일치하는 계획을 찾음
        for (int index = 0; index < plans.size(); index++) {
            Plan onePlan = plans.get(index);
            LocalDate planDate = onePlan.getDate();

            if (planDate.isEqual(date)) {
                datePlans.add(onePlan);
            }
        }

        return datePlans;
    }

    // 특정 계획 내용과 일치하는 계획을 검색하는 메서드 
    @Override
    public List<Plan> findByPlanContent(String planContent) {
        List<Plan> contentPlans = new ArrayList<>();

        // 저장된 모든 계획을 반복하며 계획 내용과 입력된 내용이 일치하는 계획을 찾음
        for (int index = 0; index < plans.size(); index++) {
            Plan onePlan = plans.get(index);
            String content = onePlan.getPlanContent();

            // 계획 내용이 null이 아니고 입력된 내용과 일치하는 경우에만 리스트에 추가
            if (content != null && content.equals(planContent)) {
                contentPlans.add(onePlan);
            }
        }

        return contentPlans;
    }
}
