package com.burnhamup.maze;

import java.util.ArrayList;


public class BoardHistory {

    private ArrayList<Move> history;

    public BoardHistory() {
        history = new ArrayList<Move>();
    }

    public void pushMove(Move m) {
        history.add(m);
    }

    public Move popMove() {
        return history.remove((history.size()-1));
    }

}
