package com.smartbear.core;

import com.smartbear.util.ClockTime;
import com.smartbear.util.NumberWords;
import com.smartbear.util.SpokenTimeFormatter;

public final class DigitalBritishFormatter implements SpokenTimeFormatter {

    @Override
    public String speak(ClockTime t) {
        if (t.isMidnight()) return "midnight";
        if (t.isNoon()) return "noon";

        String hour = NumberWords.hourWord(t.hour12());
        int m = t.minute();
        if (m == 0) return hour + " o'clock";
        return hour + " " + NumberWords.minuteWordDigital(m);
    }
}
