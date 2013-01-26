package com.me.darkbeat;

public class Player{
	
	private int position;
	private float fear;
	private int direction;
	
	/* direction state.
	 * 	0: up
	 *  1: down
	 *  2: left
	 *  3: right
	 */
	
	public Player(int startingPosition) {
		position = startingPosition;
		fear = 0.0f;
		direction = 0;
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
	
	public int getDirection(){
		return direction;
	}
	
	public void setDirection(int newDirection){
		direction = newDirection;
	}
}