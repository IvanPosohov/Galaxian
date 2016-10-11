package ru.ivanp.galaxian.world.models;

import java.awt.Color;
import java.awt.Graphics;

import ru.ivanp.galaxian.core.models.DrawableObject;

public class Bullet extends DrawableObject {
	private static final Color COLOR = new Color(0xb5b5b5);
	private static final int DEFAULT_WIDTH = 2;
	private static final int DEFAULT_HEIGHT = 5;
	private static final int DEFAULT_Y_VELOCITY = -30;

	public boolean ifFlies;

	public Bullet() {
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		yVelocity = DEFAULT_Y_VELOCITY;
	}

	@Override
	public void draw(Graphics _graphics) {
		_graphics.setColor(COLOR);
		_graphics.fillRect(x, y, width, height);
	}

}
