package by.pharmsystem.orderservice.service.util.validator.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegExp {

    public final static String ADDRESS_REG_EXP = "^[A-Za-zА-Яа-я]+\\.?-?$";

    public static boolean test(String regexp, String str) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher m = pattern.matcher(str);
        return m.matches();
    }
}
