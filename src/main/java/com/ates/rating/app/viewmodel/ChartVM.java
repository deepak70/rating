package com.ates.rating.app.viewmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Map;

@Data
public class ChartVM {
    private String question;
    private Long questionId;
    private OptionWeightVM optionWeightVM;
    @JsonIgnore
    private Map<String, Integer> optionWeightVMS;
}
