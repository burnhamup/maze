package com.burnhamup.maze.android;

import java.util.HashSet;
import java.util.Set;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Game;
import com.burnhamup.maze.GameListener;
import com.burnhamup.maze.Position;
import com.burnhamup.maze.Space;
import com.burnhamup.maze.pieces.Piece;
import com.burnhamup.maze.pieces.Piece.PieceType;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements GameListener {
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

	public Set<Position> highlightedPositions;

	private GestureDetector mDetector;
	private Position position;

	private int pieceTileSize;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);
		highlightedPositions = new HashSet<Position>();
		mDetector = new GestureDetector(context, new TapListener());
		position = new Position(0,0);
	}
	
	@Override
	 public void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Log.i("cburnham","onSizeChanged called");
		
		initGraphics();

	 }
	
	private void initGraphics() {

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
		pieceTileSize = TileSize * 3 /4 ;
		pieceOffset = (TileSize - pieceTileSize) / 2;
		
		blackPieces[PieceType.Mate.ordinal()] = loadImage(res.getDrawable(R.drawable.blackmate), pieceTileSize);
		blackPieces[PieceType.Shadow.ordinal()] = loadImage(res.getDrawable(R.drawable.blackshadow), pieceTileSize);
		blackPieces[PieceType.Lightning.ordinal()] = loadImage(res.getDrawable(R.drawable.blacklightning), pieceTileSize);
		blackPieces[PieceType.Rabbit.ordinal()] = loadImage(res.getDrawable(R.drawable.blackrabbit), pieceTileSize);
		blackPieces[PieceType.Tree.ordinal()] = loadImage(res.getDrawable(R.drawable.blacktree), pieceTileSize);
		blackPieces[PieceType.Stone.ordinal()] = loadImage(res.getDrawable(R.drawable.blackstone), pieceTileSize);
		blackPieces[PieceType.Pawn1.ordinal()] = loadImage(res.getDrawable(R.drawable.blackpawn1), pieceTileSize);
		blackPieces[PieceType.Pawn2.ordinal()] = loadImage(res.getDrawable(R.drawable.blackpawn2), pieceTileSize);
		blackPieces[PieceType.Pawn3.ordinal()] = loadImage(res.getDrawable(R.drawable.blackpawn3), pieceTileSize);
		
		whitePieces[PieceType.Mate.ordinal()] = loadImage(res.getDrawable(R.drawable.whitemate), pieceTileSize);
		whitePieces[PieceType.Shadow.ordinal()] = loadImage(res.getDrawable(R.drawable.whiteshadow), pieceTileSize);
		whitePieces[PieceType.Lightning.ordinal()] = loadImage(res.getDrawable(R.drawable.whitelightning), pieceTileSize);
		whitePieces[PieceType.Rabbit.ordinal()] = loadImage(res.getDrawable(R.drawable.whiterabbit), pieceTileSize);
		whitePieces[PieceType.Tree.ordinal()] = loadImage(res.getDrawable(R.drawable.whitetree), pieceTileSize);
		whitePieces[PieceType.Stone.ordinal()] = loadImage(res.getDrawable(R.drawable.whitestone), pieceTileSize);
		whitePieces[PieceType.Pawn1.ordinal()] = loadImage(res.getDrawable(R.drawable.whitepawn1), pieceTileSize);
		whitePieces[PieceType.Pawn2.ordinal()] = loadImage(res.getDrawable(R.drawable.whitepawn2), pieceTileSize);
		whitePieces[PieceType.Pawn3.ordinal()] = loadImage(res.getDrawable(R.drawable.whitepawn3), pieceTileSize);
	  
		graphicInit = true;
	}

	
	public void setGame(Game g) {
		game = g;
		game.registerListener(this);
	}
	
    public Game getGame() {
        return game;
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
		this.mDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	
	class TapListener extends GestureDetector.SimpleOnGestureListener {
		
		@Override
		public boolean onDown(MotionEvent event) {
			Log.d("cburnham",event.toString());
			float x = event.getX();
			float y = event.getY();
			//Convert x and y to a position.
			int col = (int) (x - xOffset) / TileSize;
			int row = (int) (y - yOffset) / TileSize;
			position.row = row;
			position.col = col;
			Log.d("cburnham","Position: " + position.toString());
			//Either move or not.
			if (highlightedPositions.contains(position)) {
				game.movePiece(position);
				highlightedPositions.clear();
			} else {				
				highlightedPositions = game.getValidMoves(position);
			}
			invalidate();
			
			
			return true;
		}
	}
	
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
				position.row = row;
				position.col = col;
				Space s = b.getSpace(position);
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
						if (p.isDead()) {
							paint.setColor(android.graphics.Color.RED);
							paint.setStrokeWidth(5);
							canvas.drawLine(xOffset + col * TileSize + pieceOffset, 
									yOffset+row * TileSize + pieceOffset, 
									xOffset + col * TileSize + pieceOffset + pieceTileSize, 
									yOffset+row * TileSize + pieceOffset + pieceTileSize,
									paint);
							canvas.drawLine(xOffset + col * TileSize + pieceOffset + pieceTileSize, 
									yOffset+row * TileSize + pieceOffset, 
									xOffset + col * TileSize + pieceOffset, 
									yOffset+row * TileSize + pieceOffset + pieceTileSize,
									paint);
							paint.setStrokeWidth(0);
							
						}
					}
				}
			}
		}
		for (Position p : highlightedPositions) {
			paint.setColor(android.graphics.Color.RED);
			canvas.drawRect(xOffset+p.col*TileSize,  yOffset+p.row*TileSize, xOffset+p.col*TileSize + TileSize, yOffset+p.row*TileSize + TileSize, paint);
		}
		paint.setColor(android.graphics.Color.BLACK);
		canvas.drawText("Current Turn: " + game.getCurrentTurn(), 10, 10, paint);
		
	}
	
	

	@Override
	public void gameHasChanged() {
		Log.i("cburnham","INVALIDATED");
				
		invalidate();
	}

}
