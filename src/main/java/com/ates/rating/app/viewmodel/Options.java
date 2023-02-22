package com.ates.rating.app.viewmodel;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Options {
    private Long optionId;
    private BigDecimal percentage;
}
