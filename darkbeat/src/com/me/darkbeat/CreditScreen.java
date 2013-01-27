package com.me.darkbeat;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CreditScreen {
	private float height = 0.0f;
	private ArrayList<Sprite> creditSprites;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	public boolean endCredits = false;
	private int pauseLast = -1;
	private int waitInc = 0;
	private boolean pause = false;
	private boolean keyRelease = true;
	private int keyCount = 0;

	public CreditScreen() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1, h / w);
		batch = new SpriteBatch();

		creditSprites = new ArrayList<Sprite>();
		Texture tex1 = new Texture(Gdx.files.internal("data/credit1.png"));
		tex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture tex2 = new Texture(Gdx.files.internal("data/credit2.png"));
		tex2.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture tex3 = new Texture(Gdx.files.internal("data/credit3.png"));
		tex3.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture tex4 = new Texture(Gdx.files.internal("data/textures/misc/game_end.png"));
		tex4.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion region1 = new TextureRegion(tex1, 0, 0, 800, 600);
		TextureRegion region2 = new TextureRegion(tex2, 0, 0, 800, 600);
		TextureRegion region3 = new TextureRegion(tex3, 0, 0, 800, 600);
		TextureRegion region4 = new TextureRegion(tex4, 0, 0, 800, 600);

		Sprite sprite = new Sprite(region1);
		sprite.setSize(1.0f, 1.0f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(0.5f, 0.5f);

		float x = -sprite.getWidth() / 2.0f;
		float y = -sprite.getHeight() - (sprite.getHeight() / 2);
		sprite.setPosition(x, y);
		creditSprites.add(sprite);

		sprite = new Sprite(region2);
		sprite.setSize(1.0f, 1.0f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(0.5f, 0.5f);
		y = y - sprite.getHeight();
		sprite.setPosition(x, y);
		creditSprites.add(sprite);

		sprite = new Sprite(region3);
		sprite.setSize(1.0f, 1.0f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(0.5f, 0.5f);
		y = y - sprite.getHeight();
		sprite.setPosition(x, y);
		creditSprites.add(sprite);

		sprite = new Sprite(region4);
		sprite.setSize(1.0f, 1.0f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(0.5f, 0.5f);
		y = y - sprite.getHeight();
		sprite.setPosition(x, y);
		creditSprites.add(sprite);
	}

	public void update() {
		if (keyRelease) {
			if (Gdx.input.isKeyPressed(Keys.SPACE)) {
				if (pause) {
					pause = false;
				} else {
					pause = true;
				}
				keyRelease = false;
			}
		} else {
			if (!Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
				if(keyCount > 7){
					keyRelease = true;
					keyCount = 0;
				}
				keyCount++;
			}
		}
		if (!pause) {
			if (waitInc == 2 && pauseLast < 0) {
				height = 0.005f;
				waitInc = 0;
			} else {
				height = 0;
			}
			if (creditSprites.get(creditSprites.size() - 1).getY() > -0.3f) {
				height = 0;
				pauseLast++;
			}
			if (pauseLast == 200) {
				endCredits = true;
			}
			waitInc++;
		} else {
			height = 0;
		}
		for (Sprite spr : creditSprites) {
			spr.setPosition(spr.getX(), spr.getY() + height);
		}
	}

	public void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Sprite spr : creditSprites) {
			spr.draw(batch);
		}

		batch.end();
	}

}
