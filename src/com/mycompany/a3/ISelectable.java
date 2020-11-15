package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public interface ISelectable {
	public void setSelected(boolean isSelected);
	public boolean isSelected();
	public boolean contains(Point p1, Point p2);
	public void draw(Graphics graphics, Point p2);
}
