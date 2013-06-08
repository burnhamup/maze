package com.burnhamup.maze.pieces;

import java.util.HashSet;
import java.util.Set;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;


public class Shadow extends Piece {
    
    public Shadow(Color c, Set<Mate> mates) {
        super(c);
    }

    public Set<Position> getValidMoves(Board b) {
        Set<Position> result = new HashSet<>();
        return result;
    }

}