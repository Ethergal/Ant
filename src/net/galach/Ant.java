package net.galach;

import java.util.ArrayList;
import java.util.Vector;
import java.util.function.Consumer;

public class Ant {

	Map map;
	public Ant(Map map) {
		this.map = map;
	}

	public ArrayList<MapCell> findRoute(MapCell start, ArrayList<MapCell> route) {
		
		if (route == null) route = new ArrayList<MapCell>();
		
		//printRoute(route);
		
		if (start == null) return route;
		if (route.indexOf(start) >= 0) return route;
		
		start.visited = true;
		route.add(start);
		
		if (start.isEnd) {
			return route;
		}
		
		for (int i = 0; i < 4; i++) {
			MapCell next = map.go(start, i);
			if (next == null)
				continue;
			
			route = findRoute(next, route);
			if (route.indexOf(start) < route.size() - 1)
				return route;
		}
		
		// if not found, set not visited, return not found
		start.visited = false;
		route.remove(start);
		
		return route;
	}
	
	public void printRoute(ArrayList<MapCell> route) {
		System.out.print("< ");
		route.forEach(new Consumer<MapCell>() {

			@Override
			public void accept(MapCell cell) {
				System.out.print(cell.index);
			}
		});
		System.out.println("> ");
		
	}
	
	public static void main(String[] args) {
		Ant ant = new Ant(new Map(10, 10));
		ant.map.print();
		
		ArrayList<MapCell> route = ant.findRoute(ant.map.getStart(), null);
		ant.printRoute(route);
	}

}
