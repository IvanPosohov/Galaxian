package ru.ivanp.galaxian.world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ru.ivanp.galaxian.core.models.BaseManager;
import ru.ivanp.galaxian.utils.RandomUtils;
import ru.ivanp.galaxian.world.models.BaseEnemy;
import ru.ivanp.galaxian.world.models.BlueEnemy;
import ru.ivanp.galaxian.world.models.Bullet;
import ru.ivanp.galaxian.world.models.EnemyState;
import ru.ivanp.galaxian.world.models.FlagshipEnemy;
import ru.ivanp.galaxian.world.models.Player;
import ru.ivanp.galaxian.world.models.PurpleEnemy;
import ru.ivanp.galaxian.world.models.RedEnemy;

public class WorldManager extends BaseManager {
    // =============================================================================================
    // CONSTANTS
    // =============================================================================================
    private static final int COLUMNS_COUNT = 10;
    private static final int ROW_COUNT = 6;
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

    private static final int ENEMIES_FORMATION_X_VELOCITY = 2;
    private static final int ENEMIES_FORMATION_BOTTOM = TOP_MARGIN + ROW_COUNT * CELL_HEIGHT;

    // =============================================================================================
    // FIELDS
    // =============================================================================================
    private boolean[] keys;
    private Player player;
    private Bullet bullet;
    private ArrayList<BaseEnemy> enemies;
    private int enemiesVelocity;
    private boolean holdEnemies;

    // =============================================================================================
    // CONSTRUCTOR
    // =============================================================================================
    public WorldManager() {
        keys = new boolean[65536];
        player = new Player();
        bullet = new Bullet();
        enemies = new ArrayList<BaseEnemy>();
        enemiesVelocity = RandomUtils.nextBoolean() ? ENEMIES_FORMATION_X_VELOCITY : -ENEMIES_FORMATION_X_VELOCITY;
        initEnemies();
    }

    // =============================================================================================
    // UPDATE
    // =============================================================================================
    @Override
    public void init() {
        initPlayer();
        initBullet();
        initEnemiesPosition();
    }

    @Override
    public void update() {
        processInput();
        updatePlayer();
        updateBullet();
        updateEnemies();
    }

    @Override
    public void render(Graphics g) {
        player.draw(g);
        bullet.draw(g);
        for (BaseEnemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    // =============================================================================================
    // PLAYER
    // =============================================================================================
    private void initPlayer() {
        player.x = width / 2 - player.width / 2;
        player.y = height - player.height;
    }

    private void updatePlayer() {
        player.move();
        if (player.x < 0) {
            player.x = 0;
        } else if (player.x + player.width > width) {
            player.x = width - player.width;
        }
    }

    // =============================================================================================
    // BULLET
    // =============================================================================================
    private void initBullet() {
        bullet.ifFlies = false;
        holdEnemies = false;
        bullet.x = player.getCenterX();
        bullet.y = height - player.height - bullet.height;
    }

    private void shoot() {
        if (!bullet.ifFlies) {
            bullet.ifFlies = true;
        }
    }

    private void updateBullet() {
        // TODO too complex logic, refactor plz
        // check collision between enemy and player's bullet
        if (bullet.ifFlies) {
            int nextY = bullet.y + bullet.yVelocity;
            boolean isKilled = false;

            // One Flew Over the Cuckoo's Nest
            if (nextY <= ENEMIES_FORMATION_BOTTOM) {
                holdEnemies = true;
            }

            // simple Continuous Collision Detection
            out_of_loop: while (nextY < bullet.y) {
                for (BaseEnemy enemy : enemies) {
                    if (enemy.state != EnemyState.DEAD && enemy.state != EnemyState.DYING && bullet.isIntersects(enemy)) {
                        enemy.kill();
                        isKilled = true;
                        break out_of_loop;
                    }
                }
                bullet.y--;
            }
            if (isKilled) {
                initBullet();
            } else {
                bullet.y = nextY;
            }

            // out of game field
            if (bullet.y + bullet.width <= 0) {
                initBullet();
            }
        } else {
            bullet.x = player.getCenterX();
        }
    }

    // =============================================================================================
    // ENIMIES
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
        int leftMargin = (width - gridWidth) / 2;
        for (BaseEnemy enemy : enemies) {
            enemy.x = leftMargin + enemy.column * CELL_WIDTH + enemy.column * COLUMN_GAP;
            enemy.y = TOP_MARGIN + enemy.row * CELL_HEIGHT + enemy.row * ROW_GAP;
        }
    }

    private void updateEnemies() {
        boolean needToChangeDirection = false;
        for (BaseEnemy enemy : enemies) {
            if (enemy.state != EnemyState.DEAD) {
                if (!holdEnemies) {
                    enemy.x += enemiesVelocity;
                    if (!needToChangeDirection) {
                        needToChangeDirection = enemy.x < LEFT_MARGIN
                                || enemy.x + enemy.width > width - RIGHT_MARGIN;
                    }
                }
            }
        }
        if (needToChangeDirection) {
            enemiesVelocity = -enemiesVelocity;
        }
    }

    // =============================================================================================
    // KEYBOARD EVENT HANDLERS
    // =============================================================================================
    private void processInput() {
        player.xVelocity = 0;
        if (keys[KeyEvent.VK_LEFT] && !keys[KeyEvent.VK_RIGHT]) {
            player.xVelocity = -Player.DEFAULT_X_VELOCITY;
        }
        if (!keys[KeyEvent.VK_LEFT] && keys[KeyEvent.VK_RIGHT]) {
            player.xVelocity = Player.DEFAULT_X_VELOCITY;
        }
        if (keys[KeyEvent.VK_SPACE]) {
            shoot();
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (code >= 0 && code < keys.length) {
            keys[code] = true;
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (code >= 0 && code < keys.length) {
            keys[code] = false;
        }
    }
}
