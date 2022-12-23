package maplestory;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Ladder {
	//x: x position, ystart: y start position, yend: y end position
	private int xm, ystart, yend, length;
	
	public Ladder(int _xm, int _ystart, int _length) {
		xm = _xm;
		ystart = _ystart;
		length = _length;
		
		yend = _ystart + 22 + (length / 2 + length % 2) * 48 + (length / 2) * 56  + 14 - 10;
	}

	//getters
	public int getX() {
		return xm;
	}

	public int getYstart() {
		return ystart;
	}

	public int getYend() {
		return yend;
	}
	
	public void setYend(int _yend) {
		yend = _yend;
	}
	
	public int getLength() {
		return length;
	}
	
	//Draw
	public void drawLadder(Graphics2D g, int x, int y, ImageObserver observer) {
		int ytop = y - 10;
		Image start = Maplestory.images.ladder_start.getImage();
		Image middle1 = Maplestory.images.ladder_middle1.getImage();
		Image middle2 = Maplestory.images.ladder_middle2.getImage();
		Image end = Maplestory.images.ladder_end.getImage();
		
		g.drawImage(start, x+2, ytop, observer);
		ytop += 22;
		for(int i = 0; i < (length / 2); i++) {
			g.drawImage(middle1, x+1, ytop, observer);
			ytop += 48;
			g.drawImage(middle2, x, ytop, observer);
			ytop += 56;
		}
		
		if(length % 2 == 1) {
			g.drawImage(middle1, x+1, ytop, observer);
			ytop += 48;
			g.drawImage(end, x+2, ytop, observer);
		}
		else {
			g.drawImage(end, x+3, ytop, observer);
		}
	}
	
	public void DSILadder(Graphics2D g, int x, int y, int offset, ImageObserver observer) {
		int ytop = y - 10;
		ImageIcon start = Maplestory.images.ladder_start;
		ImageIcon middle1 = Maplestory.images.ladder_middle1;
		ImageIcon middle2 = Maplestory.images.ladder_middle2;
		ImageIcon end = Maplestory.images.ladder_end;
		
		g.drawImage(start.getImage(), (x+2) / offset, ytop / offset, start.getIconWidth() / offset, start.getIconHeight() / offset, observer);
		ytop += 22;
		for(int i = 0; i < (length / 2); i++) {
			g.drawImage(middle1.getImage(), (x+1) / offset, ytop / offset, middle1.getIconWidth() / offset, middle1.getIconHeight() / offset, observer);
			ytop += 48;
			g.drawImage(middle2.getImage(), x / offset, ytop / offset, middle2.getIconWidth() / offset, middle2.getIconHeight() / offset, observer);
			ytop += 56;
		}
		
		if(length % 2 == 1) {
			g.drawImage(middle1.getImage(), (x+1) / offset, ytop / offset, middle1.getIconWidth() / offset, middle1.getIconHeight() / offset, observer);
			ytop += 48;
			g.drawImage(end.getImage(), (x+2) / offset, ytop / offset, end.getIconWidth() / offset, end.getIconHeight() / offset, observer);
		}
		else {
			g.drawImage(end.getImage(), (x+3) / offset, ytop / offset, end.getIconWidth() / offset, end.getIconHeight() / offset, observer);
		}
	}
}
