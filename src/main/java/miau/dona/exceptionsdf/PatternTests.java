package miau.dona.exceptionsdf;

import miau.dona.exceptionsdf.exceptions.*;

public class PatternTests {

    // Extracted method to detect if the char introduced is a number
    private static boolean isANumber(char c) {
        return c >= '0' && c <= '9';
    }

    // Extracted method to count how many digits are in the pattern
    private static int countDigits(String pattern) throws ErrorCode5, ErrorCode1 {
        int nDigits = 0;

        if (pattern.isEmpty()) {
            throw new ErrorCode5();
        }

        char digitBefore = pattern.charAt(0);
        for (char c : pattern.toCharArray()) {
            if (isANumber(c)) {
                nDigits++;

                // If it's negative throw Error Code 1
                if (digitBefore == '-') {
                    throw new ErrorCode1();
                }
            }

            digitBefore = c;
        }

        return nDigits;
    }

    // Tests if there are 6 valid positive ints.
    private static void sixPositiveIntDigits(String pattern) throws ErrorCode2, ErrorCode1, ErrorCode0, ErrorCode5 {
        int nDigits = countDigits(pattern);

        // There are no digits
        if (nDigits == 0) {
            throw new ErrorCode0();
        }

        if (nDigits < 6) {
            throw new ErrorCode2();
        }
    }

    // Tests if there is a symbol of each valid type.
    private static void simbols(String pattern) throws ErrorCode3, ErrorCode4, ErrorCode5 {
        int nHastags = 0;
        int nAsterisks = 0;

        char digitBefore = 'a';
        for (char c : pattern.toCharArray()) {
            if (c == '#') {
                nHastags++;
            }
            if (c == '*') {
                nAsterisks++;
            }

            if (digitBefore == '-' && !(c >= '0' && c <= '9')) {
                throw new ErrorCode5();
            }

            digitBefore = c;
        }

        if (nHastags > 1 || nAsterisks > 1) {
            throw new ErrorCode5();
        }
        if (nAsterisks == 0) {
            throw new ErrorCode4();
        }
        if (nHastags == 0) {
            throw new ErrorCode3();
        }
    }

    // Throws an exception if there's a not valid character
    private static void otherExceptions(String pattern) throws ErrorCode5, ErrorCode1 {
        int digits = countDigits(pattern);

        for (char c : pattern.toCharArray()) {
            if (c != '#' && c != '*' && c != '-' && !(c >= '0' && c <= '9') && digits > 0) {
                throw new ErrorCode5();
            }
        }
    }

    /**
     * Tests the pattern and throws an error if it's not valid
     * @param pattern String to test if it is a valid pattern
     */
    public static void testPattern(String pattern) {
        try {
            sixPositiveIntDigits(pattern);
            simbols(pattern);
            otherExceptions(pattern);

            System.out.println("NO error. Everything is correct. Correct pattern");
        } catch (ErrorCode2 | ErrorCode1 | ErrorCode0 | ErrorCode3 | ErrorCode4 | ErrorCode5 e) {
            System.out.println(e.getMessage());
        }
    }
}
