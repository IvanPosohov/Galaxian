package ru.ivanp.galaxian.utils;

import java.awt.*;

public final class ColorUtils {
    private ColorUtils() {
        // don't instantiate
    }

    public static Color getRandomColor(float alpha) {
        return new Color(RandomUtils.nextFloat(), RandomUtils.nextFloat(), RandomUtils.nextFloat(), alpha);
    }
}