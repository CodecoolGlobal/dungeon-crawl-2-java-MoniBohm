package com.codecool.dungeoncrawl.util;

import java.util.Random;

public class RandomHelper {

    private static final Random RANDOM = new Random();


    /**
     * @param upperBound max number (exclusive)
     * @return random number between 0 (inclusive) and given number (exclusive).
     */
    public static int getRandomInt(int upperBound) {
        return RANDOM.nextInt(upperBound);
    }

    /**
     * @param lowerBound lowest number (inclusive)
     * @param upperBound highest number (exclusive)
     * @return random number between two given arguments
     */
    public static int getRandomInt(int lowerBound, int upperBound) {
        return RANDOM.nextInt(upperBound - lowerBound) + lowerBound;
    }
}
