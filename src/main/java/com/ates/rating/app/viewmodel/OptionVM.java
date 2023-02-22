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
    @NotNull(message = "Semester should not be blank")
    private Long semesterId;
    private List<SaveOptionVM> saveOptionVMS;
}
