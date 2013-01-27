package com.me.darkbeat;

import java.util.Random;

public class Cat extends Enemies{
	
	private Random randGen;
	private Level level;
	private int oldPos;
	
	public Cat(int pos, Level l){
		super(pos);
		level = l;
		oldPos = pos;
		randGen = new Random();
	}
	
	/*
	 * 0 : North
	 * 1 : South
	 * 2 : East
	 * 3 : West
	 */
	public void move(){
		int randomNum;
		randomNum = randGen.nextInt(4);
		oldPos = position;
		switch(randomNum){
		case 0:
			if(position - level.getWidth() > 0){
				if (level.mapArray[position-level.getWidth()] == 0
						|| level.mapArray[position-level.getWidth()] == 2){
					position = position-level.getWidth();
				}
			}
			break;
		case 1:
			if(position + level.getWidth() < level.mapArray.length){
				if (level.mapArray[position+level.getWidth()] == 0
						|| level.mapArray[position+level.getWidth()] == 2){
					position = position+level.getWidth();
				}
			}
			break;
		case 2:
			if(position + 1 < level.mapArray.length){
				if (level.mapArray[position+1] == 0
						|| level.mapArray[position+1] == 2){
					position = position+1;
				}
			}
			break;
		case 3:
			if(position - 1 > 0){
				if (level.mapArray[position-1] == 0
						|| level.mapArray[position-1] == 2){
					position = position-1;
				}
			}
			break;
		default:
			break;
		}
		level.mapArray[oldPos] = 0;
		level.mapArray[position] = 3;
	}
}