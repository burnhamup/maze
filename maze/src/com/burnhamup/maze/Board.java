package com.burnhamup.maze;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import com.burnhamup.maze.pieces.Piece;
import com.burnhamup.maze.pieces.Mate;

/**
 * A class representing the board for MAZE
 * 
 * The board looks like:
 * 
 * 
 *   XXXXXX
 *  XXXXXXXX
 * XXXXXXXXXX
 * XXXXXXXXXX
 *  XXXXXXXX
 *   XXXXXX
 * 
 * @author Chris
 *
 */
public class Board {
	protected Space board[][];
	public static final int rows = 6;
	public static final int cols = 10;
	protected Set<Mate> blackMates;
	protected Set<Mate> whiteMates;
    private BoardHistory history;
	
	private boolean drubenVariation = false; //Whether or not the time pawns can move less than the number of their spaces
	
	public Board() {
		blackMates = new HashSet<Mate>();
		whiteMates = new HashSet<Mate>();
		board = new Space[rows][cols];
        history = new BoardHistory();
		Color color = null;
		for (int row =0; row<rows;row++) {
			for (int col=0; col<cols;col++) {
				if (row==0 && (col<2 || col >7)) {
					continue;
				}
				if (row ==1 && (col==0 || col==9)) {
					continue;
				}
				if (row == 4 && (col==0 || col==9)) {
					continue;
				}
				if (row == 5 && (col <2 || col > 7)) {
					continue;
				}
				boolean isDesert = false;
				if (col == 3 || col == 6) {
					isDesert = true;
				}
				if ((row +col) %2 == 0) {
					color = Color.WHITE;
				} else {
					color = Color.BLACK;
				}
				board[row][col] = new Space(new Position(row,col), color, isDesert);
			}
		}
	}
	
	/**
	 * Checks to determine if there is a space at that position.
	 * @param position Position to test
	 * @return true if the position is a legal position to place a piece
	 */
	public boolean isValidPosition(Position position) {
		if (position.row < 0 || position.row >= rows || 
		    position.col < 0 || position.col >= cols) {
		    return false;
		}
		return getSpace(position) != null;
	}
	
	/**
	 * @param position
	 * @return Returns the Space at the given position 
	 */
	public Space getSpace(Position position) {
		return board[position.row][position.col];
	}
	
	/**
	 * 
	 * @param position
	 * @return true if the position is empty because a piece can be placed there. 
	 */
	public boolean isPositionEmpty(Position position) {
		if (isValidPosition(position)) {
			return getSpace(position).getOccupyingPiece() == null;
		} 
		return false;
	}
	
	/**
	 * Adds the piece to the board at position.
	 * @param piece
	 * @param position
	 */
	public void addPiece(Piece piece, Position position) {
		if (!this.isValidPosition(position)) {
			throw new IllegalArgumentException("No such space");
		}
		if (!this.isPositionEmpty(position)) {
			throw new IllegalArgumentException("Current Position is occupied");
		}
		getSpace(position).setOccupyingPiece(piece);
		piece.setPosition(position);
		if (piece.getClass() == Mate.class) {
			Mate mate = (Mate) piece;
			if (piece.getColor() == Color.WHITE) {
				whiteMates.add(mate);
			} else {
				blackMates.add(mate);
			}
		}
		notifySpaceHasChanged(position);
	}
	
	/**
	 * Moves the piece from the current position to the newPosition
	 * @param current
	 * @param newPosition
	 * @throws Exception
	 */
	public void movePiece(Position current, Position newPosition)  {
		if (!this.isValidPosition(newPosition)) {
			throw new IllegalArgumentException("The new position is not a valid position.");
		}
		if (this.isPositionEmpty(current)) {
			throw new IllegalArgumentException("Current position is empty");
		}
		if (!this.isPositionEmpty(newPosition)) {
			throw new IllegalArgumentException("New position is not empty");
		}
		Piece movingPiece = getPiece(current);
		getSpace(current).setOccupyingPiece(null);
		movingPiece.movePiece(newPosition);
		getSpace(newPosition).setOccupyingPiece(movingPiece);
		
		if (getSpace(newPosition).isDesert()) {
			movingPiece.kill();
		}
        Move m = new Move(current, newPosition);
        history.pushMove(m);
		notifySpaceHasChanged(current);
		notifySpaceHasChanged(newPosition);	
	}

    /**
     * Undoes the last move made on this board.
     */
    public boolean undoMove() {
       if (history.count() == 0) {
            return false;
       }
       Move m = history.popMove();
       Piece move = getPiece(m.end);

       getSpace(m.end).setOccupyingPiece(null);
       getSpace(m.start).setOccupyingPiece(move);
       move.movePiece(m.start);
        
       move.unkill();
       return true;
    }
	
	/**
	 * 
	 * @param pos
	 * @return
	 
     */
	public Piece getPiece(Position pos) {
		if (isValidPosition(pos)) {
			return getSpace(pos).getOccupyingPiece();
		}
		return null;
	}
	
	/**
	 * Returns true when the game board is in a winning state
	 * @return
	 */
	public boolean isGameWon() {
		Piece blackMate1 = getPiece(new Position(2,0));
		Piece blackMate2 = getPiece(new Position(3,0));
		Piece whiteMate1 = getPiece(new Position(2,9));
		Piece whiteMate2 = getPiece(new Position(3,9));
		
		if (blackMate1 != null && blackMate1.getColor() == Color.BLACK &&
				blackMate2 != null && blackMate2.getColor() == Color.BLACK &&
				whiteMate1 != null && whiteMate1.getColor() == Color.WHITE &&
				whiteMate2 != null && whiteMate2.getColor() == Color.WHITE) {
			return true;
		}
		
		return false;
	}
	
	
	public Set<Mate> getMates(Color c) {
		if (c == Color.BLACK) {
			return blackMates;
		} else {
			return whiteMates;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}

	public Set<Position> getAllColorPieces(Color c) {
		Set<Position> result = new HashSet<Position>();
		Position p = null;
		Space s = null;
		for (int i = 0; i<rows; i++) {
			for (int j =0; j<cols; j++) {
				p = new Position(i,j);
				s = getSpace(p);
				if (s != null && s.getColor() == c) {
					result.add(p);
				}
			}
		}
		return result;
	}

	public boolean isDrubenVariation() {
		return drubenVariation;
	}

	public void setDrubenVariation(boolean drubenVariation) {
		this.drubenVariation = drubenVariation;
	}
	
	private Vector<BoardListener> listeners = new Vector<BoardListener>();
	
	public void addListener(BoardListener b) {
		listeners.add(b);
	}
	
	public void removeListener(BoardListener b) {
		listeners.remove(b);
	}
	
	private void notifyBoardHasChanged() {
		for (BoardListener l : listeners) {
			l.boardHasChanged();
		}
	}
	
	private void notifySpaceHasChanged(Position p) {
		for (BoardListener l : listeners) {
			l.spaceHasChanged(p);
		}
		notifyBoardHasChanged();
	}

}
