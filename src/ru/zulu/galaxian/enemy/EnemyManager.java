package ru.zulu.galaxian.enemy;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import ru.zulu.galaxian.core.BaseManager;
import ru.zulu.galaxian.core.OnUpdateListener;
import ru.zulu.galaxian.core.views.BaseView.OnDrawListener;
import ru.zulu.galaxian.enemy.models.BaseEnemy;
import ru.zulu.galaxian.enemy.models.BlueEnemy;
import ru.zulu.galaxian.enemy.models.EnemyState;
import ru.zulu.galaxian.enemy.models.FlagshipEnemy;
import ru.zulu.galaxian.enemy.models.PurpleEnemy;
import ru.zulu.galaxian.enemy.models.RedEnemy;

public class EnemyManager extends BaseManager implements OnUpdateListener, OnDrawListener {
	// =============================================================================================
	// CONSTATNS
	// =============================================================================================
	private static final int COLUMNS_COUNT = 10;
	private static final int RED_ENIMIES_COUNT = 6;
	private static final int PURPLE_ENIMIES_COUNT = 8;
	private static final int BLUE_ENIMIES_COUNT = 30;

	private static final int LEFT_MARGIN = 10;
	private static final int TOP_MARGIN = 50;
	private static final int RIGHT_MARGIN = 10;
	private static final int CELL_WIDTH = 32;
	private static final int CELL_HEIGHT = 32;
	private static final int COLUMN_GAP = 10;
	private static final int ROW_GAP = 4;

	private static final int FORMATION_SPEED = 2;

	// =============================================================================================
	// FIELDS
	// =============================================================================================
	private ArrayList<BaseEnemy> enemies;
	private Random random;
	private boolean isMovingLeft;

	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public EnemyManager() {
		enemies = new ArrayList<BaseEnemy>();
		random = new Random();
		initEnemies();
	}

	// =============================================================================================
	// METHODS OF BASE CLASSES
	// =============================================================================================
	@Override
	public void start() {
		super.start();
		isMovingLeft = random.nextBoolean();
		initEnemiesPosition();
	}

	// =============================================================================================
	// METHODS
	// =============================================================================================

	private void initEnemies() {
		// enemies placement scheme
		// F F
		// RRRRRR
		// PPPPPPPP
		// BBBBBBBBBB
		// BBBBBBBBBB
		// BBBBBBBBBB

		// zero row - two flagships on 3th and 6th columns
		enemies.add(new FlagshipEnemy(0, 3));
		enemies.add(new FlagshipEnemy(0, 6));

		// 1st row - six red from 2nd to 7th columns
		for (int i = 0; i < RED_ENIMIES_COUNT; i++) {
			int column = i + 2;
			enemies.add(new RedEnemy(1, column));
		}

		// 2nd row - eight purple from 1st to 8th columns
		for (int i = 0; i < PURPLE_ENIMIES_COUNT; i++) {
			int column = i + 1;
			enemies.add(new PurpleEnemy(2, column));
		}

		// 3-5 rows - thirty blue, ten per row
		for (int i = 0; i < BLUE_ENIMIES_COUNT; i++) {
			int column = i % COLUMNS_COUNT;
			int row = 3 + i / COLUMNS_COUNT;
			enemies.add(new BlueEnemy(row, column));
		}
	}

	private void initEnemiesPosition() {
		int gridWidth = COLUMNS_COUNT * CELL_WIDTH + (COLUMNS_COUNT - 1) * COLUMN_GAP;
		int leftMargin = (gameAreaWidth - gridWidth) / 2;
		for (BaseEnemy enemy : enemies) {
			enemy.x = leftMargin + enemy.column * CELL_WIDTH + enemy.column * COLUMN_GAP;
			enemy.y = TOP_MARGIN + enemy.row * CELL_HEIGHT + enemy.row * ROW_GAP;
		}
	}

	// =============================================================================================
	// UPDATE
	// =============================================================================================
	@Override
	public void onUpdate() {
		int offsetX = isMovingLeft ? -FORMATION_SPEED : FORMATION_SPEED;
		boolean needToChangeDirection = false;
		for (BaseEnemy enemy : enemies) {
			if (enemy.state != EnemyState.DEAD) {
				enemy.update();
				enemy.x += offsetX;
				if (!needToChangeDirection) {
					needToChangeDirection = enemy.x < LEFT_MARGIN
							|| enemy.x + enemy.width > gameAreaWidth - RIGHT_MARGIN;
				}
			}
		}
		if (needToChangeDirection) {
			isMovingLeft = !isMovingLeft;
		}
	}

	@Override
	public void onDraw(Graphics _graphics) {
		for (BaseEnemy enemy : enemies) {
			if (enemy.state != EnemyState.DEAD) {
				enemy.draw(_graphics);
			}
		}

	}
}
