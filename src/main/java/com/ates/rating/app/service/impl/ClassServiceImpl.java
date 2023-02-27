package com.ates.rating.app.service.impl;

import com.ates.rating.app.exception.handler.AppException;
import com.ates.rating.app.repository.ClassEntityRepository;
import com.ates.rating.app.repository.DepartmentRepository;
import com.ates.rating.app.service.ClassService;
import com.ates.rating.app.viewmodel.ClassList;
import com.ates.rating.app.viewmodel.ClassVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClassServiceImpl implements ClassService {
    private final DepartmentRepository departmentRepository;

    private final ClassEntityRepository classEntityRepository;

    @Override
    public ClassList getClassList(Long departmentId) {

        var department = departmentRepository.findById(departmentId)
                .orElseThrow(()->new AppException("Department id not match "+departmentId));
        var classEntities = classEntityRepository.findAllByDepartmentEntity(department);
        var classVMList = classEntities.stream()
                .filter(classEntity -> !Objects.equals("NA", classEntity.getClassName()))
                .map(classEntity -> new ClassVM().setClassName(classEntity.getClassName())
                        .setDepartmentId(classEntity.getDepartmentEntity().getId())
                        .setId(classEntity.getId()))
                .toList();
        return new ClassList().setClassVM(classVMList);


    }
}
