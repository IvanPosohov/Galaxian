package ru.zulu.galaxian.world.models;

import java.awt.Graphics;
import java.awt.Image;

import ru.zulu.galaxian.core.models.DrawableObject;
import ru.zulu.galaxian.utils.ResourcesLoader;

public class Player extends DrawableObject {
	public static final int DEFAULT_X_VELOCITY = 3;

	private Image sprite;

	public int getCenterX() {
		return x + width / 2;
	}

	public Player() {
		sprite = ResourcesLoader.loadDrawableIgnoreErrors("player_ship.png");
		width = sprite.getWidth(null);
		height = sprite.getHeight(null);
	}

	@Override
	public void draw(Graphics _graphics) {
		_graphics.drawImage(sprite, x, y, null);
	}

}
