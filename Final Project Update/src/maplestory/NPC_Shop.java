package maplestory;

import javax.swing.ImageIcon;

public class NPC_Shop extends NPC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static String type = "Shop";
	
	protected Shop shop;
	
	public NPC_Shop(String _name, ImageIcon _image, Shop _shop, int _xpos, int _ypos, int _direction) {
		super(_name, _image, _xpos, _ypos);
		subName = "잡화 상인";
		shop = _shop;
		direction = _direction;
	}
	
	@Override
	public void clickEvent() {
		shop.open(image);
	}
	
	public String getType() {
		return type;
	}
}
