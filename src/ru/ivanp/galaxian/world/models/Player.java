package ru.ivanp.galaxian.world.models;

import java.awt.Graphics;
import java.awt.Image;

import ru.ivanp.galaxian.utils.ResourcesLoader;
import ru.ivanp.galaxian.core.models.DrawableObject;

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
