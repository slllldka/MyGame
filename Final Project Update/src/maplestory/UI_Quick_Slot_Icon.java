package maplestory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UI_Quick_Slot_Icon extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int quickslotindex, index;
	protected Item item = null, tempItem = null;
	
	public UI_Quick_Slot_Icon(int _index) {
		quickslotindex = _index;
		index = -1;
		setBounds(8 + 35 * (quickslotindex % 4), 9 + 32 * (quickslotindex / 4), 30, 30);
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int _index) {
		index = _index;
	}
	
	public int getQuickSlotIndex() {
		return quickslotindex;
	}
	
	public void setQuickSlotIndex(int _quickslotindex) {
		quickslotindex = _quickslotindex;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item _item) {
		if(_item == null) {
			item = null;
			Maplestory.keyConfig.itemUse[KeyEvent.VK_1 + quickslotindex].setValues(-1, "");
		}
		else {
			item = _item.getNew(0);
			Maplestory.keyConfig.itemUse[KeyEvent.VK_1 + quickslotindex].setValues(item.getItemCode(), item.getType());
		}
	}
	
	public ImageIcon getNumberIcon(int Number) {
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
	
	public void showNumber(Graphics g) {
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
			drawNumber(g, data.Quantity, 0, getHeight()-12);
		}
		else {
			drawNumber(g, 0, 0, getHeight()-12);
		}
	}
	
	public void drawNumber(Graphics g, int Quantity, int X, int Y) {
		if(Quantity < 10) {
			ImageIcon num1 = getNumberIcon(Quantity);
			g.drawImage(num1.getImage(), X, Y, Maplestory.current_stage);
		}
		else if(Quantity < 100) {
			int first = Quantity / 10;
			int second = Quantity - first*10;
			ImageIcon num1 = getNumberIcon(first);
			ImageIcon num2 = getNumberIcon(second);
			g.drawImage(num1.getImage(), X, Y, Maplestory.current_stage);
			g.drawImage(num2.getImage(), X+num1.getIconWidth(), Y, Maplestory.current_stage);
		}
		else if(Quantity < 1000) {
			int first = Quantity / 100;
			int second = (Quantity - first*100) / 10;
			int third = Quantity - first*100 - second*10;
			ImageIcon num1 = getNumberIcon(first);
			ImageIcon num2 = getNumberIcon(second);
			ImageIcon num3 = getNumberIcon(third);
			g.drawImage(num1.getImage(), X, Y, Maplestory.current_stage);
			g.drawImage(num2.getImage(), X+num1.getIconWidth(), Y, Maplestory.current_stage);
			g.drawImage(num3.getImage(), X+num1.getIconWidth()+num2.getIconWidth(), Y, Maplestory.current_stage);
		}
	}
}
