package com.smartbear.smartbearemil.core.format;

import com.smartbear.smartbearemil.core.ClockTime;

public interface SpokenTimeFormatter {
    String speak(ClockTime time);
}

