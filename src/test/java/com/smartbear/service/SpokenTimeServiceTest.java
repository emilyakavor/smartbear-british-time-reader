package com.smartbear.service;

import com.smartbear.util.Style;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpokenTimeServiceTest {

    private final SpokenTimeService svc = new SpokenTimeService();

    @Test
    void exacts() {
        assertThat(svc.speak("00:00", Style.COLLOQUIAL)).isEqualTo("midnight");
        assertThat(svc.speak("12:00", Style.COLLOQUIAL)).isEqualTo("noon");
        assertThat(svc.speak("01:00", Style.COLLOQUIAL)).isEqualTo("one o'clock");
    }

    @Test void past_side() {
        assertThat(svc.speak("02:05", Style.COLLOQUIAL)).isEqualTo("five past two");
        assertThat(svc.speak("03:10", Style.COLLOQUIAL)).isEqualTo("ten past three");
        assertThat(svc.speak("04:15", Style.COLLOQUIAL)).isEqualTo("quarter past four");
        assertThat(svc.speak("05:20", Style.COLLOQUIAL)).isEqualTo("twenty past five");
        assertThat(svc.speak("06:25", Style.COLLOQUIAL)).isEqualTo("twenty five past six");
        assertThat(svc.speak("07:30", Style.COLLOQUIAL)).isEqualTo("half past seven");
    }

    @Test void to_side() {
        assertThat(svc.speak("07:35", Style.COLLOQUIAL)).isEqualTo("twenty five to eight");
        assertThat(svc.speak("08:40", Style.COLLOQUIAL)).isEqualTo("twenty to nine");
        assertThat(svc.speak("09:45", Style.COLLOQUIAL)).isEqualTo("quarter to ten");
        assertThat(svc.speak("10:50", Style.COLLOQUIAL)).isEqualTo("ten to eleven");
        assertThat(svc.speak("11:55", Style.COLLOQUIAL)).isEqualTo("five to twelve");
    }

    @Test void digital_example() {
        assertThat(svc.speak("06:32", Style.DIGITAL)).isEqualTo("six thirty two");
    }

    @ParameterizedTest
    @CsvSource({
            "01:00, one o'clock",
            "02:05, five past two",
            "03:10, ten past three",
            "04:15, quarter past four",
            "05:20, twenty past five",
            "06:25, twenty five past six",
            "07:30, half past seven",
            "08:35, twenty five to nine",
            "09:40, twenty to ten",
            "10:45, quarter to eleven",
            "11:50, ten to twelve"
    })
    void speakTime_ShouldReturnCorrectBritishTime(String inputTime, String expectedOutput) {
        // Given
        var s = Style.of(null);
        String result = svc.speak(inputTime, s);

        // Assert
        assertThat(result).isEqualTo(expectedOutput);

        // Assert
//        assertEquals(expectedOutput, result);
//        verify(digitalBritishFormatter, times(1)).speak(clockTime);
    }
}