package com.me.darkbeat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Level {

	public int mapArray[];
	private int height;
	private int width;
	private double maxDistance;
	private Sound footSteps;
	private Sound openDoor;
	private Sound catMEOW;
	public int doorPos;
	public boolean hitCat = false;
	public boolean endGame = false;
	public int endGameAnime = -1;

	public Level(int levelWidth, int levelHeight, int level[]) {
		mapArray = level;
		height = levelHeight;
		width = levelWidth;
		footSteps = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/footsteps.ogg"));
		openDoor = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/openDoor.ogg"));
		catMEOW = Gdx.audio
				.newSound(Gdx.files.internal("data/sounds/Meow.ogg"));
		doorPos = -1;

		double temp = (levelHeight * levelHeight) + (levelWidth * levelWidth);
		maxDistance = Math.sqrt(temp);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void switchEnemy(int newIndex, int oldIndex, int category) {
		mapArray[oldIndex] = 0;
		mapArray[newIndex] = category;
	}

	public Cat[] setCats() {
		int count = 0;
		for (int i = 0; i < (width * height); i++) {
			if (mapArray[i] == 3) {
				count++;
			}
		}
		Cat[] catList = new Cat[count];
		int catNumber = 0;
		for (int i = 0; i < (width * height); i++) {
			if (mapArray[i] == 3) {
				catList[catNumber] = new Cat(i, this);
				catNumber++;
			}
		}
		return catList;
	}

	/*
	 * Types 0: empty 1: wall 2: player 3: cat 4: closed door 5: open door 6: 7:
	 * 8: Heart 9: End goal
	 */

	public void checkPlayer(Player player, int heart, int endDoor) {
		long id;
		if (!player.isAtDoor && doorPos > 0
				&& mapArray[player.getPosition()] != 5) {
			mapArray[doorPos] = 4;
			doorPos = -1;
		}
		if (mapArray[player.getPosition()] == 0
				|| mapArray[player.getPosition()] == 5
				|| mapArray[player.getPosition()] == 3) {
			if (mapArray[player.getPosition()] == 5) {
				switch (player.getDirection()) {
				case North:
					mapArray[player.getPosition()] = 4;
					mapArray[player.oldPos] = 0;
					player.setPosition(player.getPosition() - width);
					break;
				case South:
					mapArray[player.oldPos] = 0;
					mapArray[player.getPosition()] = 4;
					player.setPosition(player.getPosition() + width);
					break;
				case East:
					mapArray[player.oldPos] = 0;
					mapArray[player.getPosition()] = 4;
					player.setPosition(player.getPosition() + 1);
					break;
				case West:
					mapArray[player.oldPos] = 0;
					mapArray[player.getPosition()] = 4;
					player.setPosition(player.getPosition() - 1);
					break;
				}
			} else if (mapArray[player.getPosition()] == 3) {
				catMEOW.play(0.25f);
				mapArray[player.getOldPosition()] = 0;
				hitCat = true;
			} else {
				mapArray[player.getOldPosition()] = 0;
				mapArray[player.getPosition()] = 2;
				id = footSteps.play(1.0f);
				footSteps.setVolume(id, 0.015f);
			}
		} else {
			switch (mapArray[player.getPosition()]) {
			case 1:
				break;
			case 2:
				break;
			case 4:
				mapArray[player.getPosition()] = 5;
				openDoor.play(0.025f);
				doorPos = player.getPosition();
				player.isAtDoor = true;
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				mapArray[player.getPosition()] = 0;
				player.hasHeart = true;
				player.setVolume(1.0f);
				break;
			case 9:
				if(player.hasHeart){
					openDoor.play(0.045f);
					endGame = true;
					endGameAnime = 0;
				}
				break;
			default:
				break;
			}
			if(!endGame){
				player.setPosition(player.getOldPosition());
			}
		}
		if (!player.hasHeart) {
			int heartOffset_x = (player.getPosition() % width)
					- (heart % width);
			int heartOffset_y = (player.getPosition() / width)
					- (heart / width);
			double distance = Math.sqrt(Math.pow(heartOffset_x, 2)
					+ Math.pow(heartOffset_y, 2));
			if (distance > maxDistance / 2.0) {
				player.setVolume(0.0f);
			} else {
				distance = distance / maxDistance;
				distance = 1.0 - distance;
				distance = Math.pow(distance, 8);
				player.setVolume((float) distance);
							}
		} else{
			player.setVolume(1.0f);
			int endDoorOffset_x = (player.getPosition() % width)
					- (endDoor % width);
			int endDoorOffset_y = (player.getPosition() / width)
					- (endDoor / width);
			double distance = Math.sqrt(Math.pow(endDoorOffset_x, 2)
					+ Math.pow(endDoorOffset_y, 2));
			distance = distance / maxDistance;
			distance = 1.0 - distance;
			distance = Math.pow(distance, 3);
			player.setFear(bobBarker(distance, player));
		}
	}
	
	public int bobBarker(double input, Player player){
		int pos= player.getPosition();
		/*if ((pos / 14) < 4 && (pos % 14) < 3) player.setVolume(0.025f);
		else if ((pos / 14) < 4 && (pos % 14) < 6) player.setVolume(0.1f);
		else if ((pos / 14) < 4 ) player.setVolume(0.2f);
		else if((pos / 14) < 7 && (pos % 14) > 7) player.setVolume(0.4f);
		else player.setVolume(1.0f);*/
		if(input < .2) return 5;
		else if(input < .4) return 4;
		else if(input < .6) return 3;
		else if(input < .8) return 2;
		else return 1;
	}

}