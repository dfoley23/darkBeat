package com.me.darkbeat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	public GameScreen(){
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
	
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
	}
	
	public void update(){
		
	}
	
	public void dispose(){
		
	}
	
	public void draw(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.end();
		
	}

}
