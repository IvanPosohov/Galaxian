package ru.zulu.galaxian.player.models;

import java.awt.Color;
import java.awt.Graphics;

import ru.zulu.galaxian.core.models.DrawableObject;

public class Bullet extends DrawableObject {
	private static final Color COLOR = new Color(0xb5b5b5);
	public static final int WIDTH = 2;
	public static final int HEIGHT = 5;
	public static final int SPEED = 50;
	
	public boolean ifFlies;
	
	@Override
	public void draw(Graphics _graphics) {
		_graphics.setColor(COLOR);
		_graphics.fillRect(x, y, WIDTH, HEIGHT);
	}

}