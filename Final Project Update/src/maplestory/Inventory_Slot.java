package maplestory;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Inventory_Slot extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int index;
	private ImageIcon icon;
	
	public Inventory_Slot(int _index) {
		index = _index;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(isVisible()) {
			Item data = Maplestory.player.inventory.current_inventory_list.get(index);
			if(data != null) {
				icon = data.getIcon();
				g.drawImage(icon.getImage(), 16 - icon.getIconWidth() / 2, 16 - icon.getIconHeight() / 2, this);
				if(Maplestory.player.inventory.current_type != 0) {
					Show_Number(g, data);
				}
			}
			else {
				if(index < Maplestory.player.inventory.current_size) {
					icon = null;
				}
				else {
					icon = Maplestory.images.Slot_DisabledImg;
					g.drawImage(icon.getImage(), 0, 0, this);
				}
			}
		}
	}
	
	public ImageIcon Get_Number_Icon(int Number) {
		if(Number == 0) {
			return Maplestory.images.Number0;
		}
		else if(Number == 1) {
			return Maplestory.images.Number1;
		}
		else if(Number == 2) {
			return Maplestory.images.Number2;
		}
		else if(Number == 3) {
			return Maplestory.images.Number3;
		}
		else if(Number == 4) {
			return Maplestory.images.Number4;
		}
		else if(Number == 5) {
			return Maplestory.images.Number5;
		}
		else if(Number == 6) {
			return Maplestory.images.Number6;
		}
		else if(Number == 7) {
			return Maplestory.images.Number7;
		}
		else if(Number == 8) {
			return Maplestory.images.Number8;
		}
		else if(Number == 9) {
			return Maplestory.images.Number9;
		}
		else {
			return null;
		}
	}
	
	public void Show_Number(Graphics g, Item data) {
		if(data != null) {
			Draw_Number(g, data.Quantity, 1, getHeight()-12);
		}
	}
	
	public void Draw_Number(Graphics g, int Quantity, int X, int Y) {
		if(Quantity < 10) {
			ImageIcon num1 = Get_Number_Icon(Quantity);
			g.drawImage(num1.getImage(), X, Y, this);
		}
		else if(Quantity < 100) {
			int first = Quantity / 10;
			int second = Quantity - first*10;
			ImageIcon num1 = Get_Number_Icon(first);
			ImageIcon num2 = Get_Number_Icon(second);
			g.drawImage(num1.getImage(), X, Y, this);
			g.drawImage(num2.getImage(), X+num1.getIconWidth(), Y, this);
		}
		else if(Quantity < 1000) {
			int first = Quantity / 100;
			int second = (Quantity - first*100) / 10;
			int third = Quantity - first*100 - second*10;
			ImageIcon num1 = Get_Number_Icon(first);
			ImageIcon num2 = Get_Number_Icon(second);
			ImageIcon num3 = Get_Number_Icon(third);
			g.drawImage(num1.getImage(), X, Y, this);
			g.drawImage(num2.getImage(), X+num1.getIconWidth(), Y, this);
			g.drawImage(num3.getImage(), X+num1.getIconWidth()+num2.getIconWidth(), Y, this);
		}
	}

	public int Get_Index() {
		return index;
	}
}
