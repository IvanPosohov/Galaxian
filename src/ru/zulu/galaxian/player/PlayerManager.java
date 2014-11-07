package ru.zulu.galaxian.player;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ru.zulu.galaxian.core.BaseManager;
import ru.zulu.galaxian.core.OnUpdateListener;
import ru.zulu.galaxian.core.views.BaseView.OnDrawListener;
import ru.zulu.galaxian.player.models.Bullet;
import ru.zulu.galaxian.player.models.MoveDirection;
import ru.zulu.galaxian.player.models.Player;

public class PlayerManager extends BaseManager implements OnUpdateListener, OnDrawListener, KeyListener {
	// =============================================================================================
	// CONSTANTS
	// =============================================================================================

	// =============================================================================================
	// FIELDS
	// =============================================================================================
	private Player player;
	private Bullet bullet;
	private MoveDirection moveDirection;

	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public PlayerManager() {
		player = new Player();
		bullet = new Bullet();
		moveDirection = MoveDirection.NONE;
	}

	// =============================================================================================
	// METHODS OF BASE CLASSES
	// =============================================================================================
	@Override
	public void setGameAreaSize(int _width, int _height) {
		super.setGameAreaSize(_width, _height);
		player.y = _height - player.height;
	}

	@Override
	public void start() {
		super.start();
		player.x = gameAreaWidth / 2 - player.width / 2;
		initBullet();
	}

	// =============================================================================================
	// UPDATE
	// =============================================================================================
	@Override
	public void onUpdate() {
		if (moveDirection == MoveDirection.LEFT) {
			moveShipLeft();
		} else if (moveDirection == MoveDirection.RIGHT) {
			moveShipRight();
		}
		updateBullet();
	}

	private void moveShipLeft() {
		player.x -= Player.SPEED;
		if (player.x < 0) {
			player.x = 0;
		}
	}

	private void moveShipRight() {
		player.x += Player.SPEED;
		if (player.x + player.width > gameAreaWidth) {
			player.x = gameAreaWidth - player.width;
		}
	}

	private void updateBullet() {
		if (bullet.ifFlies) {
			bullet.y -= Bullet.SPEED;
			if (bullet.y + bullet.width <= 0) {
				initBullet();
			}
		} else {
			bullet.x = player.getCenterX();
		}
	}

	private void initBullet() {
		bullet.ifFlies = false;
		bullet.x = player.getCenterX();
		bullet.y = gameAreaHeight - player.height - bullet.height;
	}

	@Override
	public void onDraw(Graphics _graphics) {
		player.draw(_graphics);
		bullet.draw(_graphics);
	}

	// =============================================================================================
	// KEYBOARD EVENT HANDLERS
	// =============================================================================================
	@Override
	public void keyPressed(KeyEvent _keyEvent) {
		switch (_keyEvent.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			moveDirection = MoveDirection.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			moveDirection = MoveDirection.RIGHT;
			break;
		case KeyEvent.VK_SPACE:
			shoot();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent _keyEvent) {
		switch (_keyEvent.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (moveDirection == MoveDirection.LEFT) {
				moveDirection = MoveDirection.NONE;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (moveDirection == MoveDirection.RIGHT) {
				moveDirection = MoveDirection.NONE;
			}
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent _keyEvent) {
	}

	// =============================================================================================
	// METHODS
	// =============================================================================================
	private void shoot() {
		if (!bullet.ifFlies) {
			bullet.ifFlies = true;
		}
	}
}
