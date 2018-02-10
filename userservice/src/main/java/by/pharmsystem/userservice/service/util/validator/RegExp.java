package by.pharmsystem.userservice.service.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegExp {

    //authorization
    public final static String NAME_REG_EXP = "^[A-ZА-Я][a-zа-я]+$";
    public final static String EMAIL_REG_EXP = "^.+@\\w+\\.\\w+$";
    //public final static String PASSWORD_REG_EXP = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-ZА-Я])(?=.*[a-zа-я]).*$";

    //id generator
    public final static String REGION_REG_EXP = "\\d";
    public final static String POLYCLINIC_NUMBER_REG_EXP = "\\d{1,2}";
    public final static String PATIENT_CARD_NUMBER_REG_EXP = "\\d{6}";

    //TODO remove test method
    private void temp() {
        Pattern p = Pattern.compile("\\d{6}");
    }


    public static boolean test(String regexp, String str) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
