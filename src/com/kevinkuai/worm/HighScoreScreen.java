package com.kevinkuai.worm;

import java.util.List;

import com.kevinkuai.framework.Game;
import com.kevinkuai.framework.Graphics;
import com.kevinkuai.framework.Screen;
import com.kevinkuai.framework.Input.TouchEvent;

public class HighScoreScreen extends Screen {
	
	String lines[] = new String[5];

	public HighScoreScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		for (int i=0; i<5; i++){
			lines[i]=" "+(i+1)+". "+Settings.highscores[i];
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		List <TouchEvent> touch = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touch.size();
		for (int i=0; i<len; i++){
			TouchEvent te = touch.get(i);
			if (te.type == TouchEvent.TOUCH_UP){
				if (te.x <64 && te.y >416){
					game.setScreen(new MainMenuScreen(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
			}
		}

	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.mainMenu, 64, 20,0,42,196,42);
		
		int y = 100;
		for (int i = 0; i<5; i++){
			drawText(g, lines[i], 20, y);
			y+=50;
		}
		g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);

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
