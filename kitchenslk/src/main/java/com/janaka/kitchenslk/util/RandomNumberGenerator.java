package com.janaka.kitchenslk.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component(value = "randomNumberGenerator")
public class RandomNumberGenerator {
	
	private static RandomNumberGenerator instance;

    private RandomNumberGenerator() {
    }

    //this method generates a random number with the given length
    public long getRandomnumber(int length)  {
        String randomStr = "";
        while (randomStr.length() <= length) {
            Random randomGenerator = new Random();

            int randomInt = randomGenerator.nextInt(100);
            String str = Integer.toString(randomInt);
            randomStr = randomStr + str;


        }
        return Long.parseLong(randomStr);
    }

	 public static synchronized RandomNumberGenerator getInstance() {
	        if (instance == null) {
	            instance = new RandomNumberGenerator();
	        }
	        return instance;
	 }
}
