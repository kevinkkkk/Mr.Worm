package com.kevinkuai.worm;

import java.util.ArrayList;
import java.util.List;

public class Snake {
	
	public static final int UP =0;
	public static final int LEFT =1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	
	public List<SnakePart> parts = new ArrayList<SnakePart>();
	public int direction;
	
	public Snake(){
		
		direction =0;
		parts.add(new SnakePart(5,6));
		parts.add(new SnakePart(5,7));
		
	}
	
	public void turnLeft(){
		direction += 1;
		if (direction>RIGHT)
			direction = UP;	
	}
	
	public void turnRight(){
		direction -= 1;
		if (direction < UP)
			direction = RIGHT;
	}
	
	public void advance(){
		SnakePart head = parts.get(0);
		
		int len = parts.size();
		for (int i = len-1; i>0; i--){
			SnakePart after = parts.get(i);
			SnakePart before = parts.get(i-1);
			after.x = before.x;
			after.y = before.y;
		}
		
		if (direction == UP)
			head.y -= 1;
		if (direction == LEFT)
			head.x -= 1;
		if (direction == DOWN)
			head.y += 1;
		if (direction == RIGHT)
			head.x += 1;
		
		if (head.x <0)
			head.x = 9;
		if (head.x >9)
			head.x = 0;
		if (head.y<0)
			head.y =12;
		if (head.y > 12)
			head.y = 0;
			
	}
	
	public void eat(){
		SnakePart end = parts.get(parts.size()-1);
		parts.add(new SnakePart(end.x, end.y));
	}

	public boolean checkBitten(){
		
		int len = parts.size();
		for (int i=1; i<len; i++){
			if (parts.get(0).x == parts.get(i).x && parts.get(0).y == parts.get(i).y)
				return true;
			
		}
		return false;
	}
}
