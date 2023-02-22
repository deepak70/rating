package com.ates.rating.app.service.impl;

import com.ates.rating.app.repository.SemesterRepository;
import com.ates.rating.app.service.SemesterService;
import com.ates.rating.app.viewmodel.Semester;
import com.ates.rating.app.viewmodel.SemesterVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SemesterServiceImpl implements SemesterService {
    private final SemesterRepository semesterRepository;

    @Override
    public SemesterVM getSemesters() {
        var semesterEntities = semesterRepository.findAll();
        var semesters = semesterEntities.stream()
                .filter(semesterEntity -> !Objects.equals("NA", semesterEntity.getSemester()))
                .map(semesterEntity -> new Semester()
                        .setSemester(semesterEntity.getSemester())
                        .setSemesterId(semesterEntity.getId()))
                .collect(Collectors.toList());
        return new SemesterVM()
                .setSemesters(semesters);
    }
}
