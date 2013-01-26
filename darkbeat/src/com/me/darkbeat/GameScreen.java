package com.me.darkbeat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite wallFront;
	private Sprite wallLeft;
	private Sprite wallRight;
	private Sprite floor;
	private int mapArray[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
							   1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 
							   1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1,
							   1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 
							   1, 0, 0, 0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 1, 
							   1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1,
							   1, 0, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0, 0, 1, 
							   1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 
							   1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 
							   1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 
							   1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 
							   1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 1,
							   1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	private Player player;
	
	public GameScreen(){
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
	
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		Texture wallTex = new Texture(Gdx.files.internal("data/wall.png"));
		Texture floorTex = new Texture(Gdx.files.internal("data/floor.png"));
		
		wallTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		floorTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region1 = new TextureRegion(wallTex, 0, 0, 64, 64);
		TextureRegion region2 = new TextureRegion(floorTex, 0, 0, 64, 64);
		
		wallFront = new Sprite(region1);
		wallFront.setSize(0.5f, 0.5f * wallFront.getHeight() / wallFront.getWidth());
		wallFront.setOrigin(0, 0);
		wallLeft = new Sprite(region1);
		wallLeft.setSize(0.5f, 0.5f * wallLeft.getHeight() / wallLeft.getWidth());
		wallLeft.setOrigin(0, 0);	
		wallRight = new Sprite(region1);
		wallRight.setSize(0.5f, 0.5f * wallRight.getHeight() / wallRight.getWidth());
		wallRight.setOrigin(0, 0);
		
		floor = new Sprite(region1);
		floor.setSize(0.5f, 0.5f * floor.getHeight() / floor.getWidth());
		floor.setOrigin(0, 0);
		
		player = new Player(15, 14);
		
	}
	
	public void update(){
		player.update();
		
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
