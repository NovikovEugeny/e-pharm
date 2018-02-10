package by.pharmsystem.medicamentservice.service.util.validator.regexp;

public final class RegExp {

    //authorization
    public final static String NAME_REG_EXP = "^[A-ZА-Я][a-zа-я]+$";
    public final static String EMAIL_REG_EXP = "^.+@\\w+\\.\\w+$";
    public final static String PASSWORD_REG_EXP = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-ZА-Я])(?=.*[a-zа-я]).*$";

}
