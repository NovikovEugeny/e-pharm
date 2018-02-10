package by.pharmsystem.userservice.service.util.generator;

import by.pharmsystem.userservice.service.util.UserConstants;

import java.util.Map;

public final class IdGenerator {

    public static Long generate(Map<String, String> data) {

        int roleDigit = 0;
        String role = data.get(UserConstants.ROLE);

        if (role.equals(UserConstants.CLIENT)) roleDigit = 0;
        if (role.equals(UserConstants.PHARMACIST)) roleDigit = 1;
        if (role.equals(UserConstants.DOCTOR)) roleDigit = 2;

        StringBuilder strId = new StringBuilder();
        strId
                .append(data.get(UserConstants.REGION))
                .append(data.get(UserConstants.POLYCLINIC_NUMBER))
                .append(data.get(UserConstants.PATIENT_CARD_NUMBER))
                .append(roleDigit);

        return Long.parseLong(strId.toString());
    }
}
