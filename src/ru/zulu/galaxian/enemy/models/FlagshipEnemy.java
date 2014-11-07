package ru.zulu.galaxian.enemy.models;

import java.awt.Image;

import utils.ResourcesLoader;

public class FlagshipEnemy extends BaseEnemy {
	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public FlagshipEnemy(int _row, int _column) {
		super(_row, _column);
		sprites = new Image[] { ResourcesLoader.loadDrawableIgnoreErrors("flagship_enemy.png") };
		onSpritesLoaded();
	}
}
