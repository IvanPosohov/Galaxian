package ru.zulu.galaxian.enemy.models;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import ru.zulu.galaxian.core.models.DrawableObject;

public abstract class BaseEnemy extends DrawableObject {
	// =============================================================================================
	// CONSTANTS
	// =============================================================================================
	public static final int MAX_UPDATES_TO_CHANGE_SPRITE = 9;
	public static final int MIN_UPDATES_TO_CHANGE_SPRITE = 4;

	// =============================================================================================
	// FIELDS
	// =============================================================================================
	private static Random random = new Random();
	public EnemyState state = EnemyState.STILL;
	public final int row;
	public final int column;
	protected Image[] sprites;
	private int updatesToChangeSprite;
	private int spriteIndex;

	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public BaseEnemy(int _row, int _column) {
		row = _row;
		column = _column;
	}
	
	// =============================================================================================
	// CALLBACKS
	// =============================================================================================
	protected void onSpritesLoaded() {
		// all sprites must be the same size
		width = sprites[0].getWidth(null);
		height = sprites[0].getHeight(null);
	}

	// =============================================================================================
	// METHODS
	// =============================================================================================
	public void update() {
		if (updatesToChangeSprite-- == 0) {
			updatesToChangeSprite = random.nextInt(MAX_UPDATES_TO_CHANGE_SPRITE - MIN_UPDATES_TO_CHANGE_SPRITE)
					+ MIN_UPDATES_TO_CHANGE_SPRITE;
			spriteIndex = (spriteIndex + 1) % sprites.length;
		}
	}
	
	// =============================================================================================
	// BASE METHODS
	// =============================================================================================
	@Override
	public void draw(Graphics _graphics) {
		_graphics.drawImage(sprites[spriteIndex], x, y, null);
	}
}
