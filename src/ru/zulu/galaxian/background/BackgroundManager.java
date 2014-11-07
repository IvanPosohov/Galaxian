package ru.zulu.galaxian.background;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import ru.zulu.galaxian.background.models.Star;
import ru.zulu.galaxian.core.BaseManager;
import ru.zulu.galaxian.core.OnUpdateListener;
import ru.zulu.galaxian.core.views.BaseView.OnDrawListener;

public class BackgroundManager extends BaseManager implements OnUpdateListener, OnDrawListener {
	// =============================================================================================
	// CONSTANTS
	// =============================================================================================
	private static final int STAR_COUNT = 100;

	// =============================================================================================
	// FIELDS
	// =============================================================================================
	private final Random random = new Random();
	private ArrayList<Star> stars;

	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public BackgroundManager() {
		stars = new ArrayList<Star>();
	}

	// =============================================================================================
	// OVERRIDDEN METHODS
	// =============================================================================================
	@Override
	public void onUpdate() {
		if (stars.size() == 0) {
			initStars();
		}
		updateStars();
	}

	@Override
	public void onDraw(Graphics _graphics) {
		if (stars != null) {
			for (Star star : stars) {
				if (star.isVisible) {
					star.draw(_graphics);
				}
			}
		}
	}

	// =============================================================================================
	// METHODS
	// =============================================================================================
	private void initStars() {
		for (int i = 0; i < STAR_COUNT; i++) {
			Star star = new Star();
			star.x = getRandomX();
			star.y = getRandomY();
			star.isVisible = random.nextBoolean();
			stars.add(star);
		}
	}

	private void updateStars() {
		for (Star star : stars) {
			star.update();
			star.y += Star.SPEED;
			if (star.y > gameAreaHeight) {
				star.x = getRandomX();
				star.y = 0;
			}
		}

	}

	private int getRandomX() {
		return random.nextInt(gameAreaWidth);
	}

	private int getRandomY() {
		return random.nextInt(gameAreaHeight);
	}
}
