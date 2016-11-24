package net.galach.view;

import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;

import net.galach.Map;

public class AntRouteApplet extends JApplet {
	public AntRouteApplet() {
		
		this.add(new MapView(new Map(50, 50)));
		//this.rootPane.setSize(new Dimension(1024, 768));
		this.setVisible(true);
	}
}
