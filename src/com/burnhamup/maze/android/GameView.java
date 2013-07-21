package com.burnhamup.maze.android;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Game;
import com.burnhamup.maze.GameListener;
import com.burnhamup.maze.Position;
import com.burnhamup.maze.Space;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends View implements Callback, GameListener {
	private Game game;
	
	private static int TileSize = 96;
	
	Bitmap blackSquare;
	Bitmap whiteSquare;
	
	private Paint paint = new Paint();
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
//		getHolder().addCallback(this);
		setFocusable(true);

		//Load Images
		Resources res = context.getResources();
		blackSquare = loadImage(res.getDrawable(R.drawable.blacksquare));
		whiteSquare = loadImage(res.getDrawable(R.drawable.whitesquare));
		
		game = new Game();
		game.registerListener(this);
		game.loadRandomStartingPositions(10);
		Log.i("cburnham", "VIEW BUILT!");
		invalidate();

	}
	
	private Bitmap loadImage(Drawable tile) {
		Bitmap bitmap = Bitmap.createBitmap(TileSize, TileSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0,0,TileSize, TileSize);
        tile.draw(canvas);
        return bitmap;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		Log.i("cburnham", "Drawing!");
		super.onDraw(canvas);
		Board b = game.getBoard();
		for (int row =0; row < Board.rows; row++) {
			for (int col = 0; col < Board.cols; col++) {
				Space s = b.getSpace(new Position(row, col));
				if (s != null) {
					if (s.getColor() == Color.BLACK) {
						canvas.drawBitmap(blackSquare, col*TileSize, row * TileSize, paint);
					} else {
						canvas.drawBitmap(whiteSquare, col*TileSize, row * TileSize, paint);
					}
				}
			}
		}
		canvas.drawText("Welcome to Maze", 10, 10, paint);
	}

	@Override
	public void gameHasChanged() {
		Log.i("cburnham","INVALIDATED");
				
		invalidate();
	}

}
