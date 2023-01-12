package maplestory;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class Shop_Shop_Slot extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int index;
	
	public Shop_Shop_Slot(int _index) {
		index = _index;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		ItemForSale itemForSale = Shop.currentSellList.get(index);
		g.drawImage(itemForSale.item.getIcon().getImage(), 17 - itemForSale.item.getIcon().getIconWidth() / 2
				, 17 - itemForSale.item.getIcon().getIconHeight() / 2, this);
		if(Shop.selectedShopSlotIndex == index) {
			g.drawImage(Maplestory.images.Shop_Select.getImage(), 36, 0, this);
		}
		g.setFont(Stage.font);
		if(Shop.currentSellList.get(index).item.Quantity == 0) {
			g.setColor(Color.RED);
		}
		else {
			g.setColor(Color.BLACK);
		}
		g.drawString(itemForSale.item.getName(), 40, 14);
		g.drawString(Item_Meso.dec_format.format(itemForSale.price) + " 메소", 40, 31);
	}
}
