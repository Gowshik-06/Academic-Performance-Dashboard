package com.apda.apda_backend.controller;

import com.apda.apda_backend.dto.PerformanceStatusDTO;
import com.apda.apda_backend.service.PerformanceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    // 🔐 STUDENT – view own performance
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/me")
    public PerformanceStatusDTO getMyPerformance() {
        return performanceService.getMyPerformance();
    }
}
