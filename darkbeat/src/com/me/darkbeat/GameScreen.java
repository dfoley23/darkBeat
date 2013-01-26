package com.me.darkbeat;

import java.util.Random;

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
	private Sprite wallFront[];
	private Sprite wallLeft[];
	private Sprite wallRight[];
	private Sprite floor;
	private int mapArray[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
			0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1,
			1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0,
			0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0,
			0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0,
			0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1,
			1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1,
			0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	
	private Player nickCage;
	private Level testlevel;
	private int lWidth;
	private int lHeight;
	private int[] test = new int[100];
	private int[] textureArray = new int[test.length];
	
	public GameScreen() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		lWidth = 10;
		lHeight = 10;

		camera = new OrthographicCamera(1, h / w);
		batch = new SpriteBatch();

		Texture wallFrontTex1 = new Texture(
				Gdx.files.internal("data/textures/walls/stone_wall_1_front.png"));
		wallFrontTex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture wallFrontTex2 = new Texture(
				Gdx.files.internal("data/textures/walls/stone_wall_2_front.png"));
		wallFrontTex2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture wallFrontTex3 = new Texture(
				Gdx.files.internal("data/textures/walls/stone_wall_3_fornt.png"));
		wallFrontTex3.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture wallFrontTex4 = new Texture(
				Gdx.files.internal("data/textures/walls/stone_wall_4_front.png"));
		wallFrontTex4.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture wallLeftTex1 = new Texture(
				Gdx.files.internal("data/textures/walls/stone_wall_1_left.png"));
		wallLeftTex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture wallLeftTex2 = new Texture(
				Gdx.files.internal("data/textures/walls/stone_wall_2_left.png"));
		wallLeftTex2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture wallLeftTex3 = new Texture(
				Gdx.files.internal("data/textures/walls/stone_wall_3_left.png"));
		wallLeftTex3.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture wallLeftTex4 = new Texture(
				Gdx.files.internal("data/textures/walls/stone_wall_4_left.png"));
		wallLeftTex4.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture wallRightTex1 = new Texture(
				Gdx.files.internal("data/textures/walls/stone_wall_1_right.png"));
		wallRightTex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture wallRightTex2 = new Texture(
				Gdx.files.internal("data/textures/walls/stone_wall_2_right.png"));
		wallRightTex2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture wallRightTex3 = new Texture(
				Gdx.files.internal("data/textures/walls/stone_wall_3_right.png"));
		wallRightTex3.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture wallRightTex4 = new Texture(
				Gdx.files.internal("data/textures/walls/stone_wall_4_right.png"));
		wallRightTex4.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture floorTex = new Texture(Gdx.files.internal("data/floor.png"));

		TextureRegion region1 = new TextureRegion(wallFrontTex1, 0, 0, 800, 600);
		TextureRegion region2 = new TextureRegion(wallLeftTex1, 0, 0, 800, 600);
		TextureRegion region3 = new TextureRegion(wallRightTex1, 0, 0, 800, 600);

		wallFront = new Sprite[4];
		wallLeft = new Sprite[4];
		wallRight = new Sprite[4];
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 1:
				region1 = new TextureRegion(wallFrontTex2, 0, 0, 64, 64);
				region2 = new TextureRegion(wallLeftTex2, 0, 0, 64, 64);
				region3 = new TextureRegion(wallRightTex2, 0, 0, 64, 64);
				break;
			case 2:
				region1 = new TextureRegion(wallFrontTex3, 0, 0, 64, 64);
				region2 = new TextureRegion(wallLeftTex3, 0, 0, 64, 64);
				region3 = new TextureRegion(wallRightTex3, 0, 0, 64, 64);
				break;
			case 3:
				region1 = new TextureRegion(wallFrontTex4, 0, 0, 64, 64);
				region2 = new TextureRegion(wallLeftTex4, 0, 0, 64, 64);
				region3 = new TextureRegion(wallRightTex4, 0, 0, 64, 64);
				break;
			default:
				break;
			}
			wallFront[i] = new Sprite(region1);
			wallFront[i].setSize(1, 1 * wallFront[i].getHeight()
					/ wallFront[i].getWidth());
			wallFront[i].setOrigin(0.5f, 0.5f);
			wallFront[i].setPosition(-wallFront[i].getWidth()/2.0f, -wallFront[i].getHeight()/2.0f);
			wallLeft[i] = new Sprite(region2);
			wallLeft[i].setSize(1, 1 * wallLeft[i].getHeight()
					/ wallLeft[i].getWidth());
			wallLeft[i].setOrigin(0.5f, 0.5f);
			wallLeft[i].setPosition(-wallLeft[i].getWidth()/2.0f, -wallLeft[i].getHeight()/2.0f);
			wallRight[i] = new Sprite(region3);
			wallRight[i].setSize(1, 1 * wallRight[i].getHeight()
					/ wallRight[i].getWidth());
			wallRight[i].setOrigin(0.5f, 0.5f);
			wallRight[i].setPosition(-wallRight[i].getWidth()/2.0f, -wallRight[i].getHeight()/2.0f);
		}

		floor = new Sprite(region2);
		floor.setSize(0.5f, 0.5f * floor.getHeight() / floor.getWidth());
		floor.setOrigin(0, 0);

		for (int i = 0; i < 10; i++) {
			test[i] = 1;
		}
		for (int i = 10; i < 90; i++) {
			if (i % lWidth == 9 || i % lHeight == 0)
				test[i] = 1;
			else
				test[i] = 0;
		}
		for (int i = 90; i < 100; i++) {
			test[i] = 1;
		}

		for(int i=0; i<test.length; i++){
			textureArray[i] = test[i];
		}
		Random randGen = new Random();
		int randNum = 0;
		for (int i = 0; i < textureArray.length; i++) {
			if (textureArray[i] == 1) {
				randNum = randGen.nextInt(4) + 1;
				textureArray[i] = randNum;
			}
		}

		nickCage = new Player(45, 10);

		testlevel = new Level(lWidth, lHeight, test);

		// testDraw();

	}

	public void update() {

		nickCage.update();
		if (nickCage.changedPosition) {
			testlevel.checkPlayer(nickCage);
			testDraw();
			nickCage.changedPosition = false;
		} else if (nickCage.changedDirection) {
			testDraw();
			nickCage.changedDirection = false;
		}
	}

	public void dispose() {

	}

	public void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		int left = 0;
		int right = 0;
		int front = 0;
		switch (nickCage.getDirection()) {
		case North:
			left = textureArray[nickCage.getPosition() - 1];
			right = textureArray[nickCage.getPosition() + 1];
			front = textureArray[nickCage.getPosition() - testlevel.getWidth()];
			break;
		case South:
			left = textureArray[nickCage.getPosition() + 1];
			right = textureArray[nickCage.getPosition() - 1];
			front = textureArray[nickCage.getPosition() + testlevel.getWidth()];
			break;
		case East:
			left = textureArray[nickCage.getPosition() - testlevel.getWidth()];
			right = textureArray[nickCage.getPosition() + testlevel.getWidth()];
			front = textureArray[nickCage.getPosition() + 1];
			break;
		case West:
			left = textureArray[nickCage.getPosition() - testlevel.getWidth()];
			right = textureArray[nickCage.getPosition() + testlevel.getWidth()];
			front = textureArray[nickCage.getPosition() - 1];
			break;
		default:
			break;
		}
		// render left wall if its there
		switch (left) {
		case 1:
			wallLeft[0].draw(batch);
			break;
		case 2:
			wallLeft[1].draw(batch);
			break;
		case 3:
			wallLeft[2].draw(batch);
			break;
		case 4:
			wallLeft[3].draw(batch);
			break;
		default:
			break;
		}
		// render right wall if its there
		switch (right) {
		case 1:
			wallRight[0].draw(batch);
			break;
		case 2:
			wallRight[1].draw(batch);
			break;
		case 3:
			wallRight[2].draw(batch);
			break;
		case 4:
			wallRight[3].draw(batch);
			break;
		default:
			break;
		}

		// render front wall if its there
		switch (front) {
		case 1:
			wallFront[0].draw(batch);
			break;
		case 2:
			wallFront[1].draw(batch);
			break;
		case 3:
			wallFront[2].draw(batch);
			break;
		case 4:
			wallFront[3].draw(batch);
			break;
		default:
			break;
		}

		batch.end();

	}

	public void testDraw() {
		int p = 0;
		for (int i = 0; i < lHeight; i++) {
			for (int j = 0; j < lWidth; j++) {
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
