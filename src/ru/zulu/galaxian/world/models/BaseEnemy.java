package ru.zulu.galaxian.world.models;

import java.awt.Graphics;
import java.awt.Image;

import ru.zulu.galaxian.core.models.DrawableObject;
import ru.zulu.galaxian.utils.SpriteSheet;
import ru.zulu.galaxian.utils.SpriteSheetAnimator;
import ru.zulu.galaxian.utils.SpriteSheetAnimator.SpriteSheetAnimatorEventsListener;

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
	public BaseEnemy(int _row, int _column) {
		row = _row;
		column = _column;
		state = EnemyState.STILL;
		dyingAnimator = new SpriteSheetAnimator(DYING_SPRITE_SHEET, dyingAnimatorEventsListener);
	}

	// =============================================================================================
	// METHODS
	// =============================================================================================
	private SpriteSheetAnimatorEventsListener dyingAnimatorEventsListener = new SpriteSheetAnimatorEventsListener() {
		@Override
		public void onAnimationFinished() {
			state = EnemyState.DEAD;
		}
	};

	// =============================================================================================
	// BASE METHODS
	// =============================================================================================
	@Override
	public void draw(Graphics _graphics) {
		switch (state) {
		case STILL:
			if (livingAnimator == null) {
				_graphics.drawImage(sprite, x, y, null);
			} else {
				livingAnimator.drawNextFrame(_graphics, x, y);
			}
			break;
		case DYING:
			dyingAnimator.drawNextFrame(_graphics, x, y);
			break;
		}
	}

	public void kill() {
		state = EnemyState.DYING;
	}
}
