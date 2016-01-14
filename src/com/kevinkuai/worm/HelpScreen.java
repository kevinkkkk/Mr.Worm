package com.kevinkuai.worm;

import java.util.List;

import com.kevinkuai.framework.Game;
import com.kevinkuai.framework.Graphics;
import com.kevinkuai.framework.Input.TouchEvent;
import com.kevinkuai.framework.Screen;

public class HelpScreen extends Screen {

	public HelpScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
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
				if (te.x >256 && te.y >416){
					game.setScreen(new HelpScreen2(game));
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
		g.drawPixmap(Assets.help1, 64, 100);
		g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);

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
