package com.ates.rating.app.viewmodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class SaveOptionVM {
    @NotNull(message = "Select option id not null")
    private Long selectedOptionId;
    @NotNull(message = "option id not null")
    private Long questionId;
}
