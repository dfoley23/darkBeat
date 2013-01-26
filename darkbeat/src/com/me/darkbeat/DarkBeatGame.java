package com.me.darkbeat;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
