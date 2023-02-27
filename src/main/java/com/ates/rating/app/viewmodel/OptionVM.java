package com.ates.rating.app.viewmodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class OptionVM {
    @NotNull(message = "Year should not be blank")
    private Integer year;
    private Long semesterId;
    private Long classId;
    private Long departmentId;
    private List<SubjectUserOptionVM> subjectUserOptionVMS;

}
