package com.burnhamup.maze;

public class Move {

    public Position start;
    public Position end;

    public Move(Position start, Position end) {
        this.start = start.clone();
        this.end = end.clone();
    }

}
