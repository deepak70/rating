package com.ates.rating.app.viewmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectUserOptionVM {
    private Long subjectId;
    private List<SaveOptionVM> saveOptionVMS;
}
