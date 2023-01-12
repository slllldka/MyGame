package maplestory;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class NPC extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static int LEFT = 0, RIGHT = 1;

	protected String name;
	protected String subName = "";
	protected ImageIcon image;
	
	protected int xcenter, xpos, ybottom, ypos, direction;
	
	public NPC(String _name, ImageIcon _image, int _xcenter, int _ybottom) {
		name = _name;
		image = _image;
		
		xcenter = _xcenter;
		xpos = _xcenter - image.getIconWidth() / 2;
		ybottom = _ybottom;
		ypos = _ybottom - image.getIconHeight();
		
		setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		
		setMouseEvents();
		setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		setLocation(xpos - Maplestory.current_stage.CameraX, ypos - Maplestory.current_stage.CameraY);
	}
	
	public void setMouseEvents() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickEvent();
			}
		});
	}
	
	public abstract void clickEvent();
	
	public abstract String getType();
	
}