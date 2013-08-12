package com.burnhamup.maze;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.burnhamup.maze.pieces.Lightning;
import com.burnhamup.maze.pieces.Mate;
import com.burnhamup.maze.pieces.Piece;
import com.burnhamup.maze.pieces.Rabbit;
import com.burnhamup.maze.pieces.Shadow;
import com.burnhamup.maze.pieces.Stone;
import com.burnhamup.maze.pieces.TimePawn;
import com.burnhamup.maze.pieces.Tree;

public class Game implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5361738109052980571L;
	protected Color currentTurn;
	protected Board board;
	protected Set<Position> validMoves;
	protected Position lastMovedPiece;

	
	/**
	 * Creates a new game with mates in their starting positions,
	 * with white having the first move.
	 */
	public Game() {
		currentTurn = Color.WHITE;
		board = new Board();
		Mate whiteMate1 = new Mate(Color.WHITE);
		Mate whiteMate2 = new Mate(Color.WHITE);
		Mate blackMate1 = new Mate(Color.BLACK);
		Mate blackMate2 = new Mate(Color.BLACK);
		board.addPiece(whiteMate1, new Position(1));
		board.addPiece(whiteMate2, new Position(2));
		board.addPiece(blackMate1, new Position(47));
		board.addPiece(blackMate2, new Position(48));
		validMoves = new HashSet<Position>();
	}
	
	/**
	 * These lists do not contain the mates.
	 * @param white List of white pieces that have either been loaded or randomized
	 * @param black List of black pieces that have either been loaded or randomized
	 */
	public void loadStartingPositions(List<Piece> white, List<Piece> black) {
		for (int i =0; i < white.size(); i++) {
			board.addPiece(white.get(i), new Position(i+3));
		}
		for (int j = 0; j < black.size(); j++) {
			board.addPiece(black.get(j), new Position(46-j));
		}
		this.notifyGameHasChanged();
	}
	
	//TODO add support for a seeded random number generator
	public void loadRandomStartingPositions(int numberOfPieces) {
		List<Piece> white = initialPieces(Color.WHITE);
		Collections.shuffle(white);
		white = white.subList(0, numberOfPieces);
		List<Piece> black = initialPieces(Color.BLACK);
		Collections.shuffle(black);
		black = black.subList(0, numberOfPieces);
		loadStartingPositions(white,black);
		this.notifyGameHasChanged();
	}
	
	public List<Piece> loadSetStartingPosition(String text, Color c) {
		List<Piece> result = new ArrayList<Piece>();
		for (int i=0; i< text.length(); i++) {
			char letter = text.charAt(i);
			switch (letter) {
			case 'H': //Shadow
				result.add(new Shadow(c));
				break;
			case 'L': //Lightning
				result.add(new Lightning(c));
				break;
			case 'R':
				result.add(new Rabbit(c));
				break;
			case 'T':
				result.add(new Tree(c));
				break;
			case 'S':
				result.add(new Stone(c));
				break;
			case '1':
				result.add(new TimePawn(c,1));
				break;
			case '2':
				result.add(new TimePawn(c,2));
				break;
			case '3':
				result.add(new TimePawn(c,3));
				break;
			}
		}
		return result;
	}
	
	private List<Piece> initialPieces(Color color) {
		List<Piece> result = new ArrayList<Piece>();
		result.add(new Shadow(color));
		result.add(new Lightning(color));
		result.add(new Rabbit(color));
		result.add(new Tree(color));
		result.add(new Stone(color));
		result.add(new TimePawn(color,1));
		result.add(new TimePawn(color,2));
		result.add(new TimePawn(color,3));
		result.add(new Shadow(color));
		result.add(new Lightning(color));
		result.add(new Rabbit(color));
		result.add(new Tree(color));
		result.add(new Stone(color));
		result.add(new TimePawn(color,1));
		result.add(new TimePawn(color,2));
		result.add(new TimePawn(color,3));
		return result;
		
	}
	
	
	/**
	 * This method can only be called on a piece belonging to the player whose turn it is.
	 * @param p
	 * @return
	 */
	public Set<Position> getValidMoves(Position p) {
		validMoves.clear();
		lastMovedPiece = null;
		if (currentTurn == Color.BLACK && p.col >= 5 ||
				currentTurn ==Color.WHITE && p.col < 5) {
			Piece piece = board.getPiece(p);
			if (piece != null) {
				lastMovedPiece = p.clone();
				validMoves = piece.getValidMoves(board);
			}
		}
		return validMoves;
	}
	
	/**
	 * This method is applied to the piece that was selected by getValidMoves.
	 * A piece can only be moved to a position that was returned by getValidMoves
	 * @param start
	 * @param end
	 */
	public void movePiece(Position end) {
		if (lastMovedPiece!= null) {
			if (validMoves.contains(end)) {
				board.movePiece(lastMovedPiece, end);
				if (currentTurn == Color.WHITE) {
					currentTurn = Color.BLACK;
				} else {
					currentTurn = Color.WHITE;
				}
			}
		}
		notifyGameHasChanged();
	}

    public void undoMove() {
        if (board.undoMove()) {
            if (currentTurn == Color.WHITE) {
                 currentTurn = Color.BLACK;
            } else {
                currentTurn = Color.WHITE;
            }
        notifyGameHasChanged();
        }
    }

	public Board getBoard() {
		return board;
	}
	
	private Vector<GameListener> listeners  = new Vector<GameListener>();
	
	public void registerListener(GameListener newListener) {
		listeners.add(newListener);
	}
	
	public void removeListener(GameListener listener) {
		listeners.remove(listener);
	}
	
	private void notifyGameHasChanged() {
		for (GameListener l : listeners) {
			l.gameHasChanged();
		}
	}
	
	private void writeObject(java.io.ObjectOutputStream out) {
		listeners = null;
	}

	/**
	 * @return the currentTurn
	 */
	public Color getCurrentTurn() {
		return currentTurn;
	}

	/**
	 * @param currentTurn the currentTurn to set
	 */
	public void setCurrentTurn(Color currentTurn) {
		this.currentTurn = currentTurn;
	}
	
	
	

}
