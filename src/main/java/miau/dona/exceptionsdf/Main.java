package miau.dona.exceptionsdf;

public class Main {
    public static void main(String[] args) {
        // Working example
        String pattern = "*0234#56";
        PatternTests.testPattern(pattern);

        // If it's made only of letters and symbols, Error 0
        String pattern0 = "sdd*asfs#";
        PatternTests.testPattern(pattern0);

        // If there's a negative number, Error 1
        String pattern1 = "#-123*456";
        PatternTests.testPattern(pattern1);

        // If there aren't enough digits, Error 2
        String pattern2 = "123*#";
        PatternTests.testPattern(pattern2);

        // If there isn't a #, Error 3
        String pattern3 = "123456*";
        PatternTests.testPattern(pattern3);

        // If there isn't a *, Error 4
        String pattern4 = "123456#";
        PatternTests.testPattern(pattern4);

        // If other exception, Error 5
        String pattern5 = "123d456*-#";
        PatternTests.testPattern(pattern5);
    }
}