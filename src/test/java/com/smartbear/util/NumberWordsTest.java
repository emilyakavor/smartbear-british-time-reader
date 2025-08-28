package com.smartbear.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWordsTest {

    @Test
    void hourWord_ShouldReturnCorrectWord() {
        assertEquals("one", NumberWords.hourWord(1));
        assertEquals("two", NumberWords.hourWord(2));
        assertEquals("three", NumberWords.hourWord(3));
        assertEquals("four", NumberWords.hourWord(4));
        assertEquals("five", NumberWords.hourWord(5));
        assertEquals("six", NumberWords.hourWord(6));
        assertEquals("seven", NumberWords.hourWord(7));
        assertEquals("eight", NumberWords.hourWord(8));
        assertEquals("nine", NumberWords.hourWord(9));
        assertEquals("ten", NumberWords.hourWord(10));
        assertEquals("eleven", NumberWords.hourWord(11));
        assertEquals("twelve", NumberWords.hourWord(12));
    }

    @Test
    void hourWord_ShouldThrowException_ForInvalidHour() {
        assertThrows(IllegalArgumentException.class, () -> NumberWords.hourWord(0));
        assertThrows(IllegalArgumentException.class, () -> NumberWords.hourWord(13));
        assertThrows(IllegalArgumentException.class, () -> NumberWords.hourWord(-1));
    }

    @ParameterizedTest
    @CsvSource({
            "0, zero", "1, one", "2, two", "3, three", "4, four", "5, five",
            "6, six", "7, seven", "8, eight", "9, nine", "10, ten",
            "11, eleven", "12, twelve", "13, thirteen", "14, fourteen",
            "15, fifteen", "16, sixteen", "17, seventeen", "18, eighteen",
            "19, nineteen"
    })
    void minuteWord_ShouldReturnCorrectWord_For0To19(int minute, String expected) {
        assertEquals(expected, NumberWords.minuteWord(minute));
    }

    @ParameterizedTest
    @CsvSource({
            "20, twenty", "21, twenty one", "22, twenty two", "23, twenty three",
            "24, twenty four", "25, twenty five", "26, twenty six", "27, twenty seven",
            "28, twenty eight", "29, twenty nine", "30, thirty", "31, thirty one",
            "32, thirty two", "33, thirty three", "34, thirty four", "35, thirty five",
            "36, thirty six", "37, thirty seven", "38, thirty eight", "39, thirty nine",
            "40, forty", "41, forty one", "42, forty two", "43, forty three",
            "44, forty four", "45, forty five", "46, forty six", "47, forty seven",
            "48, forty eight", "49, forty nine", "50, fifty", "51, fifty one",
            "52, fifty two", "53, fifty three", "54, fifty four", "55, fifty five",
            "56, fifty six", "57, fifty seven", "58, fifty eight", "59, fifty nine"
    })
    void minuteWord_ShouldReturnCorrectWord_For20To59(int minute, String expected) {
        assertEquals(expected, NumberWords.minuteWord(minute));
    }

    @Test
    void minuteWord_ShouldThrowException_ForNegativeMinute() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> NumberWords.minuteWord(-1));
    }

    @Test
    void minuteWord_ShouldThrowException_ForMinuteAbove59() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> NumberWords.minuteWord(60));
    }

    @ParameterizedTest
    @CsvSource({
            "1, oh one", "2, oh two", "3, oh three", "4, oh four", "5, oh five",
            "6, oh six", "7, oh seven", "8, oh eight", "9, oh nine"
    })
    void minuteWordDigital_ShouldReturnOhPrefix_For1To9(int minute, String expected) {
        assertEquals(expected, NumberWords.minuteWordDigital(minute));
    }

    @ParameterizedTest
    @CsvSource({
            "10, ten", "11, eleven", "12, twelve", "13, thirteen", "14, fourteen",
            "15, fifteen", "16, sixteen", "17, seventeen", "18, eighteen", "19, nineteen",
            "20, twenty", "25, twenty five", "30, thirty", "45, forty five", "59, fifty nine"
    })
    void minuteWordDigital_ShouldReturnNormalWord_For10To59(int minute, String expected) {
        assertEquals(expected, NumberWords.minuteWordDigital(minute));
    }

    @Test
    void minuteWordDigital_ShouldReturnOClock_For0() {
        assertEquals("o'clock", NumberWords.minuteWordDigital(0));
    }

    @Test
    void minuteWordDigital_ShouldThrowException_ForNegativeMinute() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> NumberWords.minuteWordDigital(-1));
    }

    @Test
    void minuteWordDigital_ShouldThrowException_ForMinuteAbove59() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> NumberWords.minuteWordDigital(60));
    }

    @Test
    void constructor_IsPrivate() {
        // Verify that the constructor is private and cannot be instantiated
        assertThrows(IllegalAccessException.class, () -> {
            NumberWords.class.getDeclaredConstructor().newInstance();
        });
    }
}