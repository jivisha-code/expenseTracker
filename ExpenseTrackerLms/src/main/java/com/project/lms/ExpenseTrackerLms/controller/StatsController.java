package com.project.lms.ExpenseTrackerLms.controller;

import com.project.lms.ExpenseTrackerLms.dto.GraphDTO;
import com.project.lms.ExpenseTrackerLms.services.stats.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
//@CrossOrigin("*")

public class StatsController {
    private final StatsService statsService;


    @GetMapping("/chart/{userId}")
    public ResponseEntity<GraphDTO> getChartDetails(@PathVariable Long userId){
        return ResponseEntity.ok(statsService.getChartData(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getStats(@PathVariable Long userId){
        return ResponseEntity.ok(statsService.getStats(userId));
    }
}
