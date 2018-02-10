package by.pharmsystem.userservice.service.util.generator;

import by.pharmsystem.userservice.entity.User;

import java.util.Random;

public final class PasswordGenerator {

    public static String generate(User user) {
        Random r = new Random();
        int symbolQuantity = (int) (Math.random() * 3) + 3;
        char[] symbols = new char[symbolQuantity];

        for (int i = 0; i < symbolQuantity; i++) {
            char c;
            if (i % 2 == 0) {
                c = (char) (r.nextInt(26) + 'a');
            } else {
                c = (char) (r.nextInt(26) + 'A');
            }
            symbols[i] = c;
        }

        String digits = Integer.toString(Math.abs(user.hashCode()));

        String digitPart = digits.substring(0, 6);
        String symbolPart = new String(symbols);

        return symbolPart + digitPart;
    }
}
