package com.sdl.StudentAdmission;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class WindowDragger 
{
	private static int mouseX;
	private static int mouseY;
	
	public static void panelMouseDragged(MouseEvent e, JFrame f)
	{
		int x_coordinate = e.getXOnScreen();
		int y_coordinate = e.getYOnScreen();
		
		f.setLocation(x_coordinate - mouseX, y_coordinate - mouseY);
	}
	
	public static void panelMousePressed(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}
}
