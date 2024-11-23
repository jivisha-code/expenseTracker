package com.project.lms.ExpenseTrackerLms.services.stats;

import com.project.lms.ExpenseTrackerLms.dto.GraphDTO;
import com.project.lms.ExpenseTrackerLms.dto.StatsDTO;

public interface StatsService {

    GraphDTO getChartData(Long userId);

    StatsDTO getStats(Long userId);
}
