package ru.zulu.galaxian.enemy.models;

import java.awt.Image;

import utils.ResourcesLoader;

public class RedEnemy extends BaseEnemy {
	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public RedEnemy(int _row, int _column) {
		super(_row, _column);
		sprites = new Image[] { ResourcesLoader.loadDrawableIgnoreErrors("red_enemy.png"),
				ResourcesLoader.loadDrawableIgnoreErrors("red_enemy_up.png") };
		onSpritesLoaded();
	}
}
