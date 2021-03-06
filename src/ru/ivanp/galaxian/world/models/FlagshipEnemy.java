package ru.ivanp.galaxian.world.models;

import ru.ivanp.galaxian.utils.ResourcesLoader;

public class FlagshipEnemy extends BaseEnemy {
    // =============================================================================================
    // CONSTANTS
    // =============================================================================================
    private final static int WIDTH = 32;
    private final static int HEIGHT = 32;

    // =============================================================================================
    // CONSTRUCTOR
    // =============================================================================================
    public FlagshipEnemy(int row, int column) {
        super(row, column);
        width = WIDTH;
        height = HEIGHT;
        sprite = ResourcesLoader.loadDrawableIgnoreErrors("flagship_enemy.png");
    }
}
