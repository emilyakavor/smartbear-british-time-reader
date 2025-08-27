package com.smartbear.util;

import com.smartbear.util.format.ColloquialBritishFormatter;
import com.smartbear.util.format.DigitalBritishFormatter;
import com.smartbear.util.format.SpokenTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class SpokenTimeService {

    private final SpokenTimeFormatter colloquial = new ColloquialBritishFormatter();
    private final SpokenTimeFormatter digital    = new DigitalBritishFormatter();

    public String speak(String hhmm, Style style) {
        var time = ClockTime.parse(hhmm);
        return switch (style) {
            case colloquial -> colloquial.speak(time);
            case digital    -> digital.speak(time);
        };
    }
}
