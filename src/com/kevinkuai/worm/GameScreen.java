package com.kevinkuai.worm;

import java.util.List;

import android.graphics.Color;
import android.util.Log;

import com.kevinkuai.framework.Game;
import com.kevinkuai.framework.Graphics;
import com.kevinkuai.framework.Input.TouchEvent;
import com.kevinkuai.framework.Pixmap;
import com.kevinkuai.framework.Screen;

public class GameScreen extends Screen {
	
	enum GameState {
		Ready,
		Running,
		Paused,
		GameOver
	}
	
	GameState state = GameState.Ready;
	World world;
	int oldScore = 0;
	String score = "0";

	public GameScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		world = new World();
		
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		List<TouchEvent> touch = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		if (state == GameState.Ready)
			updateReady(touch);
		if (state == GameState.Running)
			updateRunning(touch, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touch);
		if (state == GameState.GameOver)
			updateGameOver(touch);
	}
	
	private void updateGameOver(List<TouchEvent> touch) {
		// TODO Auto-generated method stub
		int len = touch.size();
		for (int i = 0; i<len; i++){
			TouchEvent te = touch.get(i);
			if (te.type == TouchEvent.TOUCH_UP){
				if (te.x > 128 && te.x <= 192){
					if (te.y > 200 && te.y <= 264){
						if (Settings.soundEnabled)
							Assets.click.play(1);
						game.setScreen(new MainMenuScreen(game));
						return;
					}
				}
			}
		}
		
	}

	private void updatePaused(List<TouchEvent> touch) {
		// TODO Auto-generated method stub
		int len = touch.size();
		for (int i = 0; i<len; i++){
			TouchEvent te = touch.get(i);
			if (te.type == TouchEvent.TOUCH_UP){
				if (te.x > 80 && te.x <= 240){
					if (te.y > 100 && te.y <= 148){
						if (Settings.soundEnabled)
							Assets.click.play(1);
						state = GameState.Running;
						return;
					}
					if (te.y > 148 && te.y <196){
						if (Settings.soundEnabled)
							Assets.click.play(1);
						game.setScreen(new MainMenuScreen(game));
					}
				}
			}
		}
	}

	private void updateRunning(List<TouchEvent> touch, float deltaTime) {
		// TODO Auto-generated method stub
		int len = touch.size();
		for (int i=0; i<len; i++){
			TouchEvent te = touch.get(i);
			if (te.type == TouchEvent.TOUCH_UP){
				if (te.x < 64 && te.y <64){
					if (Settings.soundEnabled)
						Assets.click.play(1);
					state = GameState.Paused;
					return;
				}
			}
			
			if (te.type == TouchEvent.TOUCH_DOWN){
				if (te.x < 64 && te.y >416){
					world.snake.turnLeft();
				}
				if(te.x>256 && te.y >416){
					world.snake.turnRight();
				}
			}
		}
		
		world.update(deltaTime);
		if (world.gameOver){
			if (Settings.soundEnabled)
				Assets.bitten.play(1);
			state = GameState.GameOver;
		}
		if (oldScore != world.score){
			oldScore = world.score;
			score = " " + oldScore;
			if (Settings.soundEnabled)
				Assets.eat.play(1);
				
		}
		
		
	}

	private void updateReady(List<TouchEvent> touch){
		int len = touch.size();
		Log.d("AndroidGame", "Te size is "+ len);
		for (int i = 0; i<len; i++){
			TouchEvent te = touch.get(i);
			if (te.type == TouchEvent.TOUCH_DOWN)
			state = GameState.Running;
			}
		/*if (len >0){
			Log.d("AndroidGame", "type is "+ touch.get(0).type);
			state = GameState.Running;
		}*/
		
	}
	
	

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.background, 0, 0);
		drawWorld();
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();
		
		drawText(g, score, g.getWidth()/2-score.length()*20/2, g.getHeight()-42);
	}

	private void drawGameOverUI() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.gameOver, 62, 100);
		g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
		
	}

	private void drawPausedUI() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.pause, 80, 100);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
		
		
	}

	private void drawRunningUI() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.buttons, 0,0, 64, 128, 64,64);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
		g.drawPixmap(Assets.buttons, 0,416, 64, 64, 64,64);
		g.drawPixmap(Assets.buttons, 256,416, 0, 64, 64,64);
		
	}

	private void drawReadyUI() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.ready, 47, 100);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
		
	}

	private void drawWorld() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		Snake snake = world.snake;
		SnakePart head = snake.parts.get(0);
		Stain stain = world.stain;
		
		Pixmap stainPixmap = null;
		if (stain.type == Stain.TYPE_1)
			stainPixmap = Assets.stain1;
		if (stain.type == Stain.TYPE_2)
			stainPixmap = Assets.stain2;
		if (stain.type == Stain.TYPE_3)
			stainPixmap = Assets.stain3;
		int x, y;
		x = stain.x * 32;
		y = stain.y * 32;
		g.drawPixmap(stainPixmap, x, y);
		
		int len = snake.parts.size();
		for (int i = 1; i < len; i ++){
			SnakePart part = snake.parts.get(i);
			x = part.x * 32;
			y = part.y * 32;
			g.drawPixmap(Assets.tail, x, y);
		}
		
		Pixmap headmap = null;
		if (snake.direction == Snake.UP)
			headmap = Assets.headUp;
		if (snake.direction == Snake.LEFT)
			headmap = Assets.headLeft;
		if (snake.direction == Snake.DOWN)
			headmap = Assets.headDown;
		if (snake.direction == Snake.RIGHT)
			headmap = Assets.headRight;
		x = head.x*32+16;
		y = head.y*32+16;
		g.drawPixmap(headmap, x - headmap.getWidth()/2, 
				y - headmap.getHeight()/2);
		
	}
	
	private void drawText(Graphics g, String line, int x, int y) {
		// TODO Auto-generated method stub
		int len = line.length();
		for (int i =0; i<len; i++){
			char c = line.charAt(i);
			
			if (c == ' ') {
				x += 20;
				continue;
			}
			
			int srcX = 0;
			int srcWidth = 0;
			
			if (c == '.') {
				srcX = 200;
				srcWidth = 10;
			}else{
				srcX = (c - '0')*20;
				srcWidth = 20;
			}
			g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
			x += srcWidth;
		}
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if (state == GameState.Running)
			state = GameState.Paused;
		
		if(world.gameOver){
			Settings.addScore(world.score);
			Settings.save(game.getFileIO());
		}

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
