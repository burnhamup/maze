package com.burnhamup.maze;

public interface BoardListener {
	
	public void boardHasChanged();
	
	public void spaceHasChanged(Position p);
}
