package ru.zulu.galaxian.world.models;

import utils.ResourcesLoader;

public class FlagshipEnemy extends BaseEnemy {
	// =============================================================================================
	// CONSTANTS
	// =============================================================================================
	private final static int WIDTH = 32;
	private final static int HEIGHT = 32;
	
	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public FlagshipEnemy(int _row, int _column) {
		super(_row, _column);
		width = WIDTH;
		height = HEIGHT;
		sprite = ResourcesLoader.loadDrawableIgnoreErrors("flagship_enemy.png");
	}
}
