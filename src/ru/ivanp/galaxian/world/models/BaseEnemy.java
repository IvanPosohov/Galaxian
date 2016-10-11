package ru.ivanp.galaxian.world.models;

import java.awt.Graphics;
import java.awt.Image;

import ru.ivanp.galaxian.core.models.DrawableObject;
import ru.ivanp.galaxian.utils.SpriteSheet;
import ru.ivanp.galaxian.utils.SpriteSheetAnimator;
import ru.ivanp.galaxian.utils.SpriteSheetAnimator.EventsListener;

public abstract class BaseEnemy extends DrawableObject {
    // =============================================================================================
    // CONSTANTS
    // =============================================================================================
    protected static final int MAX_UPDATES_TO_CHANGE_SPRITE = 9;
    protected static final int MIN_UPDATES_TO_CHANGE_SPRITE = 4;
    private static final SpriteSheet DYING_SPRITE_SHEET = new SpriteSheet("explosion.png", 32, 32);

    // =============================================================================================
    // FIELDS
    // =============================================================================================
    public final int row;
    public final int column;
    public EnemyState state;
    protected Image sprite;
    protected SpriteSheetAnimator livingAnimator;
    private SpriteSheetAnimator dyingAnimator;

    // =============================================================================================
    // CONSTRUCTOR
    // =============================================================================================
    public BaseEnemy(int row, int column) {
        this.row = row;
        this.column = column;
        state = EnemyState.STILL;
        dyingAnimator = new SpriteSheetAnimator(DYING_SPRITE_SHEET, new EventsListener() {
            @Override
            public void onAnimationFinished() {
                state = EnemyState.DEAD;
            }
        });
    }

    // =============================================================================================
    // METHODS
    // =============================================================================================
    @Override
    public void draw(Graphics g) {
        switch (state) {
        case STILL:
            if (livingAnimator == null) {
                g.drawImage(sprite, x, y, null);
            } else {
                livingAnimator.drawNextFrame(g, x, y);
            }
            break;
        case DYING:
            dyingAnimator.drawNextFrame(g, x, y);
            break;
        }
    }

    public void kill() {
        state = EnemyState.DYING;
    }
}
