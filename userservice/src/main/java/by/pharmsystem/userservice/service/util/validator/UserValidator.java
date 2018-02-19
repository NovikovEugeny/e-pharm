package by.pharmsystem.userservice.service.util.validator;

import by.pharmsystem.userservice.service.exception.BadRequestException;
import by.pharmsystem.userservice.service.util.ConstantStorage;

import java.util.Map;


public final class UserValidator {

    public static void validateSignIn(String login, String password) {
        if (login == null || login.isEmpty()) {
            throw new BadRequestException();
        }
        if (password == null || password.isEmpty()) {
            throw new BadRequestException();
        }
    }

    public static void validateSignUp(Map<String, String> data) {
        if (data.get(ConstantStorage.REGION) == null || data.get(ConstantStorage.REGION).isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.REGION_REG_EXP, data.get(ConstantStorage.REGION))) {
            throw new BadRequestException();
        }
        if (data.get(ConstantStorage.POLYCLINIC_NUMBER) == null || data.get(ConstantStorage.POLYCLINIC_NUMBER).isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.POLYCLINIC_NUMBER_REG_EXP, data.get(ConstantStorage.POLYCLINIC_NUMBER))) {
            throw new BadRequestException();
        }
        if (data.get(ConstantStorage.PATIENT_CARD_NUMBER) == null || data.get(ConstantStorage.PATIENT_CARD_NUMBER).isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.PATIENT_CARD_NUMBER_REG_EXP, data.get(ConstantStorage.PATIENT_CARD_NUMBER))) {
            throw new BadRequestException();
        }
        if (data.get(ConstantStorage.NAME) == null || data.get(ConstantStorage.NAME).isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.NAME_REG_EXP, data.get(ConstantStorage.NAME))) {
            throw new BadRequestException();
        }
        validateLogin(data.get(ConstantStorage.LOGIN));
        validateRole(data.get(ConstantStorage.ROLE));
    }

    public static void validateRole(String role) {
        if (role == null || role.isEmpty()) {
            throw new BadRequestException();
        }
        if (!role.equals(ConstantStorage.CLIENT) &&
                !role.equals(ConstantStorage.DOCTOR) &&
                !role.equals(ConstantStorage.PHARMACIST) &&
                !role.equals(ConstantStorage.ADMIN)) {
            throw new BadRequestException();
        }
    }

    public static void validateLogin(String login) {
        if (login == null || login.isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.EMAIL_REG_EXP, login)) {
            throw new BadRequestException();
        }
    }

}
