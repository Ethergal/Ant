package net.galach;

public class MapCell {
	
	public Tuple<Integer, Integer> index = null;
	
	public boolean visited = false;
	public boolean[] walls = {true, true, true, true}; //N,E,S,W
	
	public boolean isStart = false;
	public boolean isEnd   = false;

	public MapCell() {
	}
	
	public MapCell(int x, int y) {
		index = new Tuple<Integer, Integer>(x, y);
		
	}
}