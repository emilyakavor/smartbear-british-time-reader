package com.smartbear.util.format;

final class NumberWords {
    private static final String[] ONES = {
            "zero","one","two","three","four","five","six","seven","eight","nine","ten",
            "eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen",
            "eighteen","nineteen"
    };
    private static final String[] TENS = {
            "", "", "twenty","thirty","forty","fifty"
    };

    static String hourWord(int h12) { // 1..12
        return switch (h12) {
            case 1 -> "one";   case 2 -> "two";   case 3 -> "three";
            case 4 -> "four";  case 5 -> "five";  case 6 -> "six";
            case 7 -> "seven"; case 8 -> "eight"; case 9 -> "nine";
            case 10 -> "ten";  case 11 -> "eleven"; case 12 -> "twelve";
            default -> throw new IllegalArgumentException("hour must be 1..12");
        };
    }

    static String minuteWord(int m) { // 0..59, no “oh” prefix; spaces not hyphens
        if (m < 20) return ONES[m];
        int tens = m / 10, ones = m % 10;
        return ones == 0 ? TENS[tens] : TENS[tens] + " " + ONES[ones];
    }

    static String minuteWordDigital(int m) { // add natural “oh” for 01..09
        if (m == 0) return "o'clock"; // handled upstream for exact hours
        if (m < 10) return "oh " + ONES[m];
        return minuteWord(m);
    }

    private NumberWords() {}
}
