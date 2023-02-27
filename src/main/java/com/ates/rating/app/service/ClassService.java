package com.ates.rating.app.service;

import com.ates.rating.app.viewmodel.ClassList;

public interface ClassService {
    ClassList getClassList(Long departmentId);
}
