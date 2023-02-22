package com.ates.rating.app.controller;

import com.ates.rating.app.GenericResponse;
import com.ates.rating.app.security.security.services.UserDetailsImpl;
import com.ates.rating.app.security.security.services.UserDetailsServiceImpl;
import com.ates.rating.app.service.*;
import com.ates.rating.app.viewmodel.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/common")
public class CommonAPIController {

    private final SemesterService semesterService;
    private final ClassService classService;
    private final DepartmentService departmentService;
    private final EligibilityService eligibilityService;
    private final YearService yearService;
    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping("/get-semester")
    public GenericResponse<SemesterVM> getSemesters() {
        var semesters = semesterService.getSemesters();
        return GenericResponse.<SemesterVM>builder()
                .data(semesters)
                .success(Objects.nonNull(semesters))
                .build();
    }

    @GetMapping("/get-class")
    public GenericResponse<ClassList> getClassList() {
        var classList = classService.getClassList();
        return GenericResponse.<ClassList>builder()
                .data(classList)
                .build();
    }

    @GetMapping("/get-department")
    public GenericResponse<DepartmentListVM> listVMResponseEntity() {
        return GenericResponse.<DepartmentListVM>builder()
                .success(true)
                .data(departmentService.getDepartment())
                .build();
    }

    @GetMapping("/check-eligibility")
    public GenericResponse<Boolean> checkEligibility(@RequestParam String eligibilityNumber,
                                                     @RequestParam String userType,
                                                     Principal principal) {
        var user = (UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        var eligibility = eligibilityService.isValidEligibility(eligibilityNumber, userType);
        return GenericResponse.<Boolean>builder()
                .success(eligibility)
                .data(eligibility)
                .message(eligibility ? "" : "Invalid eligibility number")
                .build();


    }

    @GetMapping("/get-year")
    public GenericResponse<YearVM> getYears(Principal principal) {
        var data = yearService.getYearVm();
        return GenericResponse.<YearVM>builder()
                .data(data)
                .success(true)
                .build();
    }

    @PostMapping("/stud-info")
    public GenericResponse<Boolean> updateStudent(@RequestBody UpdateStudentVM studentVM,
                                                  Principal principal) {
        var user = (UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        var data = userDetailsService.updateUserInfo(studentVM, user.getId());
        return GenericResponse.<Boolean>builder()
                .data(data)
                .success(data)
                .build();
    }
}
