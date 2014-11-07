package ru.zulu.galaxian.enemy.models;

import java.awt.Image;

import utils.ResourcesLoader;

public class BlueEnemy extends BaseEnemy {
	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public BlueEnemy(int _row, int _column) {
		super(_row, _column);
		sprites = new Image[] { ResourcesLoader.loadDrawableIgnoreErrors("blue_enemy.png"),
				ResourcesLoader.loadDrawableIgnoreErrors("blue_enemy_up.png") };
		onSpritesLoaded();
	}
}
