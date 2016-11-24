package net.galach.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;

import net.galach.Map;
import net.galach.MapCell;

public class MapView extends Canvas {
	Map map;
	Dimension size;
	
	BufferedImage backImg = null;
	
	public MapView(Map map) {
		this.map = map;
		
	}
	
	@Override
	public void update(Graphics g) {
		System.out.println("update");
	}
	
	@Override
	public void paint(Graphics g1) {
		// TODO Auto-generated method stub
		//super.paint(g);
		if (backImg == null) {
			this.size = this.getSize();
			
			int x_offset = 10, y_offset = 10;
			int cell_size = Math.max(3, Math.min((size.width - 2*x_offset) / map.getWidth() , (size.height - 2*y_offset) / map.getHeight()));
			int route_size = cell_size * 8 / 10, 
				wall_size = cell_size - route_size;

			backImg = new BufferedImage(2*x_offset + map.getWidth() * cell_size, 2*y_offset + map.getHeight() * cell_size, BufferedImage.TYPE_INT_RGB);
			Graphics g = backImg.getGraphics();	
			
			for (int x = 0; x < map.getWidth(); x++) {
				for (int y = 0; y < map.getHeight(); y++) {
					MapCell cell = map.getCell(x, y);
					
					g.setColor(Color.WHITE);
					g.fillRect(x_offset + x * cell_size , y_offset + y * cell_size, cell_size, cell_size);

					g.setColor(cell.isStart ? Color.GREEN : cell.isEnd ? Color.RED : Color.WHITE);
					g.fillRect(x_offset + x * cell_size + wall_size/2 , y_offset + y * cell_size + wall_size/2, route_size, route_size);
					
					g.setColor(Color.BLACK);
					for (int i = 0; i < cell.walls.length; i++) {
						if (cell.walls[i]) {
							int x_start = x_offset + x * cell_size + (i == 1 ?  cell_size : 0);
							int y_start = y_offset + y * cell_size + (i == 2 ?  cell_size : 0);
							int x_end   = x_offset + x * cell_size + (i == 3 ?  0 : cell_size);
							int y_end   = y_offset + y * cell_size + (i == 0 ?  0 : cell_size);
							
							g.drawLine(x_start, y_start, x_end, y_end);
						}
					}
				}
			}	
			g.dispose();

		}
		g1.drawImage(backImg, 0, 0, this);
	}
}
