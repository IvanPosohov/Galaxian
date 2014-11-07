package ru.zulu.galaxian.enemy.models;

import java.awt.Image;

import utils.ResourcesLoader;

public class PurpleEnemy extends BaseEnemy {
	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public PurpleEnemy(int _row, int _column) {
		super(_row, _column);
		sprites = new Image[] { ResourcesLoader.loadDrawableIgnoreErrors("purple_enemy.png"),
				ResourcesLoader.loadDrawableIgnoreErrors("purple_enemy_up.png") };
		onSpritesLoaded();
	}
}
