package com.ates.rating.app.service.impl;

import com.ates.rating.app.service.YearService;
import com.ates.rating.app.viewmodel.Year;
import com.ates.rating.app.viewmodel.YearVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class YearServiceImpl implements YearService {
    @Override
    public YearVM getYearVm() {
        var currentDate = LocalDate.now().minusYears(5);
        List<Year> years = new ArrayList<>();
        for (int i = 1; i < 25; i++) {
            years.add(new Year().setYear(currentDate.plusYears(i).getYear()));
        }
        return new YearVM().setYear(years);
    }
}
