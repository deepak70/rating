package com.ates.rating.app.service;

import com.ates.rating.app.viewmodel.ChartDataVM;

public interface ChartService {

    ChartDataVM getChartData(String chartType, Integer year);
}
