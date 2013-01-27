package com.me.darkbeat;

import com.badlogic.gdx.ApplicationListener;

public class DarkBeatGame implements ApplicationListener {
	private ScreenManager sm;
	
	@Override
	public void create() {		
		
		sm = new ScreenManager();
		
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public void render() {		
		sm.update(); 

		sm.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
