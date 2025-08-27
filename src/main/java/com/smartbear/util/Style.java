package com.smartbear.util;

public enum Style {
    colloquial, digital;

    public static Style of(String s) {
        if (s == null || s.isBlank()) return colloquial;
        return Style.valueOf(s.toLowerCase());
    }

}
