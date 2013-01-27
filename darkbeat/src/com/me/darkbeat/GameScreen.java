package com.me.darkbeat;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.darkbeat.Player.Direction;

public class GameScreen {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite wallFront[];
	private Sprite wallLeft[];
	private Sprite wallRight[];
	private Sprite handFront;
	private Sprite handRight;
	private Sprite handLeft;
	private Sprite handOut;
	private Sprite doorFront;
	private Sprite doorRight;
	private Sprite doorLeft;
	private Sprite catSprite;
	private Sprite heart1;
	private Sprite heart2;
	private Sprite heart3;
	private Sprite heartDoorLeft;
	private Sprite heartDoorRight;
	private Sprite heartDoorFront;
	private Sprite win0;
	private Sprite win1;
	private Sprite win2;
	private Sprite win3;
	private Sprite win4;
	private Sprite win5;
	private Sprite win6;
	private Sprite win7;
	private Music heartbeat;
	private Sound beat;
	private Sound beat_up;
	private Sound beat_down;
	
	private long beat_id;
	public boolean switchScreen = false;

	// private int test[] = { //14 x 10
	// 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
	// 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 4, 0, 1,
	// 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1,
	// 1, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0, 4, 3, 1,
	// 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 3, 1,
	// 1, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 1,
	// 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1,
	// 1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1,
	// 1, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 1,
	// 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1,
	// 1, 0, 0, 8, 0, 0, 0, 1, 0, 0, 0, 1, 4, 1,
	// 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1,
	// 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1,
	// 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, };
	private int test[] = { // 14 x 10
	1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
	1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 4, 3, 1, 
	1, 1, 0, 4, 0, 0, 0, 4, 0, 1, 1, 1, 1, 1, 
	1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 
	1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1,
	1, 1, 1, 1, 4, 1, 0, 1, 1, 3, 0, 0, 0, 1,
	1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1,
	1, 0, 0, 3, 0, 0, 0, 0, 1, 1, 1, 1, 4, 1, 
	1, 1, 4, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 
	1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 
	9, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 
	1, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 8, 0, 1, 
	1, 0, 0, 4, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1,
	1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, };

	private Player nickCage;
	private Level testlevel;
	private int lWidth;
	private int lHeight;
	private int[] textureArray = new int[test.length];
	private Cat[] cats;
	private int heartPosition;
	private int endDoorPosition;
	private int catCounter = 0;
	private int frameCounter = 0;
	private int frameDivisor = 7;
	private int maxAnimeTime = 100;
	private boolean didBeat;

	public GameScreen() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		lWidth = 14;
		lHeight = 14;

		camera = new OrthographicCamera(1, h / w);
		batch = new SpriteBatch();

		heartbeat = Gdx.audio.newMusic(Gdx.files
				.internal("data/sounds/Heartbeat.ogg"));

		heartbeat.setLooping(true);
		heartbeat.setVolume(0.0f);
		//heartbeat.play();
		
		beat = Gdx.audio.newSound(Gdx.files.internal("data/sounds/h.ogg"));
		beat_id = beat.play();
		beat.setLooping(beat_id, true);
		beat.setVolume(beat_id, 0.0f);
		
		beat_up = Gdx.audio.newSound(Gdx.files.internal("data/sounds/h1.ogg"));
		beat_down = Gdx.audio.newSound(Gdx.files.internal("data/sounds/h2.ogg"));

		Texture winTex0 = new Texture(
				Gdx.files.internal("data/textures/win/win0.png"));
		winTex0.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture winTex1 = new Texture(
				Gdx.files.internal("data/textures/win/win1.png"));
		winTex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture winTex2 = new Texture(
				Gdx.files.internal("data/textures/win/win2.png"));
		winTex2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture winTex3 = new Texture(
				Gdx.files.internal("data/textures/win/win3.png"));
		winTex3.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture winTex4 = new Texture(
				Gdx.files.internal("data/textures/win/win4.png"));
		winTex4.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture winTex5 = new Texture(
				Gdx.files.internal("data/textures/win/win5.png"));
		winTex5.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture winTex6 = new Texture(
				Gdx.files.internal("data/textures/win/win6.png"));
		winTex6.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture winTex7 = new Texture(
				Gdx.files.internal("data/textures/win/win7.png"));
		winTex7.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		Texture heartDoor1 = new Texture(
				Gdx.files.internal("data/textures/door/heart_door_left.png"));
		heartDoor1.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture heartDoor2 = new Texture(
				Gdx.files.internal("data/textures/door/heart_door_right.png"));
		heartDoor2.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture heartDoor3 = new Texture(
				Gdx.files.internal("data/textures/door/heart_door_front.png"));
		heartDoor3.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture heartTex1 = new Texture(
				Gdx.files.internal("data/textures/hands/hand_heart_0.png"));
		heartTex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture heartTex2 = new Texture(
				Gdx.files.internal("data/textures/hands/hand_heart_1.png"));
		heartTex2.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture heartTex3 = new Texture(
				Gdx.files.internal("data/textures/hands/hand_heart_2.png"));
		heartTex3.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture catTex = new Texture(
				Gdx.files.internal("data/textures/misc/cat.png"));
		catTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture hand1Tex = new Texture(
				Gdx.files.internal("data/textures/hands/hand_front.png"));
		hand1Tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture hand2Tex = new Texture(
				Gdx.files.internal("data/textures/hands/hand_left.png"));
		hand2Tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture hand3Tex = new Texture(
				Gdx.files.internal("data/textures/hands/hand_right.png"));
		hand3Tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture hand4Tex = new Texture(
				Gdx.files.internal("data/textures/hands/hand_out.png"));
		hand4Tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture doorTex1 = new Texture(
				Gdx.files.internal("data/textures/door/door_front.png"));
		doorTex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture doorTex2 = new Texture(
				Gdx.files.internal("data/textures/door/door_left.png"));
		doorTex2.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture doorTex3 = new Texture(
				Gdx.files.internal("data/textures/door/door_right.png"));
		doorTex3.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture wallFrontTex1 = new Texture(
				Gdx.files
						.internal("data/textures/walls/stone_wall_1_front.png"));
		wallFrontTex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture wallFrontTex2 = new Texture(
				Gdx.files
						.internal("data/textures/walls/stone_wall_2_front.png"));
		wallFrontTex2.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture wallFrontTex3 = new Texture(
				Gdx.files
						.internal("data/textures/walls/stone_wall_3_fornt.png"));
		wallFrontTex3.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture wallFrontTex4 = new Texture(
				Gdx.files
						.internal("data/textures/walls/stone_wall_4_front.png"));
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
				Gdx.files
						.internal("data/textures/walls/stone_wall_1_right.png"));

		wallRightTex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture wallRightTex2 = new Texture(
				Gdx.files
						.internal("data/textures/walls/stone_wall_2_right.png"));
		wallRightTex2.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture wallRightTex3 = new Texture(
				Gdx.files
						.internal("data/textures/walls/stone_wall_3_right.png"));
		wallRightTex3.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture wallRightTex4 = new Texture(
				Gdx.files
						.internal("data/textures/walls/stone_wall_4_right.png"));
		wallRightTex4.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion region1 = new TextureRegion(wallFrontTex1, 0, 0, 800, 600);
		TextureRegion region2 = new TextureRegion(wallLeftTex1, 0, 0, 800, 600);
		TextureRegion region3 = new TextureRegion(wallRightTex1, 0, 0, 800, 600);

		wallFront = new Sprite[4];
		wallLeft = new Sprite[4];
		wallRight = new Sprite[4];
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 1:
				region1 = new TextureRegion(wallFrontTex2, 0, 0, 800, 600);
				region2 = new TextureRegion(wallLeftTex2, 0, 0, 800, 600);
				region3 = new TextureRegion(wallRightTex2, 0, 0, 800, 600);
				break;
			case 2:
				region1 = new TextureRegion(wallFrontTex3, 0, 0, 800, 600);
				region2 = new TextureRegion(wallLeftTex3, 0, 0, 800, 600);
				region3 = new TextureRegion(wallRightTex3, 0, 0, 800, 600);
				break;
			case 3:
				region1 = new TextureRegion(wallFrontTex4, 0, 0, 800, 600);
				region2 = new TextureRegion(wallLeftTex4, 0, 0, 800, 600);
				region3 = new TextureRegion(wallRightTex4, 0, 0, 800, 600);
				break;
			default:
				break;
			}
			wallFront[i] = new Sprite(region1);
			wallFront[i].setSize(1,
					1 * wallFront[i].getHeight() / wallFront[i].getWidth());
			wallFront[i].setOrigin(0.5f, 0.5f);
			wallFront[i].setPosition(-wallFront[i].getWidth() / 2.0f,
					-wallFront[i].getHeight() / 2.0f);
			wallLeft[i] = new Sprite(region2);
			wallLeft[i].setSize(1,
					1 * wallLeft[i].getHeight() / wallLeft[i].getWidth());
			wallLeft[i].setOrigin(0.5f, 0.5f);
			wallLeft[i].setPosition(-wallLeft[i].getWidth() / 2.0f,
					-wallLeft[i].getHeight() / 2.0f);
			wallRight[i] = new Sprite(region3);
			wallRight[i].setSize(1,
					1 * wallRight[i].getHeight() / wallRight[i].getWidth());
			wallRight[i].setOrigin(0.5f, 0.5f);
			wallRight[i].setPosition(-wallRight[i].getWidth() / 2.0f,
					-wallRight[i].getHeight() / 2.0f);
		}
		
		region1 = new TextureRegion(winTex0, 0, 0, 512, 512);
		win0 = new Sprite(region1);
		win0.setSize(1.0f, 1.0f * win0.getHeight() / win0.getWidth());
		win0.setOrigin(0.5f, 0.5f);
		win0.setPosition(-win0.getWidth() / 2.0f,
				-win0.getHeight() / 2.0f);
		
		region1 = new TextureRegion(winTex1, 0, 0, 512, 512);
		win1 = new Sprite(region1);
		win1.setSize(1.0f, 1.0f * win0.getHeight() / win0.getWidth());
		win1.setOrigin(0.5f, 0.5f);
		win1.setPosition(-win0.getWidth() / 2.0f,
				-win0.getHeight() / 2.0f);
		
		region1 = new TextureRegion(winTex2, 0, 0, 512, 512);
		win2 = new Sprite(region1);
		win2.setSize(1.0f, 1.0f * win0.getHeight() / win0.getWidth());
		win2.setOrigin(0.5f, 0.5f);
		win2.setPosition(-win0.getWidth() / 2.0f,
				-win0.getHeight() / 2.0f);
		
		region1 = new TextureRegion(winTex3, 0, 0, 512, 512);
		win3 = new Sprite(region1);
		win3.setSize(1.0f, 1.0f * win0.getHeight() / win0.getWidth());
		win3.setOrigin(0.5f, 0.5f);
		win3.setPosition(-win0.getWidth() / 2.0f,
				-win0.getHeight() / 2.0f);
		
		region1 = new TextureRegion(winTex4, 0, 0, 512, 512);
		win4 = new Sprite(region1);
		win4.setSize(1.0f, 1.0f * win0.getHeight() / win0.getWidth());
		win4.setOrigin(0.5f, 0.5f);
		win4.setPosition(-win0.getWidth() / 2.0f,
				-win0.getHeight() / 2.0f);
		
		region1 = new TextureRegion(winTex5, 0, 0, 512, 512);
		win5 = new Sprite(region1);
		win5.setSize(1.0f, 1.0f * win0.getHeight() / win0.getWidth());
		win5.setOrigin(0.5f, 0.5f);
		win5.setPosition(-win0.getWidth() / 2.0f,
				-win0.getHeight() / 2.0f);
		
		region1 = new TextureRegion(winTex6, 0, 0, 512, 512);
		win6 = new Sprite(region1);
		win6.setSize(1.0f, 1.0f * win0.getHeight() / win0.getWidth());
		win6.setOrigin(0.5f, 0.5f);
		win6.setPosition(-win0.getWidth() / 2.0f,
				-win0.getHeight() / 2.0f);
		
		region1 = new TextureRegion(winTex7, 0, 0, 512, 512);
		win7 = new Sprite(region1);
		win7.setSize(1.0f, 1.0f * win0.getHeight() / win0.getWidth());
		win7.setOrigin(0.5f, 0.5f);
		win7.setPosition(-win0.getWidth() / 2.0f,
				-win0.getHeight() / 2.0f);
		
		region1 = new TextureRegion(heartDoor1, 0, 0, 800, 600);
		heartDoorLeft = new Sprite(region1);
		heartDoorLeft.setSize(1.0f, 1.0f * heartDoorLeft.getHeight() / heartDoorLeft.getWidth());
		heartDoorLeft.setOrigin(0.5f, 0.5f);
		heartDoorLeft.setPosition(-heartDoorLeft.getWidth() / 2.0f,
				-heartDoorLeft.getHeight() / 2.0f);
		
		region1 = new TextureRegion(heartDoor2, 0, 0, 800, 600);
		heartDoorRight = new Sprite(region1);
		heartDoorRight.setSize(1.0f, 1.0f * heartDoorRight.getHeight() / heartDoorRight.getWidth());
		heartDoorRight.setOrigin(0.5f, 0.5f);
		heartDoorRight.setPosition(-heartDoorRight.getWidth() / 2.0f,
				-heartDoorRight.getHeight() / 2.0f);
		
		region1 = new TextureRegion(heartDoor3, 0, 0, 800, 600);
		heartDoorFront = new Sprite(region1);
		heartDoorFront.setSize(1.0f, 1.0f * heartDoorFront.getHeight() / heartDoorFront.getWidth());
		heartDoorFront.setOrigin(0.5f, 0.5f);
		heartDoorFront.setPosition(-heartDoorFront.getWidth() / 2.0f,
				-heartDoorFront.getHeight() / 2.0f);
		
		region1 = new TextureRegion(heartTex1, 0, 0, 800, 600);
		heart1 = new Sprite(region1);
		heart1.setSize(1.0f, 1.0f * heart1.getHeight() / heart1.getWidth());
		heart1.setOrigin(0.5f, 0.5f);
		heart1.setPosition(-heart1.getWidth() / 2.0f,
				-heart1.getHeight() / 2.0f);

		region1 = new TextureRegion(heartTex2, 0, 0, 800, 600);
		heart2 = new Sprite(region1);
		heart2.setSize(1.0f, 1.0f * heart2.getHeight() / heart2.getWidth());
		heart2.setOrigin(0.5f, 0.5f);
		heart2.setPosition(-heart2.getWidth() / 2.0f,
				-heart2.getHeight() / 2.0f);

		region1 = new TextureRegion(heartTex3, 0, 0, 800, 600);
		heart3 = new Sprite(region1);
		heart3.setSize(1.0f, 1.0f * heart3.getHeight() / heart3.getWidth());
		heart3.setOrigin(0.5f, 0.5f);
		heart3.setPosition(-heart3.getWidth() / 2.0f,
				-heart3.getHeight() / 2.0f);

		region1 = new TextureRegion(catTex, 0, 0, 800, 600);
		catSprite = new Sprite(region1);
		catSprite.setSize(1.0f,
				1.0f * catSprite.getHeight() / catSprite.getWidth());
		catSprite.setOrigin(0.5f, 0.5f);
		catSprite.setPosition(-catSprite.getWidth() / 2.0f,
				-catSprite.getHeight() / 2.0f + 0.2f);

		region1 = new TextureRegion(hand1Tex, 0, 0, 800, 600);
		handFront = new Sprite(region1);
		handFront
				.setSize(1f, 1f * handFront.getHeight() / handFront.getWidth());
		handFront.setOrigin(0.5f, 0.5f);
		handFront.setPosition(-handFront.getWidth() / 2.0f,
				-handFront.getHeight() / 2.0f);

		region1 = new TextureRegion(hand2Tex, 0, 0, 800, 600);
		handLeft = new Sprite(region1);
		handLeft.setSize(1f, 1f * handLeft.getHeight() / handLeft.getWidth());
		handLeft.setOrigin(0.5f, 0.5f);
		handLeft.setPosition(-handLeft.getWidth() / 2.0f,
				-handLeft.getHeight() / 2.0f);

		region1 = new TextureRegion(hand3Tex, 0, 0, 800, 600);
		handRight = new Sprite(region1);
		handRight
				.setSize(1f, 1f * handRight.getHeight() / handRight.getWidth());
		handRight.setOrigin(0.5f, 0.5f);
		handRight.setPosition(-handRight.getWidth() / 2.0f,
				-handRight.getHeight() / 2.0f);

		region1 = new TextureRegion(hand4Tex, 0, 0, 800, 600);
		handOut = new Sprite(region1);
		handOut.setSize(1f, 1f * handOut.getHeight() / handOut.getWidth());
		handOut.setOrigin(0.5f, 0.5f);
		handOut.setPosition(-handOut.getWidth() / 2.0f,
				-handOut.getHeight() / 2.0f);

		region1 = new TextureRegion(doorTex1, 0, 0, 800, 600);
		doorFront = new Sprite(region1);
		doorFront
				.setSize(1f, 1f * doorFront.getHeight() / doorFront.getWidth());
		doorFront.setOrigin(0.5f, 0.5f);
		doorFront.setPosition(-doorFront.getWidth() / 2.0f,
				-doorFront.getHeight() / 2.0f);

		region1 = new TextureRegion(doorTex2, 0, 0, 800, 600);
		doorLeft = new Sprite(region1);
		doorLeft.setSize(1f, 1f * doorLeft.getHeight() / doorLeft.getWidth());
		doorLeft.setOrigin(0.5f, 0.5f);
		doorLeft.setPosition(-doorLeft.getWidth() / 2.0f,
				-doorLeft.getHeight() / 2.0f);

		region1 = new TextureRegion(doorTex3, 0, 0, 800, 600);
		doorRight = new Sprite(region1);
		doorRight
				.setSize(1f, 1f * doorRight.getHeight() / doorRight.getWidth());
		doorRight.setOrigin(0.5f, 0.5f);
		doorRight.setPosition(-doorRight.getWidth() / 2.0f,
				-doorRight.getHeight() / 2.0f);

		/*
		 * for (int i = 0; i < 10; i++) { test[i] = 1; } for (int i = 10; i <
		 * 90; i++) { if (i % lWidth == 9 || i % lHeight == 0){ if(i==49){
		 * test[i] = 0; } else{ test[i] = 1; } } else test[i] = 0; } for (int i
		 * = 90; i < 100; i++) { test[i] = 1; }
		 */

		for (int i = 0; i < test.length; i++) {
			if (test[i] == 1) {
				textureArray[i] = test[i];
			} else {
				textureArray[i] = 0;
				if (test[i] == 8) {
					heartPosition = i;
				}
				if (test[i] == 9) {
					endDoorPosition = i;
				}
			}
		}

		nickCage = new Player(15, lWidth);
		testlevel = new Level(lWidth, lHeight, test);

		cats = testlevel.setCats();
		Random randGen = new Random();
		int randNum = 0;
		for (int i = 0; i < textureArray.length; i++) {
			if (textureArray[i] == 1) {
				randNum = randGen.nextInt(4) + 1;
				textureArray[i] = randNum;
			}
		}

		nickCage.setDirection(Direction.West);

		testlevel = new Level(lWidth, lHeight, test);

		testDraw();

	}

	public void update() {
		if (!testlevel.endGame) {
			nickCage.update();
			if (nickCage.changedPosition) {
				nickCage.changedPosition = false;
				if (cats.length > 0) {
					for (int c = 0; c < cats.length; c++) {
//						System.out.println("len: " + cats.length + " pos: "
//								+ cats[c].getPosition());
						cats[c].move();
					}
				}
				testlevel.checkPlayer(nickCage, heartPosition, endDoorPosition);
				testDraw();
			} else if (nickCage.changedDirection) {
				if (!nickCage.isAtDoor && testlevel.doorPos > 0) {
					testlevel.mapArray[testlevel.doorPos] = 4;
					testlevel.doorPos = -1;
				}
				nickCage.changedDirection = false;
				/*
				 * if (cats.length > 0) { for (int c = 0; c < cats.length; c++)
				 * { System.out.println("len: " + cats.length + " pos: " +
				 * cats[c].getPosition()); cats[c].move(); } }
				 */
				testDraw();
			}
			if(!nickCage.hasHeart){
				heartbeat.setVolume(nickCage.getVolume());
				beat.setVolume(beat_id, nickCage.getVolume());
			}
			else{
				heartbeat.setVolume(0.0f);
				beat.setVolume(beat_id, 0.0f);	
			}
		}
	}

	public void dispose() {

	}

	public void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if (!testlevel.endGame) {
			int left = 0;
			int right = 0;
			int front = 0;
			boolean leftWall = false;
			boolean rightWall = false;
			boolean frontWall = false;
			switch (nickCage.getDirection()) {
			case North:
				left = testlevel.mapArray[nickCage.getPosition() - 1];
				right = testlevel.mapArray[nickCage.getPosition() + 1];
				front = testlevel.mapArray[nickCage.getPosition()
						- testlevel.getWidth()];
				break;
			case South:
				left = testlevel.mapArray[nickCage.getPosition() + 1];
				right = testlevel.mapArray[nickCage.getPosition() - 1];
				front = testlevel.mapArray[nickCage.getPosition()
						+ testlevel.getWidth()];
				break;
			case East:
				left = testlevel.mapArray[nickCage.getPosition()
						- testlevel.getWidth()];
				right = testlevel.mapArray[nickCage.getPosition()
						+ testlevel.getWidth()];
				front = testlevel.mapArray[nickCage.getPosition() + 1];
				break;
			case West:
				left = testlevel.mapArray[nickCage.getPosition()
						+ testlevel.getWidth()];
				right = testlevel.mapArray[nickCage.getPosition()
						- testlevel.getWidth()];
				front = testlevel.mapArray[nickCage.getPosition() - 1];
				break;
			default:
				break;
			}

			switch (front) {
			case 4:
				frontWall = true;
				doorFront.draw(batch);
				break;
			case 5:
				doorRight.draw(batch);
				rightWall = true;
				break;
			case 9:
				frontWall = true;
				heartDoorFront.setColor(Color.RED);
				heartDoorFront.draw(batch);
				break;
			default:
				break;
			}
			switch (left) {
			case 4:
				leftWall = true;
				doorLeft.draw(batch);
				break;
			case 9:
				leftWall = true;
				heartDoorLeft.setColor(Color.RED);
				heartDoorLeft.draw(batch);
				break;
			default:
				break;
			}
			switch (right) {
			case 4:
				rightWall = true;
				doorRight.draw(batch);
				break;
			case 9:
				rightWall = true;
				heartDoorRight.setColor(Color.RED);
				heartDoorRight.draw(batch);
				break;
			default:
				break;
			}
			left = 0;
			right = 0;
			front = 0;
			switch (nickCage.getDirection()) {
			case North:
				left = textureArray[nickCage.getPosition() - 1];
				right = textureArray[nickCage.getPosition() + 1];
				front = textureArray[nickCage.getPosition()
						- testlevel.getWidth()];
				break;
			case South:
				left = textureArray[nickCage.getPosition() + 1];
				right = textureArray[nickCage.getPosition() - 1];
				front = textureArray[nickCage.getPosition()
						+ testlevel.getWidth()];
				break;
			case East:
				left = textureArray[nickCage.getPosition()
						- testlevel.getWidth()];
				right = textureArray[nickCage.getPosition()
						+ testlevel.getWidth()];
				front = textureArray[nickCage.getPosition() + 1];
				break;
			case West:
				left = textureArray[nickCage.getPosition()
						+ testlevel.getWidth()];
				right = textureArray[nickCage.getPosition()
						- testlevel.getWidth()];
				front = textureArray[nickCage.getPosition() - 1];
				break;
			default:
				break;
			}
			// render left wall if its there
			switch (left) {
			case 1:
				wallLeft[0].draw(batch);
				leftWall = true;
				break;
			case 2:
				wallLeft[1].draw(batch);
				leftWall = true;
				break;
			case 3:
				wallLeft[2].draw(batch);
				leftWall = true;
				break;
			case 4:
				wallLeft[3].draw(batch);
				leftWall = true;
				break;
			default:
				break;
			}
			// render right wall if its there
			switch (right) {
			case 1:
				wallRight[0].draw(batch);
				rightWall = true;
				break;
			case 2:
				wallRight[1].draw(batch);
				rightWall = true;
				break;
			case 3:
				wallRight[2].draw(batch);
				rightWall = true;
				break;
			case 4:
				wallRight[3].draw(batch);
				rightWall = true;
				break;
			default:
				break;
			}
			// render front wall if its there
			switch (front) {
			case 1:
				wallFront[0].draw(batch);
				frontWall = true;
				break;
			case 2:
				wallFront[1].draw(batch);
				frontWall = true;
				break;
			case 3:
				wallFront[2].draw(batch);
				frontWall = true;
				break;
			case 4:
				wallFront[3].draw(batch);
				frontWall = true;
				break;
			default:
				break;
			}
			if (frontWall) {
				handFront.draw(batch);
			} else if (rightWall) {
				handRight.draw(batch);
			} else if (leftWall) {
				handLeft.draw(batch);
			} else {
				handOut.draw(batch);
			}

			if (nickCage.hasHeart) {
				switch(nickCage.getFear()){
				case 1:
					frameDivisor = 5;
					break;
				case 2:
					frameDivisor = 6;
					break;
				case 3:
					frameDivisor = 7;
					break;
				case 4:
					frameDivisor = 10;
					break;
				case 5:
					frameDivisor = 11;
					break;
				default:
					System.out.println("Broken--fear: " + nickCage.getFear());
					break;
				}
				int frame = frameCounter / frameDivisor;
				switch (frame) {
				case 0:
					heart1.draw(batch);
					if(!didBeat){
						beat_up.play();
						didBeat = true;
					}
					break;
				case 1:
					heart2.draw(batch);
					didBeat = false;
					break;
				case 2:
					heart3.draw(batch);
					if(!didBeat){
						beat_down.play();
						didBeat = true;
					}
					break;
				case 3:
					heart2.draw(batch);
					didBeat = false;
					break;
				default:
					break;
				}
				frameCounter++;
				if (frameCounter >= (frameDivisor*4)) {
					frameCounter = 0;
				}
			}

			if (testlevel.hitCat && catCounter == 0) {
				catCounter = 30;
				testlevel.hitCat = false;
			}
			if (catCounter != 0) {
				catSprite.draw(batch);
				catCounter--;
			}
		} else {
			if(testlevel.endGameAnime < maxAnimeTime){
				/*wallLeft[0].rotate(testlevel.endGameAnime);
				wallLeft[0].draw(batch);
				doorRight.rotate(testlevel.endGameAnime);
				doorRight.draw(batch);
				handOut.rotate(testlevel.endGameAnime);
				handOut.draw(batch);*/
				int frame = frameCounter / 30;
				switch (frame) {
				case 0:
					win0.draw(batch);
					break;
				case 1:
					win1.draw(batch);
					break;
				case 2:
					win2.draw(batch);
					break;
				case 3:
					win3.draw(batch);
					break;
				case 4:
					win4.draw(batch);
					break;
				case 5:
					win5.draw(batch);
					break;
				case 6:
					win6.draw(batch);
					break;
				default:
					win7.draw(batch);
					break;
				}
				frameCounter++;
				if (frameCounter == 270) {
					switchScreen = true;
				}				
			} 
		}
		batch.end();

	}

	public void testDraw() {
//		int p = 0;
//		// Create file
//		BufferedWriter out = null;
//		try {
//			FileWriter fstream = new FileWriter("out.txt");
//			out = new BufferedWriter(fstream);
//		} catch (Exception e) {// Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}
//
//		for (int i = 0; i < lHeight; i++) {
//			for (int j = 0; j < lWidth; j++) {
//				try{
//					out.write(test[p] + " ");
//				} catch (Exception e) {// Catch exception if any
//					System.err.print("Error in stream ");
//				}
//				
//				//System.out.print(test[p]);
//				//System.out.print(" ");
//				p++;
//			}
//			try{
//			out.write("\n");
//			} catch (Exception e) {// Catch exception if any
//				System.err.print("Error in stream ");
//			}
////			System.out.print("\n");
//		}
//		try {
//			// Close the output stream
//			out.close();
//		} catch (Exception e) {// Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}
////		System.out.println("Position: " + nickCage.getPosition());
////		System.out.println("Facing: " + nickCage.getDirection());
	}

	public void stopSound(){
		heartbeat.stop();
		beat.stop();
		beat_up.stop();
		beat_down.stop();
	}
}
