package com.me.darkbeat;

public class Enemies{
	
	protected int position;
	
	public Enemies( int newPosition){
		position = newPosition;
	}
	
	public int getPosition(){
		return position;
	}
	
	public void setPosition(int newPosition){
		position = newPosition;
	}
}