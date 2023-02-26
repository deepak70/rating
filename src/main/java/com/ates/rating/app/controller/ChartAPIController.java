package com.ates.rating.app.controller;

import com.ates.rating.app.GenericResponse;
import com.ates.rating.app.repository.SemesterRepository;
import com.ates.rating.app.security.security.services.UserDetailsImpl;
import com.ates.rating.app.service.ChartService;
import com.ates.rating.app.viewmodel.ChartDataVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/chart")
@Slf4j
@RequiredArgsConstructor
public class ChartAPIController {
    private final SemesterRepository semesterRepository;

    private final ChartService chartService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GenericResponse<ChartDataVM> getChart(@RequestParam String chartType,
                                                 @RequestParam Integer year,
                                                 @RequestParam Long semesterId,
                                                 @RequestParam Long departmentId,
                                                 @RequestParam Long classId,
                                                 Principal principal) {
        var user = (UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        var data = chartService.getChartData(chartType, year, semesterId, classId, departmentId);
        return GenericResponse.<ChartDataVM>builder()
                .success(data != null)
                .data(data)
                .build();
    }
}
