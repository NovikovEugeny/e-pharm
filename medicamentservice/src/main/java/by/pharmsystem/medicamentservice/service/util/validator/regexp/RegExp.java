package by.pharmsystem.medicamentservice.service.util.validator.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegExp {

    public final static String NAME_REG_EXP = "^[A-ZА-Я][a-zа-я-]+\\s?[a-zа-я-]*\\s?\\d*\\s?[a-zа-я]*$";
    public final static String NAME_FOR_SEARCH_REG_EXP = "^[A-ZА-Яa-zа-я]{2,}.*$";
    public final static String AMOUNT_REG_EXP = "^\\d{1,4}\\s?[a-zа-я]+$";
    public final static String ACTIVE_SUBSTANCES_REG_EXP = "^([A-ZА-Яa-zа-я][а-яa-z]+\\s?-\\s?\\d+\\s?[а-яa-z/\\d%]+[,;]?\\s?)+$";
    public final static String COUNTRY_REG_EXP = "^[А-ЯA-Z][А-ЯA-Zа-яa-z]{2,}$";
    public final static String PRICE_REG_EXP = "^\\d{1,3}(\\.\\d{1,2})?$";
    
    private void temp() {
        Pattern pattern = Pattern.compile("^[A-ZА-Я][a-zа-яА-ЯA-Z]{2,}.*$");
    }

    public static boolean test(String regexp, String str) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher m = pattern.matcher(str);
        return m.matches();
    }
}
