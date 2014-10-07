package ru.zulu.galaxian.background;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.zulu.galaxian.background.models.Star;
import ru.zulu.galaxian.core.OnUpdateListener;

public class BackgroundManager implements OnUpdateListener {
	public interface OnUpdateBackgroundViewListener {
		void onUpdate(List<Star> _stars);
	}
	
    // =============================================================================================
    // CONSTANTS
    // =============================================================================================
	private static final int STAR_SPEED = 2;
	private static final int STAR_COUNT = 100;
	
    // =============================================================================================
    // FIELDS
    // =============================================================================================
	private final Random random = new Random();
	private ArrayList<Star> stars;
	private OnUpdateBackgroundViewListener onUpdateBackgroundViewListener;
	private int viewWidth;
	private int viewHeight;
	
    // =============================================================================================
    // GETTERS/SETTERS
    // =============================================================================================
	public void setOnUpdateViewListener(OnUpdateBackgroundViewListener _onUpdateBackgroundViewListener) {
		onUpdateBackgroundViewListener = _onUpdateBackgroundViewListener;
	}
	
	public void setViewWidth(int _viewWidth) {
		viewWidth = _viewWidth;
	}
	
	public void setViewHeight(int _viewHeight) {
		viewHeight = _viewHeight;
	}
	
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
		if (onUpdateBackgroundViewListener != null) {
			onUpdateBackgroundViewListener.onUpdate(stars);
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
			stars.add(star);
		}
	}
	
    private void updateStars() {
		for (Star star : stars) {
			if (star.updatesToChangeVisibility-- == 0) {
				star.isVisible = !star.isVisible;
				star.updatesToChangeVisibility = random.nextInt(Star.MAX_UPDATES_TO_CHANGE_VISIBILITY - Star.MIN_UPDATES_TO_CHANGE_VISIBILITY) + Star.MIN_UPDATES_TO_CHANGE_VISIBILITY;
			}
			star.y += STAR_SPEED;
			if (star.y > viewHeight) {
				star.x = getRandomX();
				star.y = 0;
			}
		}
		
	}
    
    private int getRandomX() {
    	return random.nextInt(viewWidth);
    }
    
    private int getRandomY() {
    	return random.nextInt(viewHeight);
    }
}
