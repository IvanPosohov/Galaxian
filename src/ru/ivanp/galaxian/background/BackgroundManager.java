package ru.ivanp.galaxian.background;

import java.awt.Graphics;
import java.util.ArrayList;

import ru.ivanp.galaxian.background.models.Star;
import ru.ivanp.galaxian.core.models.BaseManager;
import ru.ivanp.galaxian.utils.RandomUtils;

public class BackgroundManager extends BaseManager {
    // =============================================================================================
    // CONSTANTS
    // =============================================================================================
    private static final int STAR_COUNT = 100;

    // =============================================================================================
    // FIELDS
    // =============================================================================================
    private ArrayList<Star> stars;

    // =============================================================================================
    // CONSTRUCTOR
    // =============================================================================================
    public BackgroundManager() {
        stars = new ArrayList<Star>();

    }

    // =============================================================================================
    // METHODS
    // =============================================================================================
    @Override
    public void init() {
        for (int i = 0; i < STAR_COUNT; i++) {
            Star star = new Star();
            star.x = RandomUtils.nextInt(width);
            star.y = RandomUtils.nextInt(height);
            star.isVisible = RandomUtils.nextBoolean();
            stars.add(star);
        }
    }

    @Override
    public void update() {
        for (Star star : stars) {
            star.update();
            star.y += Star.SPEED;
            if (star.y > height) {
                star.x = RandomUtils.nextInt(width);
                star.y = 0;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (stars != null) {
            for (Star star : stars) {
                if (star.isVisible) {
                    star.draw(g);
                }
            }
        }
    }
}
