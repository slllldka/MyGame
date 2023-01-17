package maplestory;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class UI_Quick_Slot extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected UI_KeyConfig_Slot[] quickSlot = new UI_KeyConfig_Slot[8];
	protected int move_index = -1;
	protected int selectedIndex = -1;
	
	public UI_Quick_Slot() {
		setBounds(648, 455, 151, 80);
		setIcon(Maplestory.images.QuickSlotImg);
		for(int i=0;i<8;i++) {
			quickSlot[i] = new UI_KeyConfig_Slot(i);
			quickSlot[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e)) {
						UI_KeyConfig_Slot source = (UI_KeyConfig_Slot)e.getSource();
						int index = source.getIndex();
						int quickslotindex = source.getQuickSlotIndex();
						
						if(getParent() == Maplestory.ui_notice) {
							selectedIndex = quickslotindex;
							quickSlot[quickslotindex].requestFocus();
							return;
						}
						
						//Not Moving Key
						if(Map.move == null) {
							Item item = Maplestory.ui_keySetting.Slot[index].getItem();
							if(item != null) {
								Music DragStart_Music = new Music("DragStart.wav", 1);
								DragStart_Music.play();
								Map.move = Maplestory.ui_keySetting.Slot[index].item.getRawIcon();
								Maplestory.player.inventory.move_index = -1;
								Maplestory.ui_quick_slot.move_index = quickslotindex;
								Maplestory.ui_keySetting.move_index = index;
							}
							else if(item == null) {
								String actionMapKey = Maplestory.ui_keySetting.Slot[index].actionMapKey;
								if(!actionMapKey.equals("")) {
									Music DragStart_Music = new Music("DragStart.wav", 1);
									DragStart_Music.play();
									Map.move = Maplestory.ui_keySetting.Slot[index].actionImage;
									Maplestory.player.inventory.move_index = -1;
									Maplestory.ui_quick_slot.move_index = quickslotindex;
									Maplestory.ui_keySetting.move_index = index;
								}
							}
						}
						//Moving Key
						else {
							//Moving Item from Inventory
							if(Maplestory.player.inventory.move_index != -1) {
								Music DragEnd_Music = new Music("DragEnd.wav", 1);
								DragEnd_Music.play();
								Map.move = null;
								if(index < 67) {
									Item update = Maplestory.player.inventory.current_inventory_list.get(Maplestory.player.inventory.move_index);
									if(update.getType().equals("Consume")) {
										//Check if selected item already exists in KeySetting
										for(int i=0;i<67;i++) {
											if(i != index) {
												Item original = Maplestory.ui_keySetting.Slot[i].getItem();
												//if exists, set that quick slot empty
												if(original != null) {
													if(original.getItemCode() == update.getItemCode()) {
														Maplestory.ui_keySetting.Slot[i].setItem(null);
														Maplestory.ui_keySetting.Slot[i].actionMapKey = "";
													}
												}
											}
										}
										
										if(!Maplestory.ui_keySetting.Slot[index].actionMapKey.equals("")) {
											if(Maplestory.ui_keySetting.Slot[index].actionMapKey.equals("ItemUse")) {
											}
											else if(Maplestory.ui_keySetting.Slot[index].actionMapKey.equals("Skill")) {
											}
											else {
												Map.move = Maplestory.ui_keySetting.Slot[index].actionImage;
												Maplestory.player.inventory.move_index = -1;
												Maplestory.ui_quick_slot.move_index = -1;
												Maplestory.ui_keySetting.move_index = Maplestory.ui_keySetting.moveSlotToBottom(Maplestory.ui_keySetting.Slot[index]);
											}
										}
										Maplestory.ui_keySetting.Slot[index].setItem(update);
										Maplestory.ui_keySetting.Slot[index].actionMapKey = "ItemUse";
										Maplestory.ui_keySetting.Slot[index].actionImage = null;
									}
									
									if(Maplestory.ui_keySetting.isOpen) {
										Maplestory.ui_keySetting.isChanged = true;
									}
									else {
										Maplestory.ui_keySetting.save();
									}
								}
							}
							//Moving Key from KeyConfig
							else if(Maplestory.ui_keySetting.move_index != -1) {
								Music DragEnd_Music = new Music("DragEnd.wav", 1);
								DragEnd_Music.play();
								
								//Same Slot
								if(index == Maplestory.ui_keySetting.move_index) {
									Map.move = null;
									Maplestory.ui_quick_slot.move_index = -1;
									Maplestory.ui_keySetting.move_index = -1;
								}
								//Different Slot
								else {
									Map.move = null;
									Item srcItem = Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].item;
									Item dstItem = Maplestory.ui_keySetting.Slot[index].item;
									String srcKey = Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].actionMapKey;
									String dstKey = Maplestory.ui_keySetting.Slot[index].actionMapKey;
									ImageIcon srcImage = Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].actionImage;
									ImageIcon dstImage = Maplestory.ui_keySetting.Slot[index].actionImage;
									
									if(srcItem != null && dstItem != null) {
										Item temp = srcItem;
										Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].item = dstItem;
										Maplestory.ui_keySetting.Slot[index].item = temp;
										
										Map.move = Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].item.getRawIcon();
										Maplestory.player.inventory.move_index = -1;
										Maplestory.ui_quick_slot.move_index = move_index;
										Maplestory.ui_keySetting.move_index = Maplestory.ui_keySetting.move_index;
									}
									else if(srcItem != null && dstItem == null) {
										Item temp = srcItem;
										
										Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].item = null;
										Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].actionMapKey = dstKey;
										Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].actionImage = dstImage;
										
										Maplestory.ui_keySetting.Slot[index].item = temp;
										Maplestory.ui_keySetting.Slot[index].actionMapKey = "ItemUse";
										Maplestory.ui_keySetting.Slot[index].actionImage= null;
										
										if(Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].actionMapKey.equals("")) {
											Maplestory.ui_quick_slot.move_index = -1;
											Maplestory.ui_keySetting.move_index = -1;
										}
										else {
											Map.move = Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].actionImage;
											Maplestory.player.inventory.move_index = -1;
											Maplestory.ui_quick_slot.move_index = move_index;
											Maplestory.ui_keySetting.move_index = Maplestory.ui_keySetting.move_index; 
										}
									}
									else if(srcItem == null && dstItem != null) {
										Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].item = dstItem;
										Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].actionMapKey = "ItemUse";
										Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].actionImage = null;
										
										Maplestory.ui_keySetting.Slot[index].item = null;
										Maplestory.ui_keySetting.Slot[index].actionMapKey = srcKey;
										Maplestory.ui_keySetting.Slot[index].actionImage= srcImage;
										
										Map.move = Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].item.getRawIcon();
										Maplestory.player.inventory.move_index = -1;
										Maplestory.ui_quick_slot.move_index = move_index;
										Maplestory.ui_keySetting.move_index = Maplestory.ui_keySetting.move_index;
									}
									else {
										String tempKey = srcKey;
										ImageIcon tempImage = srcImage;
										
										Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].item = null;
										Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].actionMapKey = dstKey;
										Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].actionImage = dstImage;
										
										Maplestory.ui_keySetting.Slot[index].item = null;
										Maplestory.ui_keySetting.Slot[index].actionMapKey = tempKey;
										Maplestory.ui_keySetting.Slot[index].actionImage = tempImage;
										
										if(Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].actionMapKey.equals("")) {
											Maplestory.ui_quick_slot.move_index = -1;
											Maplestory.ui_keySetting.move_index = -1;
										}
										else {
											Map.move = Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index].actionImage;
											Maplestory.player.inventory.move_index = -1;
											Maplestory.ui_quick_slot.move_index = move_index;
											Maplestory.ui_keySetting.move_index = Maplestory.ui_keySetting.move_index; 
										}
									}
									
									if(Maplestory.ui_keySetting.isOpen) {
										Maplestory.ui_keySetting.isChanged = true;
									}
									else {
										Maplestory.ui_keySetting.save();
									}
								}
							}
						}
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					UI_KeyConfig_Slot source = (UI_KeyConfig_Slot)e.getSource();
					Item data = source.getItem();
					if(data != null) {
						Map.info = data.getInfo();
						Map.info_x = getMousePosition().x + getLocation().x;
						Map.info_y = getMousePosition().y + getLocation().y;
					}
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					Map.info = null;
				}
			});
			quickSlot[i].addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(selectedIndex != -1) {
						if(Maplestory.ui_keySetting.slotIndex[e.getKeyCode()] != -1) {
							quickSlot[selectedIndex].setQuickSlotKey(Maplestory.ui_keySetting
									.Slot[Maplestory.ui_keySetting.slotIndex[e.getKeyCode()]]);
						}
					}
				}
			});
			
			add(quickSlot[i]);
		}
		
		quickSlot[0].setQuickSlotKey(Maplestory.ui_keySetting.Slot[55]);
		quickSlot[1].setQuickSlotKey(Maplestory.ui_keySetting.Slot[12]);
		quickSlot[2].setQuickSlotKey(Maplestory.ui_keySetting.Slot[13]);
		quickSlot[3].setQuickSlotKey(Maplestory.ui_keySetting.Slot[14]);
		quickSlot[4].setQuickSlotKey(Maplestory.ui_keySetting.Slot[65]);
		quickSlot[5].setQuickSlotKey(Maplestory.ui_keySetting.Slot[15]);
		quickSlot[6].setQuickSlotKey(Maplestory.ui_keySetting.Slot[16]);
		quickSlot[7].setQuickSlotKey(Maplestory.ui_keySetting.Slot[17]);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}
}
