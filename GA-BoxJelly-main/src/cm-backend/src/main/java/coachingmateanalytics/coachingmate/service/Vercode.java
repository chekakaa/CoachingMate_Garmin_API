package coachingmateanalytics.coachingmate.service;

import java.nio.charset.Charset;
import java.util.Random;

import java.security.SecureRandom;

public class Vercode {
    private static  final String SYMBOLS = "0123456789";
    private static final Random RANDOM = new SecureRandom();
    //生成六位数的随机数字
    public static final String generateVerCode(){
        char [] numbers = new char[6];
        for (int i=0;i<numbers.length;i++){
            numbers[i] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(numbers);
    }

    public static final String generateString(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
}
