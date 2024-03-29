import java.util.regex.*;

public class RegExExample {
    public static void main(String[] args) {
//        extractIPDemo();
//        regEx();
        matcherExample();
    }

    /**
     * Simple example of regex usage
     */
    public static void regEx() {
        Pattern p = Pattern.compile("a*b");
        Matcher m = p.matcher("b");
        // matches() requires full match for the whole string
        // find() find the first match
        boolean b = m.matches();
        System.out.println(b);
    }

    public static void matcherExample() {
        String input = "11:59 am";
        // 00 02 ... 09 10 11 11    00:59
        Pattern pattern = Pattern.compile("([1-9]|1[1-2])\\s*:\\s*([0-5][0-9])\\s*([ap]m)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String hours = matcher.group(1);
            String minutes = matcher.group(2);
            String period = matcher.group(3);

            System.out.println("Hours: " + hours + " minutes: " + minutes + " " + period);
        }
    }

    /**
     * Method to demo javaDoc
     * @param input sptring
     * @return output string
     * @throws RuntimeException exception in some cases
     * @author Dmitry
     */
    public static String javaDocExample(String input) throws RuntimeException {
        return input;
    }

    /**
     * Validates a sentence. Checks if first letter is capital and it ends with dot, question mark or exclamation mark.
     *
     * @param input String with the sentence to check
     * @return true if sentence is correct, false otherwise
     */
    public static boolean validateInput(String input) {
        return Pattern.matches("[A-Z].*[.?!]", input);
    }

    /**
     * Demonstrates that regex are used as splitter for strings
     */
    public static void regExAsSplitter() {
        String test = "car;bike;train:plane";
        String[] testSplitted = test.split("[;:]");
        for (String el : testSplitted) {
            System.out.println(el);
        }
    }

    /**
     * Extracts IPv4 address from string according to RFC 791
     *
     * @param text String where to look for IP
     * @return String with IP or empty string if text doesn't contain IP
     */
    public static String extractIP(String text) {
        String regexpIP = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        Pattern pattern = Pattern.compile(regexpIP);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }


    public static void extractIPDemo() {
        System.out.println(extractIP("ahwbdvjkcabds192.168.0.3wdlfshvs&^%jvwdsc"));
        // prints 192.168.0.3
    }



    /* /////////////////////////////// TASKS /////////////////////////////// */


    // You need to add regular expressions, so that methods satisfy their descriptions
    // Run tests from InputTest.java in test folder to check your solutions


    /**
     * Validates email address. Email should contain one at-symbol (@) and at least one symbol on the left and on the right
     * of at-symbol. It can contain letters, numbers and symbols '.', '-', '_'
     *
     * @param email String with email to validate
     * @return true if email is correct, false otherwise
     */
    public static boolean validateEmail(String email) {
        Pattern p = Pattern.compile("YOUR REGEX HERE");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Validates phone number. Phone number format should be '+COUNTRY_CODE (xxx) xxx-xx-xx'. COUNTRY_CODE is 1-3 digit number.
     *
     * @param number String with email to validate
     * @return true if email is correct, false otherwise
     */
    public static boolean validatePhoneNumber(String number) {
        Pattern p = Pattern.compile("YOUR REGEX HERE");
        Matcher m = p.matcher(number);
        return m.matches();
    }
}