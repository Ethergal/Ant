package net.galach;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Map {

	private MapCell[][] grid;
	private int width;
	private int height;
	
	public  Map(int width, int height) {
		grid = new MapCell[width][height];
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				grid[x][y] = new MapCell(x, y);
			}
		}	
		//System.out.println("Width grid.length: " + grid.length + ", Height grid[0].length: "+ grid[0].length);
		
		this.width = width;
		this.height = height;
		
		generateRandomMap();
	}
	
	private void generateRandomMap() {
		MapCell cell = grid[(int)(Math.random() * width)][(int)(Math.random() * height)];
		cell.isStart = true;
		
		MapCell start = cell, end = cell;
		int stepsToEnd = 0;
		
		Stack< MapCell > path = new Stack< MapCell >();
		
		do {
			cell.visited = true;

			//find unvisited neighbours
			ArrayList< MapCell > neighbours = new ArrayList< MapCell>();
			
			if (cell.index.y > 0 && !grid[cell.index.x][cell.index.y-1].visited) 
				neighbours.add(grid[cell.index.x][cell.index.y-1]);
			if (cell.index.y < grid[0].length-1 && !grid[cell.index.x][cell.index.y+1].visited) 
				neighbours.add(grid[cell.index.x][cell.index.y+1]);
			if (cell.index.x > 0 && !grid[cell.index.x-1][cell.index.y].visited) 
				neighbours.add(grid[cell.index.x-1][cell.index.y]);
			if (cell.index.x < grid.length-1 && !grid[cell.index.x+1][cell.index.y].visited) 
				neighbours.add(grid[cell.index.x+1][cell.index.y]);
			
			if (!neighbours.isEmpty()) {
				MapCell nextCell = neighbours.get((int)(Math.random()*neighbours.size()));
				
				//open walls
				cell.walls[ (nextCell.index.y < cell.index.y 
                        ? 0 
                        : nextCell.index.y > cell.index.y 
                           	? 2 
                           	: nextCell.index.x < cell.index.x 
                           		? 3 
                           		: 1) ] = false;
				nextCell.walls[ (nextCell.index.y < cell.index.y 
                        ? 2 
                        : nextCell.index.y > cell.index.y 
                        	? 0 
                        	: nextCell.index.x < cell.index.x 
                        		? 1 
                        		: 3) ] = false;
				
				path.push(cell);
				cell = nextCell;
			}
			else {
				if (path.size() > stepsToEnd) {
					end = cell;
					stepsToEnd = path.size();
				}
				cell = path.pop();
			}
			
		} while ( !path.isEmpty() );
		
		grid[end.index.x][end.index.y].isEnd = true;
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j].visited = false;
			}
		}
	}


	public void print() {
		for (int y = 0; y < grid[0].length; y++) {
			if( y > 0 ) {
				for (int x = 0; x < grid.length; x++) {
					if ( x > 0 ) System.out.print("X ");
					System.out.print(grid[x][y].walls[0] ? "X " : "= ");
				}
				System.out.println();
			}
			for (int x = 0; x < grid.length; x++) {
				if ( x > 0 ) System.out.print(grid[x][y].walls[3] ? "X " : "= ");
				System.out.print(grid[x][y].isStart? "S " : grid[x][y].isEnd? "E " : "= ");
			}
			System.out.println();
		}
		
	}
	public void print2() {
		for (int y = 0; y < grid[0].length; y++) {
			for (int x = 0; x < grid.length; x++) {
				System.out.print("X"+(grid[x][y].walls[0] ? "X" : "=")+"X ");
			}
			System.out.println(" ");
			for (int x = 0; x < grid.length; x++) {
				System.out.print((grid[x][y].walls[3] ? "X" : "=")+(grid[x][y].isStart? "S" : grid[x][y].isEnd? "E" : "=")+(grid[x][y].walls[1] ? "X " : "= "));
			}
			System.out.println(" ");
			for (int x = 0; x < grid.length; x++) {
				System.out.print("X"+(grid[x][y].walls[2] ? "X" : "=")+"X ");
			}
			System.out.println(" \n");
		}		
	}
	
	public static void main(String[] args) {
		Map map = new Map(10,10);
		
		//map.print2();
		map.print();
	}

	public MapCell getCell(int x, int y) {
		if (x < grid.length && x >= 0 && y < grid[0].length && y >= 0)
			return grid[x][y];
		
		return null;
	}

	public MapCell getStart() {
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				if(grid[x][y].isStart) return grid[x][y];
			}
		}
		
		return null;
	}

	public MapCell go(MapCell start, int direction) {
		
		if (start.walls[direction]) return null;
		
		int x = start.index.x + (direction == 1 ? 1 : direction == 3 ? -1 : 0);
		int y = start.index.y + (direction == 0 ? -1 : direction == 2 ? 1 : 0);

		return grid[x][y];
	}

	public int getWidth() {
		
		return grid.length;
	}

	public int getHeight() {
		
		return grid[0].length;
	}


}