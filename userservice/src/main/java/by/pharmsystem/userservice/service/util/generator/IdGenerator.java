package by.pharmsystem.userservice.service.util.generator;

import by.pharmsystem.userservice.service.util.ConstantStorage;

import java.util.Map;

public final class IdGenerator {

    public static Long generate(Map<String, String> data) {

        int roleDigit = 0;
        String role = data.get(ConstantStorage.ROLE);

        if (role.equals(ConstantStorage.CLIENT)) roleDigit = 0;
        if (role.equals(ConstantStorage.PHARMACIST)) roleDigit = 1;
        if (role.equals(ConstantStorage.DOCTOR)) roleDigit = 2;

        StringBuilder strId = new StringBuilder();
        strId
                .append(data.get(ConstantStorage.REGION))
                .append(data.get(ConstantStorage.POLYCLINIC_NUMBER))
                .append(data.get(ConstantStorage.PATIENT_CARD_NUMBER))
                .append(roleDigit);

        return Long.parseLong(strId.toString());
    }
}
