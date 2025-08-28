package com.smartbear.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClockTimeTest {

    @Test
    void parse_ShouldReturnCorrectClockTime_ForValidTime() {
        ClockTime result = ClockTime.parse("12:30");
        assertNotNull(result);
        assertEquals(12, result.hour24());
        assertEquals(30, result.minute());
    }

    @ParameterizedTest
    @CsvSource({
            "00:00, 0, 0",
            "12:00, 12, 0",
            "23:59, 23, 59",
            "01:05, 1, 5",
            "09:45, 9, 45"
    })
    void parse_ShouldHandleVariousValidTimes(String timeString, int expectedHour, int expectedMinute) {
        ClockTime result = ClockTime.parse(timeString);
        assertNotNull(result);
        assertEquals(expectedHour, result.hour24());
        assertEquals(expectedMinute, result.minute());
    }

    @Test
    void nextHour12_ShouldReturnCorrectNextHour() {
        assertEquals(1, new ClockTime(0, 0).nextHour12());
        assertEquals(2, new ClockTime(1, 0).nextHour12());
        assertEquals(1, new ClockTime(12, 0).nextHour12());
        assertEquals(2, new ClockTime(13, 0).nextHour12());
        assertEquals(12, new ClockTime(23, 0).nextHour12());
    }
}
