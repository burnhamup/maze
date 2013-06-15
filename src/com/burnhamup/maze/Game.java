package com.burnhamup.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.burnhamup.maze.pieces.Lightning;
import com.burnhamup.maze.pieces.Mate;
import com.burnhamup.maze.pieces.Piece;
import com.burnhamup.maze.pieces.Rabbit;
import com.burnhamup.maze.pieces.Shadow;
import com.burnhamup.maze.pieces.Stone;
import com.burnhamup.maze.pieces.TimePawn;
import com.burnhamup.maze.pieces.Tree;

public class Game {
	protected Color currentTurn;
	protected Board board;
	protected Set<Mate> whiteMates;
	protected Set<Mate> blackMates;
	protected Set<Position> validMoves;
	protected Position lastMovedPiece;
	
	public Game() {
		currentTurn = Color.WHITE;
		board = new Board();
		whiteMates = new HashSet<>();
		blackMates = new HashSet<>();
		Mate whiteMate1 = new Mate(Color.WHITE);
		Mate whiteMate2 = new Mate(Color.WHITE);
		Mate blackMate1 = new Mate(Color.BLACK);
		Mate blackMate2 = new Mate(Color.BLACK);
		board.addPiece(whiteMate1, new Position(1));
		board.addPiece(whiteMate2, new Position(2));
		board.addPiece(blackMate1, new Position(47));
		board.addPiece(blackMate2, new Position(48));
		whiteMates.add(whiteMate1);
		whiteMates.add(whiteMate2);
		blackMates.add(blackMate1);
		blackMates.add(blackMate2);
		validMoves = new HashSet<>();
	}
	
	/**
	 * These lists do not contain the mates.
	 * @param white List of white pieces that have either been loaded or randomized
	 * @param black List of black pieces that have either been loaded or randomized
	 */
	public void loadStartingPositions(List<Piece> white, List<Piece> black) {
		for (int i =3; i <= white.size(); i++) {
			board.addPiece(white.get(i), new Position(i));
		}
		for (int j = 46; j > (46 - black.size()); j--) {
			board.addPiece(black.get(j), new Position(j));
		}
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
	}
	
	private List<Piece> initialPieces(Color color) {
		List<Piece> result = new ArrayList<>();
		if (color == Color.BLACK) {
			result.add(new Shadow(color, blackMates));
		} else {
			result.add(new Shadow(color, whiteMates));
		}
		result.add(new Lightning(color));
		result.add(new Rabbit(color));
		result.add(new Tree(color));
		result.add(new Stone(color));
		result.add(new TimePawn(color,1));
		result.add(new TimePawn(color,2));
		result.add(new TimePawn(color,3));
		for (Piece p : result) {
			result.add(p);
		}
		return result;
		
	}
	
	public Set<Mate> getMates(Color c) {
		if (c == Color.BLACK) {
			return blackMates;
		} else {
			return whiteMates;
		}
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
				lastMovedPiece = p;
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
			}
		}
	}
	
	

}
