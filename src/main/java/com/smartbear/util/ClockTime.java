package com.smartbear.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public record ClockTime(int hour24, int minute) {
    private static final DateTimeFormatter HH_MM = DateTimeFormatter.ofPattern("HH:mm");

    public static ClockTime parse(String hhmm) {
        try {
            var t = LocalTime.parse(hhmm, HH_MM);
            return new ClockTime(t.getHour(), t.getMinute());
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("time must be in HH:mm (00-23:00-59)");
        }
    }

    public int hour12() {
        int h = hour24 % 12;
        return h == 0 ? 12 : h;
    }

    public int nextHour12() {
        int nh = (hour24 + 1) % 24;
        nh = nh % 12;
        return nh == 0 ? 12 : nh;
    }

    public boolean isNoon()     { return hour24 == 12 && minute == 0; }
    public boolean isMidnight() { return hour24 == 0  && minute == 0; }
}
