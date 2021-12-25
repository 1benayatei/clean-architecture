package com.invoice.core.domain.invoice;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

public class TrackingCodeGenerator {
    public static String make() {

        char[] alphabets = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        char[] numbers = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        char firstChar = getRandom(alphabets);

        char secondChar = getRandom(ArrayUtils.removeElement(alphabets, firstChar));

        char threeChar = getRandom(ArrayUtils.removeElements(numbers, '0'));

        char fourChar = getRandom(ArrayUtils.removeElements(numbers, '0', '6'));

        char fiveChar = getRandom(ArrayUtils.removeElements(numbers, '0', '2', '3', '4', '5', '7', '8'));

        char sixChar = getRandom(numbers);

        char sevenChar = getRandom(ArrayUtils.removeElements(numbers, '9', '4', '7'));

        char eightChar = getRandom(numbers);

        char nineChar = getRandom(ArrayUtils.removeElements(numbers, '2', '7'));

        char tenChar = getRandom(ArrayUtils.removeElements(numbers, '0', '5', '8', '6'));

        char elevenChar = getRandom(ArrayUtils.removeElements(numbers, '0', '1', '2', '3', '4', '5', '6'));

        char twelveChar = getRandom(numbers);

        return String.valueOf(firstChar) + secondChar + threeChar + fourChar + fiveChar + sixChar + sevenChar
                + eightChar + nineChar + tenChar + elevenChar + twelveChar;
    }

    private static char getRandom(char[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
