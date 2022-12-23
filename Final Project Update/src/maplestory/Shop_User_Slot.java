package maplestory;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class Shop_User_Slot extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int index;
	
	public Shop_User_Slot(int _index) {
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
		Item item = Shop.currentNullRemovedList.get(index);
		g.drawImage(item.getIcon().getImage(), 17 - item.getIcon().getIconWidth() / 2
				, 17 - item.getIcon().getIconHeight() / 2, Maplestory.current_stage);
		if(Shop.selectedUserSlotIndex == index) {
			g.drawImage(Maplestory.images.Shop_Select.getImage(), 36, 0, Maplestory.current_stage);
		}
		g.setFont(Stage.font);
		g.drawString(item.getName(), 40, 14);
		g.drawString(item.getSellPrice() + " 메소", 40, 31);
	}
}
