package com.smartbear.core;

import com.smartbear.util.ClockTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitalBritishFormatterTest {

    private final DigitalBritishFormatter formatter = new DigitalBritishFormatter();

    @ParameterizedTest
    @CsvSource({
            "01:00, one o'clock",
            "02:00, two o'clock",
            "03:00, three o'clock",
            "04:00, four o'clock",
            "05:00, five o'clock",
            "06:00, six o'clock",
            "07:00, seven o'clock",
            "08:00, eight o'clock",
            "09:00, nine o'clock",
            "10:00, ten o'clock",
            "11:00, eleven o'clock",
            "12:00, noon",
            "13:00, one o'clock",
            "14:00, two o'clock",
            "15:00, three o'clock",
            "16:00, four o'clock",
            "17:00, five o'clock",
            "18:00, six o'clock",
            "19:00, seven o'clock",
            "20:00, eight o'clock",
            "21:00, nine o'clock",
            "22:00, ten o'clock",
            "23:00, eleven o'clock"
    })
    void speak_ShouldReturnCorrectOClock_ForAllHours(String inputTime, String expectedOutput) {
        // Arrange
        ClockTime time = ClockTime.parse(inputTime);

        // Act
        String result = formatter.speak(time);

        // Assert
        assertEquals(expectedOutput, result);
    }

    @Test
    void speak_ShouldHandlePmTimesCorrectly_random_times() {
        // Test PM times (13:00-23:59)
        assertEquals("one oh five", formatter.speak(ClockTime.parse("13:05")));
        assertEquals("two ten", formatter.speak(ClockTime.parse("14:10")));
        assertEquals("three fifteen", formatter.speak(ClockTime.parse("15:15")));
        assertEquals("four twenty", formatter.speak(ClockTime.parse("16:20")));
        assertEquals("five twenty five", formatter.speak(ClockTime.parse("17:25")));
        assertEquals("six thirty", formatter.speak(ClockTime.parse("18:30")));
        assertEquals("seven thirty five", formatter.speak(ClockTime.parse("19:35")));
        assertEquals("eight forty", formatter.speak(ClockTime.parse("20:40")));
        assertEquals("nine forty five", formatter.speak(ClockTime.parse("21:45")));
        assertEquals("ten fifty", formatter.speak(ClockTime.parse("22:50")));
        assertEquals("eleven fifty five", formatter.speak(ClockTime.parse("23:55")));
    }
}
