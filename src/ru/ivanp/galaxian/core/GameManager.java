package ru.ivanp.galaxian.core;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ru.ivanp.galaxian.background.BackgroundManager;
import ru.ivanp.galaxian.core.models.GameState;
import ru.ivanp.galaxian.core.views.BaseView;
import ru.ivanp.galaxian.world.WorldManager;

import javax.swing.*;

public class GameManager implements BaseView.EventsListener, KeyListener {
    // =============================================================================================
    // CONSTANTS
    // =============================================================================================
    private static final int WORLD_UPDATE_INTERVAL_MILLIS = 30;

    // =============================================================================================
    // FIELDS
    // =============================================================================================
    private final BaseView view;
    // Менеджеры отвечающие за свои частии игры
    private BackgroundManager backgroundManager;
    private WorldManager worldManager;
    // Состояние игры
    private GameState state;
    private Timer worldTimer;

    // =============================================================================================
    // CONSTRUCTOR
    // =============================================================================================
    public GameManager(BaseView view) {
        this.view = view;
        view.setEventsListener(this);
        backgroundManager = new BackgroundManager();
        worldManager = new WorldManager();
        worldTimer = new Timer(WORLD_UPDATE_INTERVAL_MILLIS, worldTimerTask);
        state = GameState.IDLE;
    }

    // =============================================================================================
    // METHODS
    // =============================================================================================
    /**
     * Запустить игру
     */
    public void start() {
        state = GameState.RUNNING;
        backgroundManager.init();
        worldManager.init();
        worldTimer.start();
    }

    /**
     * Приостановить игру
     */
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
            update();
            render();
        }
    };

    /**
     * Пересчет мира, положения столкновения и прочая физика
     */
    private void update() {
        backgroundManager.update();
        worldManager.update();
    }

    /**
     * Отрисовка мира
     */
    private void render() {
        // Сообщаем вьюшке, что она должна перерисоваться. В ответ на это будет вызван метод render(),
        // в котором мы и должны рисовать свои шарики, квадратики, whatever
        view.repaint();
    }

    // =============================================================================================
    // BASE VIEW HANDLERS
    // =============================================================================================
    @Override
    public void onDraw(Graphics g) {
        if (state == GameState.RUNNING) {
            backgroundManager.render(g);
            worldManager.render(g);
        }
    }

    @Override
    public void onResize(int width, int height) {
        backgroundManager.resize(width, height);
        worldManager.resize(width, height);
    }

    // =============================================================================================
    // KEYBOARD EVENT HANDLERS
    // =============================================================================================
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        worldManager.keyPressed(keyEvent);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        worldManager.keyReleased(keyEvent);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }
}
