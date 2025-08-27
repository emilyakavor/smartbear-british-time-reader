package com.smartbear.core;

import com.smartbear.util.ClockTime;
import com.smartbear.util.NumberWords;
import com.smartbear.util.SpokenTimeFormatter;

public final class ColloquialBritishFormatter implements SpokenTimeFormatter {

    @Override
    public String speak(ClockTime t) {
        if (t.isMidnight()) return "midnight";
        if (t.isNoon()) return "noon";

        int m = t.minute();
        String hour = NumberWords.hourWord(t.hour12());

        if (m == 0) return hour + " o'clock";
        if (m == 15) return "quarter past " + hour;
        if (m == 30) return "half past " + hour;
        if (m < 30)  return NumberWords.minuteWord(m) + " past " + hour;

        // minutes > 30
        int to = 60 - m;
        String nextHour = NumberWords.hourWord(t.nextHour12());
        if (m == 45) return "quarter to " + nextHour;
        return NumberWords.minuteWord(to) + " to " + nextHour;
    }
}

