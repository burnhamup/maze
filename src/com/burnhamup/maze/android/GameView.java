package com.burnhamup.maze.android;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Game;
import com.burnhamup.maze.GameListener;
import com.burnhamup.maze.Position;
import com.burnhamup.maze.Space;
import com.burnhamup.maze.pieces.Piece;
import com.burnhamup.maze.pieces.Piece.PieceType;

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
import android.view.View;

public class GameView extends View implements Callback, GameListener {
	private Game game;
	
	private static int TileSize = 64;
	private boolean graphicInit = false;
	
	Bitmap blackSquare;
	Bitmap whiteSquare;
	
	Bitmap[] blackPieces;
	Bitmap[] whitePieces;
	
	private Paint paint = new Paint();

	private int xOffset;

	private int yOffset;

	private int pieceOffset;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
//		getHolder().addCallback(this);
		setFocusable(true);
//		TileSize = (this.getHeight() -20) / 6;
		Log.i("cburnham",Integer.toString(this.getHeight()));

		
	}
	
	@Override
	 public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		Log.i("cburnham","onWindowFocusChanged called");
		
			initGraphics();

	 }
	
	private void initGraphics() {
		if (graphicInit) {
			return;
		}
		int tileWidth = getWidth() / Board.cols;
		int tileHeight = getHeight() / Board.rows;
		TileSize = Math.min(tileWidth, tileHeight);
		xOffset = (getWidth() - TileSize * Board.cols) / 2;
		yOffset = (getHeight() - TileSize * Board.rows) / 2; 
		//Load Images
		Resources res = getContext().getResources();
		blackSquare = loadImage(res.getDrawable(R.drawable.blacksquare), TileSize);
		whiteSquare = loadImage(res.getDrawable(R.drawable.whitesquare), TileSize);
		
		blackPieces = new Bitmap[PieceType.values().length];
		whitePieces = new Bitmap[PieceType.values().length];
		int smallerSize = TileSize * 3 /4 ;
		pieceOffset = (TileSize - smallerSize) / 2;
		
		blackPieces[PieceType.Mate.ordinal()] = loadImage(res.getDrawable(R.drawable.blackmate), smallerSize);
		blackPieces[PieceType.Shadow.ordinal()] = loadImage(res.getDrawable(R.drawable.blackshadow), smallerSize);
		blackPieces[PieceType.Lightning.ordinal()] = loadImage(res.getDrawable(R.drawable.blacklightning), smallerSize);
		blackPieces[PieceType.Rabbit.ordinal()] = loadImage(res.getDrawable(R.drawable.blackrabbit), smallerSize);
		blackPieces[PieceType.Tree.ordinal()] = loadImage(res.getDrawable(R.drawable.blacktree), smallerSize);
		blackPieces[PieceType.Stone.ordinal()] = loadImage(res.getDrawable(R.drawable.blackstone), smallerSize);
		blackPieces[PieceType.Pawn1.ordinal()] = loadImage(res.getDrawable(R.drawable.blackpawn1), smallerSize);
		blackPieces[PieceType.Pawn2.ordinal()] = loadImage(res.getDrawable(R.drawable.blackpawn2), smallerSize);
		blackPieces[PieceType.Pawn3.ordinal()] = loadImage(res.getDrawable(R.drawable.blackpawn3), smallerSize);
		
		whitePieces[PieceType.Mate.ordinal()] = loadImage(res.getDrawable(R.drawable.whitemate), smallerSize);
		whitePieces[PieceType.Shadow.ordinal()] = loadImage(res.getDrawable(R.drawable.whiteshadow), smallerSize);
		whitePieces[PieceType.Lightning.ordinal()] = loadImage(res.getDrawable(R.drawable.whitelightning), smallerSize);
		whitePieces[PieceType.Rabbit.ordinal()] = loadImage(res.getDrawable(R.drawable.whiterabbit), smallerSize);
		whitePieces[PieceType.Tree.ordinal()] = loadImage(res.getDrawable(R.drawable.whitetree), smallerSize);
		whitePieces[PieceType.Stone.ordinal()] = loadImage(res.getDrawable(R.drawable.whitestone), smallerSize);
		whitePieces[PieceType.Pawn1.ordinal()] = loadImage(res.getDrawable(R.drawable.whitepawn1), smallerSize);
		whitePieces[PieceType.Pawn2.ordinal()] = loadImage(res.getDrawable(R.drawable.whitepawn2), smallerSize);
		whitePieces[PieceType.Pawn3.ordinal()] = loadImage(res.getDrawable(R.drawable.whitepawn3), smallerSize);
	  
		graphicInit = true;
		invalidate();
	}

	
	public void setGame(Game g) {
		game = g;
		game.registerListener(this);
	}
	
	
	public Game saveGame() {
		game.removeListener(this);
		return game;
	}
	
	private Bitmap loadImage(Drawable tile, int tilesize) {
		Bitmap bitmap = Bitmap.createBitmap(tilesize, tilesize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0,0,tilesize, tilesize);
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
		super.onDraw(canvas);
		Log.i("cburnham","Trying to Draw");
		if (!graphicInit) {
			return;
		}
		Board b = game.getBoard();
		for (int row =0; row < Board.rows; row++) {
			for (int col = 0; col < Board.cols; col++) {
				Space s = b.getSpace(new Position(row, col));
				if (s != null) {
					if (s.getColor() == Color.BLACK) {
						canvas.drawBitmap(blackSquare, xOffset + col*TileSize, yOffset + row * TileSize, paint);
					} else {
						canvas.drawBitmap(whiteSquare, xOffset + col*TileSize, yOffset + row * TileSize, paint);
					}
					Piece p = s.getOccupyingPiece();
					if (p != null) {
						PieceType type = p.getPieceType();
						Bitmap pieceToDraw = null;
						if (p.getColor() == Color.BLACK){
							pieceToDraw = blackPieces[type.ordinal()];
						} else {
							pieceToDraw = whitePieces[type.ordinal()];
						}
						canvas.drawBitmap(pieceToDraw, xOffset + col*TileSize + pieceOffset, yOffset + row * TileSize + pieceOffset, paint);
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
