package com.burnhamup.maze.pieces;

import java.util.ArrayList;
import java.util.List;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;


public class Mate extends Piece {

	public Mate(Position p, Color c) {
		super(p, c);
	}

	@Override
	public List<Position> getValidMoves(Board board) {
		//Go diagonally in all directions.
		List<Position> list = new ArrayList<Position>();
		Position checkPosition = this.position.clone();
		
		checkPosition.col-=1; checkPosition.row-=1;
		while (board.isPositionEmpty(checkPosition)) {
			list.add(checkPosition.clone());
			checkPosition.col-=1; checkPosition.row-=1;
		}
		
		checkPosition = this.position.clone();
		checkPosition.col-=1; checkPosition.row+=1;
		while (board.isPositionEmpty(checkPosition)) {
			list.add(checkPosition.clone());
			checkPosition.col-=1; checkPosition.row+=1;
		}
		
		checkPosition = this.position.clone();
		checkPosition.col+=1; checkPosition.row+=1;
		while (board.isPositionEmpty(checkPosition)) {
			list.add(checkPosition.clone());
			checkPosition.col+=1; checkPosition.row+=1;
		}
		
		checkPosition = this.position.clone();
		checkPosition.col+=1; checkPosition.row-=1;
		while (board.isPositionEmpty(checkPosition)) {
			list.add(checkPosition.clone());
			checkPosition.col+=1; checkPosition.row-=1;
		}
		
		
		return list;
	}

	@Override
	public void movePiece(Position p) {
		// TODO Auto-generated method stub
		
	}

}
