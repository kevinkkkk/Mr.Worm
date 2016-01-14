package com.kevinkuai.worm;

import android.util.Log;

import com.kevinkuai.framework.Game;
import com.kevinkuai.framework.Graphics;
import com.kevinkuai.framework.Graphics.PixmapFormat;
import com.kevinkuai.framework.Screen;

public class LoadingScreen extends Screen{

	public LoadingScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
		Graphics g = game.getGraphics();
		Assets.background = g.newPixmap("background.png", PixmapFormat.ARGB4444);
		Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
		Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
		Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
		Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
		Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
		Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
		Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
		Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
		Assets.pause = g.newPixmap("pause.png", PixmapFormat.ARGB4444);
		Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
		Assets.headUp = g.newPixmap("headup.png", PixmapFormat.ARGB4444);
		Assets.headDown = g.newPixmap("headdown.png", PixmapFormat.ARGB4444);
		Assets.headLeft = g.newPixmap("headleft.png", PixmapFormat.ARGB4444);
		Assets.headRight = g.newPixmap("headright.png", PixmapFormat.ARGB4444);
		Assets.tail = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
		Assets.stain1 = g.newPixmap("stain1.png", PixmapFormat.ARGB4444);
		Assets.stain2 = g.newPixmap("stain2.png", PixmapFormat.ARGB4444);
		Assets.stain3 = g.newPixmap("stain3.png", PixmapFormat.ARGB4444);
		Assets.click = game.getAudio().newSound("click.ogg");
		Assets.eat = game.getAudio().newSound("eat.ogg");
		Assets.bitten = game.getAudio().newSound("bitten.ogg");
		
		Settings.load(game.getFileIO());
		
		Log.d("AndroidGame log", "Loading finished");
		
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		
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
