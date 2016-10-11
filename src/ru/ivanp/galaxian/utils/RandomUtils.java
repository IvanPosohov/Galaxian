package ru.ivanp.galaxian.utils;

import java.util.Random;

public final class RandomUtils {
    private RandomUtils() {
        // don't instantiate
    }

    private static final Random RAND = new Random();

    public static boolean nextBoolean() {
        return RAND.nextBoolean();
    }

    public static int nextInt(int bound) {
        return RAND.nextInt(bound);
    }

    public static int nextInt(int min, int max) {
        return RAND.nextInt(max - min) + min;
    }

    public static float nextFloat() {
        return RAND.nextFloat();
    }
}
