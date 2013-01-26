package com.me.darkbeat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Player{
	
	private int position;
	private float fear;
	private Direction dir;
	private int mapWidth;
	private int oldPos;
	public enum Direction {
		North, South, East, West;
	}
	
	private boolean keysReleased;

	
	public Player(int startingPosition, int width) {
		position = startingPosition;
		fear = 0.0f;
		dir = Direction.East;
		mapWidth = width;
	}
	
	public void update(){
		//move forward
		if (keysReleased){
			if(Gdx.input.isKeyPressed(Keys.W)) {
				switch(dir){
				case North:
					oldPos = position;
					position -= mapWidth;
					break;
				case South:
					oldPos = position;
					position += mapWidth;
					break;
				case East:
					oldPos = position;
					position += 1;
					break;
				case West:
					oldPos = position;
					position -= 1;
					break;
				default:
					break;
				}
				keysReleased = false;
			} else if (Gdx.input.isKeyPressed(Keys.A)) { //turn left
				switch(dir){
				case North:
					dir = Direction.West;
					break;
				case South:
					dir = Direction.East;
					break;
				case East:
					dir = Direction.North;
					break;
				case West:
					dir = Direction.South;
					break;
				default:
					break;
				}
				keysReleased = false;
			} else if (Gdx.input.isKeyPressed(Keys.D)) { //turn right
				switch(dir){
				case North:
					dir = Direction.East;
					break;
				case South:
					dir = Direction.West;
					break;
				case East:
					dir = Direction.South;
					break;
				case West:
					dir = Direction.North;
					break;
				default:
					break;
				}
				keysReleased = false;
			}
		}
		if (!(Gdx.input.isKeyPressed(Keys.D) || 
			  Gdx.input.isKeyPressed(Keys.W) || 
			  Gdx.input.isKeyPressed(Keys.A) )){
			keysReleased = true;
		}
	}
	
	public void collisionAction(){
		fear++;
		position = oldPos;
	}
	
	public float getFear(){
		return fear;
	}
	
	public void setFear(float newFear){
		fear = newFear;
	}
	
	public int getPosition(){
		return position;
	}
	
	public void setPosition(int newPosition){
		position = newPosition;
	}
	
	public int getOldPosition(){
		return oldPos;
	}
	
	public Direction getDirection(){
		return dir;
	}
	
	public void setDirection(Direction newDirection){
		dir = newDirection;
	}
}