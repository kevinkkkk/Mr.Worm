package com.kevinkuai.worm;

import android.util.Log;

import com.kevinkuai.framework.Screen;
import com.kevinkuai.framework.game1.AndroidGame;

public class WormGame extends AndroidGame{

	@Override
	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		
		Log.d("AndroidGame log", "WormGame stated");	
		return new LoadingScreen(this);
		

	}

	
	
	

}
