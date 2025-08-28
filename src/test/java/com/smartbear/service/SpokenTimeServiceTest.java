package com.smartbear.service;

import com.smartbear.util.Style;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpokenTimeServiceTest {

    private final SpokenTimeService svc = new SpokenTimeService();

    @Test
    void exacts() {
        assertThat(svc.speak("00:00", Style.colloquial)).isEqualTo("midnight");
        assertThat(svc.speak("12:00", Style.colloquial)).isEqualTo("noon");
        assertThat(svc.speak("01:00", Style.colloquial)).isEqualTo("one o'clock");
    }

    @Test void past_side() {
        assertThat(svc.speak("02:05", Style.colloquial)).isEqualTo("five past two");
        assertThat(svc.speak("03:10", Style.colloquial)).isEqualTo("ten past three");
        assertThat(svc.speak("04:15", Style.colloquial)).isEqualTo("quarter past four");
        assertThat(svc.speak("05:20", Style.colloquial)).isEqualTo("twenty past five");
        assertThat(svc.speak("06:25", Style.colloquial)).isEqualTo("twenty five past six");
        assertThat(svc.speak("07:30", Style.colloquial)).isEqualTo("half past seven");
    }

    @Test void to_side() {
        assertThat(svc.speak("07:35", Style.colloquial)).isEqualTo("twenty five to eight");
        assertThat(svc.speak("08:40", Style.colloquial)).isEqualTo("twenty to nine");
        assertThat(svc.speak("09:45", Style.colloquial)).isEqualTo("quarter to ten");
        assertThat(svc.speak("10:50", Style.colloquial)).isEqualTo("ten to eleven");
        assertThat(svc.speak("11:55", Style.colloquial)).isEqualTo("five to twelve");
    }

    @Test void digital_example() {
        assertThat(svc.speak("06:32", Style.digital)).isEqualTo("six thirty two");
    }
}