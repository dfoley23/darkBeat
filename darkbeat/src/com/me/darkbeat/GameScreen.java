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
	private Player nickCage;
	private Level testlevel;
	private int lWidth;
	private int lHeight;
	private int[] test = new int[100];

	
	public GameScreen(){
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		lWidth = 10;
		lHeight = 10;
	
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
		
		floor = new Sprite(region2);
		floor.setSize(0.5f, 0.5f * floor.getHeight() / floor.getWidth());
		floor.setOrigin(0, 0);

		for(int i = 0; i < 10; i++){
			 test[i] = 1;
		 }
		 for(int i = 10; i < 90; i++){
			 if(i % lWidth == 9 || i % lHeight == 0)
				 test[i] = 1;
			 else
				 test[i] = 0;
		 }
		 for(int i = 90; i < 100; i++){
			 test[i] = 1;
		 }
		 
		 nickCage = new Player(45,10);
		 
		 testlevel = new Level(lWidth, lHeight, test);
		 
		 //testDraw();
		
	}
	
	public void update(){

		nickCage.update();
		if (nickCage.changedPosition){
			testlevel.checkPlayer(nickCage);
			testDraw();
			nickCage.changedPosition = false;
		} else if (nickCage.changedDirection){
			testDraw();
			nickCage.changedDirection = false;
		}
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
	
	public void testDraw(){
		int p = 0;
		for(int i = 0; i < lHeight; i++){
			for(int j = 0; j < lWidth; j++){
				System.out.print(test[p]);
				System.out.print(" ");
				p++;
			}
			System.out.print("\n");
		}
		System.out.println("Position: " + nickCage.getPosition());
		System.out.println("Facing: " + nickCage.getDirection());
	}

}
