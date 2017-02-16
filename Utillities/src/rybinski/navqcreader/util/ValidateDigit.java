/*
 * Decompiled with CFR 0_118.
 */
package rybinski.navqcreader.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDigit {
    public static boolean validateInteger(String input) {
        return ValidateDigit.validate(input, "^[1-9][0-9]*?$");
    }

    public static boolean validateDouble(String input) {
        boolean result = ValidateDigit.validate(input, "^[1-9]{1}[0-9]*?(?:[,\\.]?[0-9]+?)$");
        if (result) {
            try {
                DecimalFormat.getInstance(new Locale("en")).parse(input);
            }
            catch (ParseException e) {
                result = false;
            }
        }
        return result;
    }

    public static boolean validate(String input, String p) {
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}

