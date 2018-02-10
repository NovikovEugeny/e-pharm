package by.pharmsystem.medicamentservice.service.util.validator.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegExpTester {

    public static boolean test(String regexp, String str) {
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
