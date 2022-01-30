package util;

import java.util.Random;

public class TestUtil {

    public static long PAGE_LOAD_TIMEOUT = 40;
    public static long IMPLICIT_WAIT = 5;

    public static int randomNumber(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(1000);
        return randomNumber;
    }
}
