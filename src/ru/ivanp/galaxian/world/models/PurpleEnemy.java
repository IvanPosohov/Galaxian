package ru.ivanp.galaxian.world.models;

import ru.ivanp.galaxian.utils.SlowSpriteSheetAnimator;
import ru.ivanp.galaxian.utils.SpriteSheet;

public class PurpleEnemy extends BaseEnemy {
    // =============================================================================================
    // CONSTANTS
    // =============================================================================================
    private final static int WIDTH = 32;
    private final static int HEIGHT = 23;
    private final static SpriteSheet LIFE_SPRITE_SHEET = new SpriteSheet("purple_enemy.png", WIDTH, HEIGHT);

    // =============================================================================================
    // CONSTRUCTOR
    // =============================================================================================
    public PurpleEnemy(int row, int column) {
        super(row, column);
        width = WIDTH;
        height = HEIGHT;
        livingAnimator = new SlowSpriteSheetAnimator(LIFE_SPRITE_SHEET, null, BaseEnemy.MIN_UPDATES_TO_CHANGE_SPRITE, BaseEnemy.MAX_UPDATES_TO_CHANGE_SPRITE);
    }
}
