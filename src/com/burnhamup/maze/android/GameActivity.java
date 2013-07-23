package com.burnhamup.maze.android;


import com.burnhamup.maze.Game;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class GameActivity extends Activity {
	
	protected GameView gameView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("cburnham", "Constructed!");
		//
		//setContentView(new GameView(this));
		setContentView(R.layout.activity_game);
		gameView = (GameView) findViewById(R.id.gameView1);
		if (savedInstanceState == null) {
			gameView.initGame();
		} else {
			Bundle bundle = savedInstanceState.getBundle("maze-game");
			gameView.setGame((Game) bundle.getSerializable("game"));
		}
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		Bundle save = new Bundle();
		save.putSerializable("game", gameView.saveGame());
		outState.putBundle("maze-game", save);
	}

}
