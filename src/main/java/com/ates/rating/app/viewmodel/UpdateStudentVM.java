package com.ates.rating.app.viewmodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateStudentVM {
    private Long departmentId;
    private Long classId;
    private Long semesterId;
    private Long year;
}
