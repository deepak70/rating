package com.ates.rating.app.service.impl;

import com.ates.rating.app.repository.DepartmentRepository;
import com.ates.rating.app.service.DepartmentService;
import com.ates.rating.app.viewmodel.DepartmentListVM;
import com.ates.rating.app.viewmodel.DepartmentVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentListVM getDepartment() {
        var departmentEntities = departmentRepository.findAll();
        var departmentVMS = departmentEntities.stream()
                .filter(departmentEntity -> !Objects.equals("NA", departmentEntity.getDepartment()))
                .map(departmentEntity -> new DepartmentVM()
                        .setDepartment(departmentEntity.getDepartment())
                        .setId(departmentEntity.getId())
                ).collect(Collectors.toList());
        return new DepartmentListVM().setDepartmentVMS(departmentVMS);

    }
}
