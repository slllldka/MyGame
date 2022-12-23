package maplestory;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class UI_Quick_Slot extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected UI_Quick_Slot_Icon[] Icon = new UI_Quick_Slot_Icon[8];
	protected int move_index = -1;
	
	public UI_Quick_Slot() {
		setBounds(648, 455, 151, 80);
		setIcon(Maplestory.images.QuickSlotImg);
		for(int i=0;i<8;i++) {
			Icon[i] = new UI_Quick_Slot_Icon(i);
			Icon[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e)) {
						UI_Quick_Slot_Icon source = (UI_Quick_Slot_Icon)e.getSource();
						int index = source.Get_Index();
						//Not Moving Item
						if(Stage.move == null) {
							Item item = Icon[index].Get_Item();
							if(item != null) {
								Music DragStart_Music = new Music("DragStart.wav", 1);
								DragStart_Music.play();
								Stage.move = Icon[index].Get_Item().getRawIcon();
								Maplestory.player.inventory.move_index = -1;
								Maplestory.quick_slot.move_index = Icon[index].Get_Index();
							}
						}
						//Moving Item
						else {
							//Moving Item from Inventory
							if(Maplestory.player.inventory.move_index != -1) {
								Music DragEnd_Music = new Music("DragEnd.wav", 1);
								DragEnd_Music.play();
								Stage.move = null;
								Item update = Maplestory.player.inventory.current_inventory_list.get(Maplestory.player.inventory.move_index);
								if(update.getType().equals("Consume")) {
									//Check if selected item already exists in Quick Slot
									for(int i=0;i<8;i++) {
										if(i != index) {
											Item original = Icon[i].Get_Item();
											//if exists, set that quick slot empty
											if(original != null) {
												if(original.getItemCode() == update.getItemCode()) {
													Icon[i].set_Item(null);
												}
											}
										}
									}
									Icon[index].set_Item(update);
								}
							}
							//Moving Item from Quick Slot
							else if(Maplestory.quick_slot.move_index != -1) {
								Music DragEnd_Music = new Music("DragEnd.wav", 1);
								DragEnd_Music.play();
								//Same Slot
								if(Icon[index].Get_Index() == Maplestory.quick_slot.move_index) {
									Stage.move = null;
								}
								//Different Slot
								else {
									Stage.move = null;
									Item temp = Icon[index].Get_Item();
									Icon[index].set_Item(Maplestory.quick_slot.Icon[Maplestory.quick_slot.move_index].Get_Item());
									Maplestory.quick_slot.Icon[Maplestory.quick_slot.move_index].set_Item(temp);
								}
							}
						}
					}
				}
			});
			add(Icon[i]);
		}
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					if(Stage.move != null) {
						Music DragEnd_Music = new Music("DragEnd.wav", 1);
						DragEnd_Music.play();
						Stage.move = null;
					}
				}
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}
}
