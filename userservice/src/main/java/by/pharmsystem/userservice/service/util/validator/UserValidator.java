package by.pharmsystem.userservice.service.util.validator;

import by.pharmsystem.userservice.entity.User;
import by.pharmsystem.userservice.service.exception.BadRequestException;
import by.pharmsystem.userservice.service.util.ConstantStorage;


public final class UserValidator {

    public static void validateSignIn(String login, String password) {
        if (login == null || login.isEmpty()) {
            throw new BadRequestException();
        }
        if (password == null || password.isEmpty()) {
            throw new BadRequestException();
        }
    }

    public static void validateSignUp(User user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            throw new BadRequestException();
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.NAME_REG_EXP, user.getName())) {
            throw new BadRequestException();
        }
        validateRole(user.getRole());
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
}
