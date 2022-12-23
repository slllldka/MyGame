package maplestory;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UI_Quick_Slot_Icon extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int index;
	private Item item = null;
	
	public UI_Quick_Slot_Icon(int _index) {
		index = _index;
		setBounds(8 + 35 * (index % 4), 9 + 32 * (index / 4), 30, 30);
	}
	
	public int Get_Index() {
		return index;
	}
	
	public Item Get_Item() {
		return item;
	}
	
	public void set_Item(Item _item) {
		if(_item == null) {
			item = null;
			Maplestory.keysetting.itemUse[index].Set(-1, "");
		}
		else {
			item = _item.getNew(0);
			Maplestory.keysetting.itemUse[index].Set(item.getItemCode(), item.getType());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(item != null) {
			g.drawImage(item.getRawIcon().getImage(), 2, 2, Maplestory.current_stage);
			Show_Number(g);
		}
		g.drawImage(Maplestory.images.Quick_Slot_Numbers[index].getImage(), 1, 1, Maplestory.current_stage);
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
	
	public void Show_Number(Graphics g) {
		Item data = null;
		for(int i=0;i<Maplestory.player.inventory.Consume_size;i++) {
			Item temp = Maplestory.player.inventory.Consume_inventory_list.get(i);
			if(temp != null) {
				if(item.getItemCode() == temp.getItemCode()) {
					data = temp;
				}
			}
		}
		if(data != null) {
			Draw_Number(g, data.Quantity, 0, getHeight()-12);
		}
		else {
			Draw_Number(g, 0, 0, getHeight()-12);
		}
	}
	
	public void Draw_Number(Graphics g, int Quantity, int X, int Y) {
		if(Quantity < 10) {
			ImageIcon num1 = Get_Number_Icon(Quantity);
			g.drawImage(num1.getImage(), X, Y, Maplestory.current_stage);
		}
		else if(Quantity < 100) {
			int first = Quantity / 10;
			int second = Quantity - first*10;
			ImageIcon num1 = Get_Number_Icon(first);
			ImageIcon num2 = Get_Number_Icon(second);
			g.drawImage(num1.getImage(), X, Y, Maplestory.current_stage);
			g.drawImage(num2.getImage(), X+num1.getIconWidth(), Y, Maplestory.current_stage);
		}
		else if(Quantity < 1000) {
			int first = Quantity / 100;
			int second = (Quantity - first*100) / 10;
			int third = Quantity - first*100 - second*10;
			ImageIcon num1 = Get_Number_Icon(first);
			ImageIcon num2 = Get_Number_Icon(second);
			ImageIcon num3 = Get_Number_Icon(third);
			g.drawImage(num1.getImage(), X, Y, Maplestory.current_stage);
			g.drawImage(num2.getImage(), X+num1.getIconWidth(), Y, Maplestory.current_stage);
			g.drawImage(num3.getImage(), X+num1.getIconWidth()+num2.getIconWidth(), Y, Maplestory.current_stage);
		}
	}
}
