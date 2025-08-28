package com.smartbear.util;

public enum Style {
    COLLOQUIAL, DIGITAL;

    public static Style of(String s) {
        if (s == null || s.isBlank()) return COLLOQUIAL;
        return Style.valueOf(s.toUpperCase());
    }

}
