package ru.zulu.galaxian.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameManager {
	// =============================================================================================
	// CONSTANTS
	// =============================================================================================
	private static final int WORLD_UPDATE_INTERVAL_MILLIS = 30;

	// =============================================================================================
	// FIELDS
	// =============================================================================================
	private Timer worldTimer;
	private List<OnUpdateListener> onUpdateListeners;

	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public GameManager() {
		worldTimer = new Timer();
		onUpdateListeners = new ArrayList<OnUpdateListener>();
	}

	// =============================================================================================
	// WORLD TIMER
	// =============================================================================================
	private TimerTask worldTimerTask = new TimerTask() {
		@Override
		public void run() {
			for (OnUpdateListener onUpdateListener : onUpdateListeners) {
				onUpdateListener.onUpdate();
			}
		}
	};

	// =============================================================================================
	// METHODS
	// =============================================================================================
	public void start() {
		worldTimer.schedule(worldTimerTask, 0, WORLD_UPDATE_INTERVAL_MILLIS);
	}

	public void stop() {
		worldTimer.cancel();
	}

	public void addOnUpdateListener(OnUpdateListener _onUpdateListener) {
		onUpdateListeners.add(_onUpdateListener);
	}

	public void removeOnUpdateListener(OnUpdateListener _onUpdateListener) {
		onUpdateListeners.remove(_onUpdateListener);
	}
}
