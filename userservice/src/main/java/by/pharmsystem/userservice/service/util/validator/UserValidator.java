package by.pharmsystem.userservice.service.util.validator;

import by.pharmsystem.userservice.service.exception.BadRequestException;
import by.pharmsystem.userservice.service.util.UserConstants;

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
        if (data.get(UserConstants.REGION) == null || data.get(UserConstants.REGION).isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.REGION_REG_EXP, data.get(UserConstants.REGION))) {
            throw new BadRequestException();
        }
        if (data.get(UserConstants.POLYCLINIC_NUMBER) == null || data.get(UserConstants.POLYCLINIC_NUMBER).isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.POLYCLINIC_NUMBER_REG_EXP, data.get(UserConstants.POLYCLINIC_NUMBER))) {
            throw new BadRequestException();
        }
        if (data.get(UserConstants.PATIENT_CARD_NUMBER) == null || data.get(UserConstants.PATIENT_CARD_NUMBER).isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.PATIENT_CARD_NUMBER_REG_EXP, data.get(UserConstants.PATIENT_CARD_NUMBER))) {
            throw new BadRequestException();
        }
        if (data.get(UserConstants.NAME) == null || data.get(UserConstants.NAME).isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.NAME_REG_EXP, data.get(UserConstants.NAME))) {
            throw new BadRequestException();
        }
        if (data.get(UserConstants.LOGIN) == null || data.get(UserConstants.LOGIN).isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.EMAIL_REG_EXP, data.get(UserConstants.LOGIN))) {
            throw new BadRequestException();
        }
        validateRole(data.get(UserConstants.ROLE));
    }

    public static void validateRole(String role) {
        if (role == null || role.isEmpty()) {
            throw new BadRequestException();
        }
        if (!role.equals(UserConstants.CLIENT) &&
                !role.equals(UserConstants.DOCTOR) &&
                !role.equals(UserConstants.PHARMACIST) &&
                !role.equals(UserConstants.ADMIN)) {
            throw new BadRequestException();
        }
    }

}
