package ru.ivanp.galaxian.background.models;

import java.awt.Color;
import java.awt.Graphics;

import ru.ivanp.galaxian.core.models.DrawableObject;
import ru.ivanp.galaxian.utils.ColorUtils;
import ru.ivanp.galaxian.utils.RandomUtils;

public class Star extends DrawableObject {
    // =============================================================================================
    // CONSTANTS
    // =============================================================================================
    public static final int SPEED = 2;
    private static final int SIZE = 3;
    private static final int MIN_UPDATE_TO_CHANGE_VISIBILITY = 5;
    private static final int MAX_UPDATE_TO_CHANGE_VISIBILITY = 15;
    private static final float ALPHA = 0.4f;

    // =============================================================================================
    // FIELDS
    // =============================================================================================
    private final Color color;
    private int updatesToChangeVisibility;
    public boolean isVisible;

    // =============================================================================================
    // CONSTRUCTOR
    // =============================================================================================
    public Star() {
        width = SIZE;
        height = SIZE;
        color = ColorUtils.getRandomColor(ALPHA);
        updatesToChangeVisibility = RandomUtils.nextInt(MIN_UPDATE_TO_CHANGE_VISIBILITY, MAX_UPDATE_TO_CHANGE_VISIBILITY);
    }

    // =============================================================================================
    // METHODS
    // =============================================================================================
    public void update() {
        if (updatesToChangeVisibility-- == 0) {
            isVisible = !isVisible;
            updatesToChangeVisibility = RandomUtils.nextInt(MIN_UPDATE_TO_CHANGE_VISIBILITY, MAX_UPDATE_TO_CHANGE_VISIBILITY);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }
}
