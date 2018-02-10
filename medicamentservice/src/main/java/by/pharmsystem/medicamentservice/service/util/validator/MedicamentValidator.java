package by.pharmsystem.medicamentservice.service.util.validator;

import by.pharmsystem.medicamentservice.entity.Medicament;
import by.pharmsystem.medicamentservice.service.exception.BadRequestException;
import by.pharmsystem.medicamentservice.service.util.validator.regexp.RegExp;
import by.pharmsystem.medicamentservice.service.util.validator.regexp.RegExpTester;


public final class MedicamentValidator {

    public static void validateAddition(Medicament medicament) {
        if (medicament.getName() == null || user.getName().isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExpTester.test(RegExp.NAME_REG_EXP, user.getName())) {
            throw new BadRequestException();
        }
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExpTester.test(RegExp.EMAIL_REG_EXP, user.getLogin())) {
            throw new BadRequestException();
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExpTester.test(RegExp.PASSWORD_REG_EXP, user.getPassword())) {
            throw new BadRequestException();
        }
        if (user.getRole() == null || user.getRole().isEmpty()) {
            throw new BadRequestException();
        }
        if (!user.getRole().equals("client") &&
                !user.getRole().equals("doctor") &&
                !user.getRole().equals("pharmacist")) {
            throw new BadRequestException();
        }
    }

}
