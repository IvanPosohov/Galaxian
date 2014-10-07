package ru.zulu.galaxian.background.models;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import ru.zulu.galaxian.core.models.DrawableObject;

public class Star extends DrawableObject {
	// =============================================================================================
	// CONSTANTS
	// =============================================================================================
	private static final int DEFAULT_SIZE = 3;
	public static final int MAX_UPDATES_TO_CHANGE_VISIBILITY = 15;
	public static final int MIN_UPDATES_TO_CHANGE_VISIBILITY = 5;
	private static final Random random = new Random();

	// =============================================================================================
	// FIELDS
	// =============================================================================================
	private final int size;
	private final Color color;
	public int x;
	public int y;
	public boolean isVisible;
	public int updatesToChangeVisibility;

	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public Star() {
		size = DEFAULT_SIZE;
		color = new Color(random.nextInt());
	}

	// =============================================================================================
	// OVVERIDDEN METHODS
	// =============================================================================================
	@Override
	public void draw(Graphics _graphics) {
		_graphics.setColor(color);
		_graphics.fillOval(x, y, size, size);
	}
}
