package by.pharmsystem.medicamentservice.service.util.validator;

import by.pharmsystem.medicamentservice.entity.Medicament;
import by.pharmsystem.medicamentservice.service.exception.BadRequestException;
import by.pharmsystem.medicamentservice.service.util.constant.FormStorage;
import by.pharmsystem.medicamentservice.service.util.constant.GroupStorage;
import by.pharmsystem.medicamentservice.service.util.validator.regexp.RegExp;


public final class MedicamentValidator {

    public static void validateAddition(Medicament medicament) {
        if (medicament.getName() == null || medicament.getName().isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.NAME_REG_EXP, medicament.getName())) {
            throw new BadRequestException();
        }
        if (medicament.getGroup() == null || medicament.getGroup().isEmpty()) {
            throw new BadRequestException();
        }
        if (!GroupStorage.isExists(medicament.getGroup())) {
            throw new BadRequestException();
        }
        if (medicament.getForm() == null || medicament.getForm().isEmpty()) {
            throw new BadRequestException();
        }
        if (!FormStorage.isExists(medicament.getForm())) {
            throw new BadRequestException();
        }
        if (medicament.getAmount() == null || medicament.getAmount().isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.AMOUNT_REG_EXP, medicament.getAmount())) {
            throw new BadRequestException();
        }
        if (medicament.getActiveSubstances() == null || medicament.getActiveSubstances().isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.ACTIVE_SUBSTANCES_REG_EXP, medicament.getActiveSubstances())) {
            throw new BadRequestException();
        }
        if (medicament.getCountry() == null || medicament.getCountry().isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.COUNTRY_REG_EXP, medicament.getCountry())) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.COUNTRY_REG_EXP, medicament.getCountry())) {
            throw new BadRequestException();
        }
        if (medicament.getPrice() <= 0) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.PRICE_REG_EXP, Double.toString(medicament.getPrice()))) {
            throw new BadRequestException();
        }
        if (medicament.getQuantity() <= 0) {
            throw new BadRequestException();
        }
    }

}
