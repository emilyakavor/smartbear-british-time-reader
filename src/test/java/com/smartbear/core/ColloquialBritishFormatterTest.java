package com.smartbear.core;

import com.smartbear.util.ClockTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColloquialBritishFormatterTest {

    private final ColloquialBritishFormatter formatter = new ColloquialBritishFormatter();

    @ParameterizedTest
    @CsvSource({
            "02:05, five past two",
            "03:10, ten past three",
            "05:20, twenty past five",
            "06:25, twenty five past six"
    })
    void speak_ShouldReturnPastTimes_Correctly(String inputTime, String expectedOutput) {
        // Arrange
        ClockTime time = ClockTime.parse(inputTime);

        // Act
        String result = formatter.speak(time);

        // Assert
        assertEquals(expectedOutput, result);
    }

    @Test
    void speak_ShouldHandleAllHourValues() {
        // Test all hours to ensure proper 12-hour conversion
        assertEquals("one o'clock", formatter.speak(ClockTime.parse("01:00")));
        assertEquals("two o'clock", formatter.speak(ClockTime.parse("02:00")));
        assertEquals("three o'clock", formatter.speak(ClockTime.parse("03:00")));
        assertEquals("four o'clock", formatter.speak(ClockTime.parse("04:00")));
        assertEquals("five o'clock", formatter.speak(ClockTime.parse("05:00")));
        assertEquals("six o'clock", formatter.speak(ClockTime.parse("06:00")));
        assertEquals("seven o'clock", formatter.speak(ClockTime.parse("07:00")));
        assertEquals("eight o'clock", formatter.speak(ClockTime.parse("08:00")));
        assertEquals("nine o'clock", formatter.speak(ClockTime.parse("09:00")));
        assertEquals("ten o'clock", formatter.speak(ClockTime.parse("10:00")));
        assertEquals("eleven o'clock", formatter.speak(ClockTime.parse("11:00")));
        assertEquals("noon", formatter.speak(ClockTime.parse("12:00")));

        // PM hours
        assertEquals("one o'clock", formatter.speak(ClockTime.parse("13:00")));
        assertEquals("two o'clock", formatter.speak(ClockTime.parse("14:00")));
        assertEquals("three o'clock", formatter.speak(ClockTime.parse("15:00")));
        assertEquals("four o'clock", formatter.speak(ClockTime.parse("16:00")));
        assertEquals("five o'clock", formatter.speak(ClockTime.parse("17:00")));
        assertEquals("six o'clock", formatter.speak(ClockTime.parse("18:00")));
        assertEquals("seven o'clock", formatter.speak(ClockTime.parse("19:00")));
        assertEquals("eight o'clock", formatter.speak(ClockTime.parse("20:00")));
        assertEquals("nine o'clock", formatter.speak(ClockTime.parse("21:00")));
        assertEquals("ten o'clock", formatter.speak(ClockTime.parse("22:00")));
        assertEquals("eleven o'clock", formatter.speak(ClockTime.parse("23:00")));
    }
}
