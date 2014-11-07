package ru.zulu.galaxian.core;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import ru.zulu.galaxian.background.BackgroundManager;
import ru.zulu.galaxian.core.models.GameState;
import ru.zulu.galaxian.core.views.BaseView;
import ru.zulu.galaxian.core.views.BaseView.OnDrawListener;
import ru.zulu.galaxian.enemy.EnemyManager;
import ru.zulu.galaxian.player.PlayerManager;

public class GameManager extends BaseManager implements KeyListener, OnDrawListener {
	// =============================================================================================
	// CONSTANTS
	// =============================================================================================
	private static final int WORLD_UPDATE_INTERVAL_MILLIS = 30;

	// =============================================================================================
	// FIELDS
	// =============================================================================================
	private BackgroundManager backgroundManager;
	private PlayerManager playerManager;
	private EnemyManager enemyManager;
	private BaseView view;
	private Timer worldTimer;
	private GameState state;

	// =============================================================================================
	// GETTERS/SETTERS
	// =============================================================================================
	public void setView(BaseView _view) {
		if (view != null) {
			view.setOnDrawListener(null);
		}
		view = _view;
		view.setOnDrawListener(this);
	}
	
	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public GameManager() {
		backgroundManager = new BackgroundManager();
		playerManager = new PlayerManager();
		enemyManager = new EnemyManager();
		worldTimer = new Timer(WORLD_UPDATE_INTERVAL_MILLIS, worldTimerTask);
		state = GameState.IDLE;
	}

	// =============================================================================================
	// METHODS OF BASE CLASSES
	// =============================================================================================
	public void setGameAreaSize(int _width, int _height) {
		super.setGameAreaSize(_width, _height);
		backgroundManager.setGameAreaSize(_width, _height);
		playerManager.setGameAreaSize(_width, _height);
		enemyManager.setGameAreaSize(_width, _height);
	};

	// =============================================================================================
	// METHODS
	// =============================================================================================
	@Override
	public void start() {
		state = GameState.RUNNING;
		playerManager.start();
		enemyManager.start();
		worldTimer.start();
	}

	public void stop() {
		state = GameState.PAUSED;
		worldTimer.stop();
		if (view != null) {
			view.repaint();
		}
	}

	// =============================================================================================
	// UPDATING
	// =============================================================================================
	private ActionListener worldTimerTask = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateWorld();
		}
	};

	public void updateWorld() {
		backgroundManager.onUpdate();
		playerManager.onUpdate();
		enemyManager.onUpdate();
		if (view != null) {
			view.repaint();
		}
	}

	@Override
	public void onDraw(Graphics _graphics) {
		if (state == GameState.RUNNING) {
			backgroundManager.onDraw(_graphics);
			playerManager.onDraw(_graphics);
			enemyManager.onDraw(_graphics);
		}
	}

	// =============================================================================================
	// KEYBOARD EVENT HANDLERS
	// =============================================================================================
	@Override
	public void keyPressed(KeyEvent _keyEvent) {
		playerManager.keyPressed(_keyEvent);
	}

	@Override
	public void keyReleased(KeyEvent _keyEvent) {
		playerManager.keyReleased(_keyEvent);
	}

	@Override
	public void keyTyped(KeyEvent _keyEvent) {
		playerManager.keyTyped(_keyEvent);
	}
}
