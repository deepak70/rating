package com.ates.rating.app.security.security.services;

import com.ates.rating.app.exception.handler.AppException;
import com.ates.rating.app.model.User;
import com.ates.rating.app.repository.ClassEntityRepository;
import com.ates.rating.app.repository.DepartmentRepository;
import com.ates.rating.app.repository.UserRepository;
import com.ates.rating.app.viewmodel.UpdateStudentVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ClassEntityRepository classEntityRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    @Transactional
    public Boolean updateUserInfo(UpdateStudentVM studentVM, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("User not found " + userId));
        var department = departmentRepository.findById(studentVM.getDepartmentId())
                .orElseThrow(() -> new AppException("Department not found"));
        var classEntity = classEntityRepository.findById(studentVM.getClassId())
                .orElseThrow(() -> new AppException("Class not found"));
        user.setStudentClass(classEntity)
                .setDepartment(department);
        return true;

    }
}
