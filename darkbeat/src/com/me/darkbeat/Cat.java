package com.me.darkbeat;

import java.util.Random;

public class Cat extends Enemies{
	
	private Random randGen;
	private Level level;
	
	public Cat(int pos, Level l){
		super(pos);
		level = l;
	}
	
	/*
	 * 0 : North
	 * 1 : South
	 * 2 : East
	 * 3 : West
	 */
	public void move(){
		int randomNum;
		boolean clearPath = false;
		while (!clearPath) {
			randomNum = randGen.nextInt(4);
			switch(randomNum){
			case 0:
				if(position - level.getWidth() > 0){
					if (level.mapArray[position-level.getWidth()] == 0){
						position = level.mapArray[position-level.getWidth()];
					}
				}
				break;
			case 1:
				if(position + level.getWidth() < level.mapArray.length){
					if (level.mapArray[position+level.getWidth()] == 0){
						position = level.mapArray[position+level.getWidth()];
					}
				}
				break;
			case 2:
				if(position + 1 < level.mapArray.length){
					if (level.mapArray[position+1] == 0){
						position = level.mapArray[position+1];
					}
				}
				break;
			case 3:
				if(position - 1 > 0){
					if (level.mapArray[position-1] == 0){
						position = level.mapArray[position-1];
					}
				}
				break;
			default:
				break;
			}
		}
	}
}