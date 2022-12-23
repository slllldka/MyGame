package maplestory;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class Shop_Shop_Slot extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int index;
	
	public Shop_Shop_Slot(int _index) {
		index = _index;
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		ItemForSale itemForSale = Shop.currentSellList.get(index);
		g.drawImage(itemForSale.item.getIcon().getImage(), 17 - itemForSale.item.getIcon().getIconWidth() / 2
				, 17 - itemForSale.item.getIcon().getIconHeight() / 2, Maplestory.current_stage);
		if(Shop.selectedShopSlotIndex == index) {
			g.drawImage(Maplestory.images.Shop_Select.getImage(), 36, 0, Maplestory.current_stage);
		}
		g.setFont(Stage.font);
		g.drawString(itemForSale.item.getName(), 40, 14);
		g.drawString(itemForSale.item.getSellPrice() + " 메소", 40, 31);
	}
}
