package com.ates.rating.app.viewmodel;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ChartDataVM {
    private List<String> options;
    private List<ChartVM> chartVMS;

}
