package maplestory;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Inventory_Tab_Button extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		g.drawImage(((ImageIcon)getIcon()).getImage(), 6, 1+(19-getIcon().getIconHeight())/2, this);
	}
}
