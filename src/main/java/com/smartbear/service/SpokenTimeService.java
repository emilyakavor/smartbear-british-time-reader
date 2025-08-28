package com.smartbear.service;

import com.smartbear.util.ClockTime;
import com.smartbear.util.Style;
import com.smartbear.core.ColloquialBritishFormatter;
import com.smartbear.core.DigitalBritishFormatter;
import com.smartbear.util.SpokenTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class SpokenTimeService {

    private final SpokenTimeFormatter colloquial = new ColloquialBritishFormatter();
    private final SpokenTimeFormatter digital    = new DigitalBritishFormatter();

    public String speak(String hhmm, Style style) {
        var time = ClockTime.parse(hhmm);
        return switch (style) {
            case COLLOQUIAL -> colloquial.speak(time);
            case DIGITAL -> digital.speak(time);
        };
    }
}
