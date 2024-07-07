package com.insitute.iimanage.util.tools;

import java.util.Random;

public class VerificationCodeGenerator {

    private final String NUMBERS = "0123456789";

    public int getCode(int length) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char selectNumber = NUMBERS.charAt(new Random().nextInt(10));
            sb.append(selectNumber);
        }

        return Integer.parseInt(sb.toString());
    }

}
