package maplestory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class Wall {
	protected int x, ystart, yend;
	protected boolean left = false;
	
	public Wall(int _x, int _ystart, int _yend, boolean _left) {
		x = _x;
		ystart = _ystart;
		yend = _yend;
		left = _left;
	}
	
	public boolean canLeftGo(int CX, int CY, int CW, int CH) {
		if(left) {
			if((CX + CW / 2) == x) {
				if((CY+CH <= ystart && CY <= ystart) || (CY >= yend && CY+CH >= yend)) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return true;
			}
		}
		else {
			return true;
		}
	}
	public boolean canRightGo(int CX, int CY, int CW, int CH) {
		if(!left) {
			if((CX + CW / 2) == x) {
				if((CY+CH <= ystart && CY <= ystart) || (CY >= yend && CY+CH >= yend)) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return true;
			}
		}
		else {
			return true;
		}
	}
	
	public void dsiWall(Graphics2D g, int offset, ImageObserver ob) {
		g.setColor(Color.GREEN);
		g.drawLine(x / offset, ystart / offset, x / offset, yend / offset);
	}
}
