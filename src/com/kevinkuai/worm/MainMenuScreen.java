package com.kevinkuai.worm;

import java.util.List;

import android.util.Log;

import com.kevinkuai.framework.Game;
import com.kevinkuai.framework.Graphics;
import com.kevinkuai.framework.Input.TouchEvent;
import com.kevinkuai.framework.Screen;

public class MainMenuScreen extends Screen{

	public MainMenuScreen(Game game) {
		
		super(game);
		// TODO Auto-generated constructor stub
		Log.d("AndroidGame log", "MainMenuScreen stated");
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		List<TouchEvent> touch = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touch.size();
		for (int i = 0; i<len; i++){
			TouchEvent te = touch.get(i);
			if (inBound(te, 0, g.getHeight()-64, 64,64)){
				Settings.soundEnabled = !Settings.soundEnabled;
				if (Settings.soundEnabled)
					Assets.click.play(1);
			}
			if (inBound(te, 64, 220, 192, 42)){
				game.setScreen(new GameScreen(game));
				if (Settings.soundEnabled)
					Assets.click.play(1);
				return;
			}
			if (inBound(te, 64, 220+42, 192, 42)){
				game.setScreen(new HighScoreScreen(game));
				if (Settings.soundEnabled)
					Assets.click.play(1);
				return;
			}
			if (inBound(te, 64, 220+84, 192, 42)){
				game.setScreen(new HelpScreen(game));
				if (Settings.soundEnabled)
					Assets.click.play(1);
				return;
			}
		}
		
	}

	private boolean inBound(TouchEvent te, int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		if (te.x > i && te.x < i+k-1 && te.y > j && te.y < j+l-1)
			return true;
		else
		  return false;
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.logo, 32, 20);
		g.drawPixmap(Assets.mainMenu, 64, 220);
		if (Settings.soundEnabled)
			g.drawPixmap(Assets.buttons, 0,416,0, 0,64,64);
		else
			g.drawPixmap(Assets.buttons, 0,416,64, 0,64,64);
		
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Settings.save(game.getFileIO());
		
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
