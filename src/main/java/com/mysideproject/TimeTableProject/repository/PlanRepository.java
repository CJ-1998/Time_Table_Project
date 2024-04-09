package com.mysideproject.TimeTableProject.repository;

import com.mysideproject.TimeTableProject.domain.Plan;
import java.time.LocalDate;
import java.util.List;

public interface PlanRepository {

    Plan save(Plan plan);

    List<Plan> findByDate(LocalDate date);

    List<Plan> findByPlanContent(String planContent);
}
