package ru.zulu.galaxian.background.models;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import ru.zulu.galaxian.core.models.DrawableObject;

public class Star extends DrawableObject {
	// =============================================================================================
	// CONSTANTS
	// =============================================================================================
	public static final int DIAMERT = 3;
	public static final int SPEED = 2;
	private static final int MAX_UPDATES_TO_CHANGE_VISIBILITY = 15;
	private static final int MIN_UPDATES_TO_CHANGE_VISIBILITY = 5;
	public static final float ALPHA = 0.4f;
	private static final Random RANDOM = new Random();

	// =============================================================================================
	// FIELDS
	// =============================================================================================
	private final Color color;
	private int updatesToChangeVisibility;
	public boolean isVisible;

	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public Star() {
		width = DIAMERT;
		height = DIAMERT;
		color = new Color(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat(), ALPHA);
		updatesToChangeVisibility = getRandomUpdatesToChangeVisibility();
	}

	// =============================================================================================
	// METHODS
	// =============================================================================================
	public void update() {
		if (updatesToChangeVisibility-- == 0) {
			isVisible = !isVisible;
			updatesToChangeVisibility = getRandomUpdatesToChangeVisibility();
		}
	}

	@Override
	public void draw(Graphics _graphics) {
		_graphics.setColor(color);
		_graphics.fillOval(x, y, width, height);
	}

	public static int getRandomUpdatesToChangeVisibility() {
		return RANDOM.nextInt(Star.MAX_UPDATES_TO_CHANGE_VISIBILITY - Star.MIN_UPDATES_TO_CHANGE_VISIBILITY)
				+ Star.MIN_UPDATES_TO_CHANGE_VISIBILITY;
	}
}
