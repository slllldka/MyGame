package maplestory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class UI_KeyConfig extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static int ZOrder = 3;
	
	protected boolean isOpen = false;
	protected boolean isChanged = false;
	
	private int mouseX, mouseY;
	
	protected UI_KeyConfig_Slot[] Slot;
	protected static int[] slotIndex;
	
	static {
		slotIndex = new int[223];
		
		for(int i=0;i<223;i++) {
			slotIndex[i] = -1;
		}
		
		slotIndex[KeyEvent.VK_F1] = 0;
		slotIndex[KeyEvent.VK_F2] = 1;
		slotIndex[KeyEvent.VK_F3] = 2;
		slotIndex[KeyEvent.VK_F4] = 3;
		slotIndex[KeyEvent.VK_F5] = 4;
		slotIndex[KeyEvent.VK_F6] = 5;
		slotIndex[KeyEvent.VK_F7] = 6;
		slotIndex[KeyEvent.VK_F8] = 7;
		slotIndex[KeyEvent.VK_F9] = 8;
		slotIndex[KeyEvent.VK_F10] = 9;
		slotIndex[KeyEvent.VK_F11] = 10;
		slotIndex[KeyEvent.VK_F12] = 11;

		slotIndex[KeyEvent.VK_INSERT] = 12;
		slotIndex[KeyEvent.VK_HOME] = 13;
		slotIndex[KeyEvent.VK_PAGE_UP] = 14;
		slotIndex[KeyEvent.VK_DELETE] = 15;
		slotIndex[KeyEvent.VK_END] = 16;
		slotIndex[KeyEvent.VK_PAGE_DOWN] = 17;
		
		slotIndex[KeyEvent.VK_BACK_QUOTE] = 18;
		slotIndex[KeyEvent.VK_1] = 19;
		slotIndex[KeyEvent.VK_2] = 20;
		slotIndex[KeyEvent.VK_3] = 21;
		slotIndex[KeyEvent.VK_4] = 22;
		slotIndex[KeyEvent.VK_5] = 23;
		slotIndex[KeyEvent.VK_6] = 24;
		slotIndex[KeyEvent.VK_7] = 25;
		slotIndex[KeyEvent.VK_8] = 26;
		slotIndex[KeyEvent.VK_9] = 27;
		slotIndex[KeyEvent.VK_0] = 28;
		slotIndex[KeyEvent.VK_MINUS] = 29;
		slotIndex[KeyEvent.VK_EQUALS] = 30;

		slotIndex[KeyEvent.VK_Q] = 31;
		slotIndex[KeyEvent.VK_W] = 32;
		slotIndex[KeyEvent.VK_E] = 33;
		slotIndex[KeyEvent.VK_R] = 34;
		slotIndex[KeyEvent.VK_T] = 35;
		slotIndex[KeyEvent.VK_Y] = 36;
		slotIndex[KeyEvent.VK_U] = 37;
		slotIndex[KeyEvent.VK_I] = 38;
		slotIndex[KeyEvent.VK_O] = 39;
		slotIndex[KeyEvent.VK_P] = 40;
		slotIndex[KeyEvent.VK_OPEN_BRACKET] = 41;
		slotIndex[KeyEvent.VK_CLOSE_BRACKET] = 42;
		slotIndex[KeyEvent.VK_BACK_SLASH] = 43;

		slotIndex[KeyEvent.VK_A] = 44;
		slotIndex[KeyEvent.VK_S] = 45;
		slotIndex[KeyEvent.VK_D] = 46;
		slotIndex[KeyEvent.VK_F] = 47;
		slotIndex[KeyEvent.VK_G] = 48;
		slotIndex[KeyEvent.VK_H] = 49;
		slotIndex[KeyEvent.VK_J] = 50;
		slotIndex[KeyEvent.VK_K] = 51;
		slotIndex[KeyEvent.VK_L] = 52;
		slotIndex[KeyEvent.VK_SEMICOLON] = 53;
		slotIndex[KeyEvent.VK_QUOTE] = 54;

		slotIndex[KeyEvent.VK_SHIFT] = 55;
		slotIndex[KeyEvent.VK_Z] = 56;
		slotIndex[KeyEvent.VK_X] = 57;
		slotIndex[KeyEvent.VK_C] = 58;
		slotIndex[KeyEvent.VK_V] = 59;
		slotIndex[KeyEvent.VK_B] = 60;
		slotIndex[KeyEvent.VK_N] = 61;
		slotIndex[KeyEvent.VK_M] = 62;
		slotIndex[KeyEvent.VK_COMMA] = 63;
		slotIndex[KeyEvent.VK_PERIOD] = 64;

		slotIndex[KeyEvent.VK_CONTROL] = 65;
		slotIndex[KeyEvent.VK_SPACE] = 66;
	}
	
	protected JLabel bt_default, bt_delete, bt_quickslotchange, bt_ok, bt_cancel;
	
	protected boolean default_entered = false, default_pressed = false;
	protected boolean delete_entered = false, delete_pressed = false;
	protected boolean quickslotchange_entered = false, quickslotchange_pressed = false;
	protected boolean ok_entered = false, ok_pressed = false;
	protected boolean cancel_entered = false, cancel_pressed = false;
	
	protected int move_index = -1;
	
	public UI_KeyConfig() {
		setBounds(89, 78, 622, 374);
		
		makeSlots();
		makeButtons();
		setScreenMouseEvents();
		
		setVisible(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(Maplestory.images.KeySetting_BackGround1.getImage(), 0, 0, this);
		g.drawImage(Maplestory.images.KeySetting_BackGround2.getImage(), 6, 23, this);
		g.drawImage(Maplestory.images.KeySetting_BackGround3.getImage(), 10, 28, this);
		
		g.drawImage(Maplestory.images.KeySetting_Jump.getImage(), 119, 198, this);
		g.drawImage(Maplestory.images.KeySetting_quickslotAlt.getImage(), 123, 202, this);
		
		if(isChanged) {
			bt_default.setEnabled(true);
		}
		else {
			bt_default.setEnabled(false);
		}
	}
	
	public void makeSlots() {
		Slot = new UI_KeyConfig_Slot[121];

		for(int i=0;i<223;i++) {
			if(slotIndex[i] != -1) {
				Slot[slotIndex[i]] = new UI_KeyConfig_Slot(i, "");
			}
		}
		for(int i=67;i<121;i++) {
			Slot[i] = new UI_KeyConfig_Slot(-i, "");
		}
		
		String query = "";
		PreparedStatement pstat = null;
		ResultSet resultSet = null;
		
		try {
			query = "SELECT * FROM KEYCONFIG WHERE NAME = ?";
			pstat = Maplestory.connection.prepareStatement(query);
			pstat.setString(1, Maplestory.player.name);
			resultSet = pstat.executeQuery();
			
			String actionMapKey = "";
			int keyCode = -1, index = -1;
			while(resultSet.next()) {
				keyCode = resultSet.getInt("KEYCODE");
				index = keyCode > -1 ? slotIndex[keyCode] : -keyCode;
				actionMapKey = resultSet.getString("ACTIONMAPKEY");
				if(actionMapKey.equals("ItemUse")) {
					Slot[index] = new UI_KeyConfig_Slot(keyCode, actionMapKey);
					Slot[index].item = Item.getItem(resultSet.getString("ITEMTYPE"), resultSet.getInt("ITEMCODE"), 0);
				}
				else if(actionMapKey.equals("Inventory")) {
					Slot[index] = new UI_KeyConfig_Slot(keyCode, actionMapKey, Maplestory.images.KeySetting_Inventory);
					
				}
				else if(actionMapKey.equals("KeyConfigAction")) {
					Slot[index] = new UI_KeyConfig_Slot(keyCode, actionMapKey, Maplestory.images.KeySetting_KeyConfig);
					
				}
				else if(actionMapKey.equals("ShiftPress")) {
					Slot[index] = new UI_KeyConfig_Slot(keyCode, actionMapKey, Maplestory.images.KeySetting_Skill);
					
				}
				else if(actionMapKey.equals("PickUp")) {
					Slot[index] = new UI_KeyConfig_Slot(keyCode, actionMapKey, Maplestory.images.KeySetting_PickUp);
					
				}
				else if(actionMapKey.equals("Minimap")) {
					Slot[index] = new UI_KeyConfig_Slot(keyCode, actionMapKey, Maplestory.images.KeySetting_Minimap);
					
				}
				else if(actionMapKey.equals("CtrlPress")) {
					Slot[index] = new UI_KeyConfig_Slot(keyCode, actionMapKey, Maplestory.images.KeySetting_Attack);
					
				}
				else if(actionMapKey.equals("NPC")) {
					Slot[index] = new UI_KeyConfig_Slot(keyCode, actionMapKey, Maplestory.images.KeySetting_NPC);
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<121;i++) {
			Slot[i].setIndex(i);
			Slot[i].setKeyImage();
			
			if(i >= 0 && i < 4) {
				Slot[i].setLocation(79 + 34 * (i - 0), 28);
			}
			else if(i >= 4 && i < 8) {
				Slot[i].setLocation(223 + 34 * (i - 4), 28);
			}
			else if(i >= 8 && i < 12) {
				Slot[i].setLocation(367 + 34 * (i - 8), 28);
			}
			else if(i >= 12 && i < 15) {
				Slot[i].setLocation(511 + 34 * (i - 12), 66);
			}
			else if(i >= 15 && i < 18) {
				Slot[i].setLocation(511 + 34 * (i - 15), 99);
			}
			else if(i >= 18 && i < 31) {
				Slot[i].setLocation(11 + 34 * (i - 18), 66);
			}
			else if(i >= 31 && i < 44) {
				Slot[i].setLocation(61 + 34 * (i - 31), 99);
			}
			else if(i >= 44 && i < 55) {
				Slot[i].setLocation(78 + 34 * (i - 44), 132);
			}
			else if(i == 55) {
				Slot[i].setLocation(35, 165);
			}
			else if(i >= 56 && i < 65) {
				Slot[i].setLocation(95 + 34 * (i - 56), 165);
			}
			else if(i == 65) {
				Slot[i].setLocation(18, 198);
			}
			else if(i == 66) {
				Slot[i].setLocation(226, 198);
			}
			else if(i >= 67 && i < 85) {
				Slot[i].setLocation(6 + 34 * (i - 67), 268);
			}
			else if(i >= 85 && i < 103) {
				Slot[i].setLocation(6 + 34 * (i - 85), 302);
			}
			else if(i >= 103 && i < 121) {
				Slot[i].setLocation(6 + 34 * (i - 103), 336);
			}
			
			Slot[i].setSize(32, 32);
			Slot[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e)) {
						UI_KeyConfig_Slot source = (UI_KeyConfig_Slot)e.getSource();
						int index = source.getIndex();
						//Not Moving Key
						if(Map.move == null) {
							Item item = Slot[index].getItem();
							if(item != null) {
								Music DragStart_Music = new Music("DragStart.wav", 1);
								DragStart_Music.play();
								Map.move = Slot[index].item.getRawIcon();
								Maplestory.player.inventory.move_index = -1;
								Maplestory.ui_quick_slot.move_index = Slot[index].quickslotindex;
								Maplestory.ui_keySetting.move_index = index;
							}
							else if(item == null) {
								String actionMapKey = Slot[index].actionMapKey;
								if(!actionMapKey.equals("")) {
									Music DragStart_Music = new Music("DragStart.wav", 1);
									DragStart_Music.play();
									Map.move = Slot[index].actionImage;
									Maplestory.player.inventory.move_index = -1;
									Maplestory.ui_quick_slot.move_index = Slot[index].quickslotindex;
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
												Item original = Slot[i].getItem();
												//if exists, set that quick slot empty
												if(original != null) {
													if(original.getItemCode() == update.getItemCode()) {
														Slot[i].setItem(null);
														Slot[i].actionMapKey = "";
													}
												}
											}
										}
										
										if(!Slot[index].actionMapKey.equals("")) {
											if(Slot[index].actionMapKey.equals("ItemUse")) {
											}
											else if(Slot[index].actionMapKey.equals("Skill")) {
											}
											else {
												Map.move = Slot[index].actionImage;
												Maplestory.player.inventory.move_index = -1;
												Maplestory.ui_quick_slot.move_index = -1;
												Maplestory.ui_keySetting.move_index = moveSlotToBottom(Slot[index]);
											}
										}
										Slot[index].item = Item.getItem("Consume", update.getItemCode(), 0);
										Slot[index].actionMapKey = "ItemUse";
										Slot[index].actionImage = null;
									}
									isChanged = true;
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
									Item srcItem = Slot[move_index].item, dstItem = Slot[index].item;
									String srcKey = Slot[move_index].actionMapKey, dstKey = Slot[index].actionMapKey;
									ImageIcon srcImage = Slot[move_index].actionImage, dstImage = Slot[index].actionImage;
									
									if(srcItem != null && dstItem != null) {
										Item temp = srcItem;
										Slot[move_index].item = dstItem;
										Slot[index].item = temp;
										
										Map.move = Slot[move_index].item.getRawIcon();
										Maplestory.player.inventory.move_index = -1;
										Maplestory.ui_quick_slot.move_index = Slot[move_index].quickslotindex;
										Maplestory.ui_keySetting.move_index = move_index;
									}
									else if(srcItem != null && dstItem == null) {
										if(index >= 67) {
											Slot[move_index].item = null;
											Slot[move_index].actionMapKey = "";
											Slot[move_index].actionImage = null;
											
											Maplestory.ui_quick_slot.move_index = -1;
											Maplestory.ui_keySetting.move_index = -1;
										}
										else {
											Item temp = srcItem;
											
											Slot[move_index].item = null;
											Slot[move_index].actionMapKey = dstKey;
											Slot[move_index].actionImage = dstImage;
											
											Slot[index].item = temp;
											Slot[index].actionMapKey = "ItemUse";
											Slot[index].actionImage= null;
											
											if(Slot[move_index].actionMapKey.equals("")) {
												Maplestory.ui_quick_slot.move_index = -1;
												Maplestory.ui_keySetting.move_index = -1;
											}
											else {
												Map.move = Slot[move_index].actionImage;
												Maplestory.player.inventory.move_index = -1;
												Maplestory.ui_quick_slot.move_index = Slot[move_index].quickslotindex;;
												Maplestory.ui_keySetting.move_index = move_index; 
											}
										}
									}
									else if(srcItem == null && dstItem != null) {
										Slot[move_index].item = dstItem;
										Slot[move_index].actionMapKey = "ItemUse";
										Slot[move_index].actionImage = null;
										
										Slot[index].item = null;
										Slot[index].actionMapKey = srcKey;
										Slot[index].actionImage= srcImage;
										
										Map.move = Slot[move_index].item.getRawIcon();
										Maplestory.player.inventory.move_index = -1;
										Maplestory.ui_quick_slot.move_index = Slot[move_index].quickslotindex;;
										Maplestory.ui_keySetting.move_index = move_index;
									}
									else {
										String tempKey = srcKey;
										ImageIcon tempImage = srcImage;
										
										Slot[move_index].item = null;
										Slot[move_index].actionMapKey = dstKey;
										Slot[move_index].actionImage = dstImage;
										
										Slot[index].item = null;
										Slot[index].actionMapKey = tempKey;
										Slot[index].actionImage = tempImage;
										
										if(Slot[move_index].actionMapKey.equals("")) {
											Maplestory.ui_quick_slot.move_index = -1;
											Maplestory.ui_keySetting.move_index = -1;
										}
										else {
											Map.move = Slot[move_index].actionImage;
											Maplestory.player.inventory.move_index = -1;
											Maplestory.ui_quick_slot.move_index = Slot[move_index].quickslotindex;
											Maplestory.ui_keySetting.move_index = move_index;
										}
									}
									
									isChanged = true;
								}
							}
						}
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					UI_Quick_Slot_Icon source = (UI_Quick_Slot_Icon)e.getSource();
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
			
			add(Slot[i]);
		}
	}
	
	public int moveSlotToBottom(UI_KeyConfig_Slot victim) {
		for(int i=67;i<121;i++) {
			if(Slot[i].actionMapKey.equals("")) {
				Slot[i].actionMapKey = victim.actionMapKey;
				Slot[i].actionImage = victim.actionImage;
				return i;
			}
		}
		
		return -1;
	}
	
	public void makeButtons() {
		bt_default = new JLabel("");
		bt_default.setBounds(11, 239, 57, 16);
		bt_default.setIcon(Maplestory.images.KeySetting_Default);
		bt_default.setDisabledIcon(Maplestory.images.KeySetting_Default_Disabled);
		bt_default.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!UI_Notice.isOpen && bt_default.isEnabled()) {
					default_entered = true;
					if(!default_pressed) {
						bt_default.setIcon(Maplestory.images.KeySetting_Default_Rollover);
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!UI_Notice.isOpen && bt_default.isEnabled()) {
					default_entered = false;
					if(!default_pressed) {
						bt_default.setIcon(Maplestory.images.KeySetting_Default);
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen && bt_default.isEnabled()) {
					default_pressed = true;
					bt_default.setIcon(Maplestory.images.KeySetting_Default_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen && bt_default.isEnabled()) {
					default_pressed = false;
					bt_default.setIcon(Maplestory.images.KeySetting_Default);
					if(default_entered) {
						notsave();
					}
				}
			}
		});

		bt_delete = new JLabel("");
		bt_delete.setBounds(74, 239, 68, 16);
		bt_delete.setIcon(Maplestory.images.KeySetting_Delete);
		bt_delete.setDisabledIcon(Maplestory.images.KeySetting_Delete_Disabled);
		bt_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					delete_entered = true;
					if(!delete_pressed) {
						bt_delete.setIcon(Maplestory.images.KeySetting_Delete_Rollover);
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					delete_entered = false;
					if(!delete_pressed) {
						bt_delete.setIcon(Maplestory.images.KeySetting_Delete);
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					delete_pressed = true;
					bt_delete.setIcon(Maplestory.images.KeySetting_Delete_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					delete_pressed = false;
					bt_delete.setIcon(Maplestory.images.KeySetting_Delete);
					if(delete_entered) {
						for(int i=0;i<121;i++) {
							Slot[i].item = null;
							Slot[i].actionMapKey = Slot[i].defaultKey;
							Slot[i].actionImage = Slot[i].defaultImage;
						}
					}
					
					isChanged = true;
				}
			}
		});
		
		bt_quickslotchange = new JLabel("");
		bt_quickslotchange.setBounds(148, 239, 93, 16);
		bt_quickslotchange.setIcon(Maplestory.images.KeySetting_QuickSlot);
		bt_quickslotchange.setDisabledIcon(Maplestory.images.KeySetting_QuickSlot_Disabled);
		bt_quickslotchange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					quickslotchange_entered = true;
					if(!quickslotchange_pressed) {
						bt_quickslotchange.setIcon(Maplestory.images.KeySetting_QuickSlot_Rollover);
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					quickslotchange_entered = false;
					if(!quickslotchange_pressed) {
						bt_quickslotchange.setIcon(Maplestory.images.KeySetting_QuickSlot);
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					quickslotchange_pressed = true;
					bt_quickslotchange.setIcon(Maplestory.images.KeySetting_QuickSlot_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					quickslotchange_pressed = false;
					bt_quickslotchange.setIcon(Maplestory.images.KeySetting_QuickSlot);
					//TODO
					Runnable runnable = new Runnable() {

						@Override
						public void run() {
							if(Maplestory.ui_notice.open(UI_Notice.QUICKSLOT_CHANGE, "")) {
								while(UI_Notice.isOpen) {
									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								
								if(UI_Notice.status == UI_Notice.OK) {
									Maplestory.current_stage.remove(Maplestory.ui_quick_slot);
									Maplestory.ui_quick_slot = Maplestory.ui_notice.tempQuickSlot;
									Maplestory.ui_quick_slot.setLocation(648, 455);
									Maplestory.ui_notice.tempQuickSlot = null;
									Maplestory.current_stage.add(Maplestory.ui_quick_slot, 6);
									
									String query = "";
									PreparedStatement pstat = null;
									
									try {
										query = "UPDATE QUICKSLOT SET IDX0 = ?, IDX1 = ?, IDX2 = ?, IDX3 = ?"
												+ ", IDX4 = ?, IDX5 = ?, IDX6 = ?, IDX7 = ?";
										pstat = Maplestory.connection.prepareStatement(query);
										pstat.setInt(1, Maplestory.ui_quick_slot.quickSlot[0].keyCode);
										pstat.setInt(2, Maplestory.ui_quick_slot.quickSlot[1].keyCode);
										pstat.setInt(3, Maplestory.ui_quick_slot.quickSlot[2].keyCode);
										pstat.setInt(4, Maplestory.ui_quick_slot.quickSlot[3].keyCode);
										pstat.setInt(5, Maplestory.ui_quick_slot.quickSlot[4].keyCode);
										pstat.setInt(6, Maplestory.ui_quick_slot.quickSlot[5].keyCode);
										pstat.setInt(7, Maplestory.ui_quick_slot.quickSlot[6].keyCode);
										pstat.setInt(8, Maplestory.ui_quick_slot.quickSlot[7].keyCode);
										
										pstat.executeUpdate();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
							}
						}
					};
					Maplestory.thread_pool.submit(runnable);
				}
			}
		});

		bt_ok = new JLabel("");
		bt_ok.setBounds(526, 239, 40, 16);
		bt_ok.setIcon(Maplestory.images.KeySetting_OK);
		bt_ok.setDisabledIcon(Maplestory.images.KeySetting_OK_Disabled);
		bt_ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					ok_entered = true;
					if(!ok_pressed) {
						bt_ok.setIcon(Maplestory.images.KeySetting_OK_Rollover);
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					ok_entered = false;
					if(!ok_pressed) {
						bt_ok.setIcon(Maplestory.images.KeySetting_OK);
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					ok_pressed = true;
					bt_ok.setIcon(Maplestory.images.KeySetting_OK_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					ok_pressed = false;
					bt_ok.setIcon(Maplestory.images.KeySetting_OK);
					if(ok_entered) {
						save();
						close(false);
					}
				}
			}
		});
		
		bt_cancel = new JLabel("");
		bt_cancel.setBounds(572, 239, 40, 16);
		bt_cancel.setIcon(Maplestory.images.KeySetting_Cancel);
		bt_cancel.setDisabledIcon(Maplestory.images.KeySetting_Cancel_Disabled);
		bt_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					cancel_entered = true;
					if(!cancel_pressed) {
						bt_cancel.setIcon(Maplestory.images.KeySetting_Cancel_Rollover);
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					cancel_entered = false;
					if(!cancel_pressed) {
						bt_cancel.setIcon(Maplestory.images.KeySetting_Cancel);
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					cancel_pressed = true;
					bt_cancel.setIcon(Maplestory.images.KeySetting_Cancel_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					cancel_pressed = false;
					bt_cancel.setIcon(Maplestory.images.KeySetting_Cancel);
					if(cancel_entered) {
						notsave();
						close(false);
					}
				}
			}
		});
		
		add(bt_default);
		add(bt_delete);
		add(bt_quickslotchange);
		add(bt_ok);
		add(bt_cancel);
	}
	
	public void setScreenMouseEvents() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					mouseX = e.getX();
					mouseY = e.getY();
					settoTop();
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					int X = e.getX();
					int Y = e.getY();
					int Screen_X = getX();
					int Screen_Y = getY();
					if(mouseX >= 4 && mouseX <= 620 && mouseY >= 0 && mouseY <= 20) {
						if(Screen_X >= -300 && Screen_X <= 500 && Screen_Y >= 0 && Screen_Y <= 550) {
							int Screen_New_X = X + Screen_X - mouseX;
							int Screen_New_Y = Y + Screen_Y - mouseY;
							if(Screen_New_X >= -300 && Screen_New_X <= 500 && Screen_New_Y >= 0 && Screen_New_Y <= 550) {
								setLocation(Screen_New_X, Screen_New_Y);
							}
						}
					}
				}
			}
		});
	}
	
	public void open() {
		Music MenuUp_Music = new Music("MenuUp.wav", 1);
		MenuUp_Music.play();
		
		for(int i=0;i<121;i++) {
			Slot[i].tempItem = Slot[i].item;
			Slot[i].tempKey = Slot[i].actionMapKey;
			Slot[i].tempImage = Slot[i].actionImage;
		}
		
		setLocation(89, 78);
		setVisible(true);
		settoTop();
		isOpen = true;
		isChanged = false;
	}
	
	public void settoTop() {
		Maplestory.current_stage.setComponentZOrder(this, 1);
	}
	
	public void close(boolean checkSave) {
		Music MenuDown_Music = new Music("MenuDown.wav", 1);
		MenuDown_Music.play();
		
		isOpen = false;
		settoBottom();
		setVisible(false);
		
		if(Map.move != null && Maplestory.ui_keySetting.move_index != -1) {
			Map.move = null;
			move_index = -1;
		}
		if(Map.info != null) {
			Map.info = null;
		}
		
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				if(checkSave) {
					if(Maplestory.ui_notice.open(UI_Notice.WITH_OK | UI_Notice.WITH_CANCEL, "변경사항을 저장하시겠습니까?")) {
						while(UI_Notice.isOpen) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						if(UI_Notice.status == UI_Notice.OK) {
							save();
						}
						else if(UI_Notice.status == UI_Notice.CANCEL) {
							notsave();
						}
					}
				}
			}
			
		};
		if(isChanged) {
			Maplestory.thread_pool.submit(runnable);
		}
	}
	
	public void save() {
		String deleteQuery1 = "DELETE FROM KEYCONFIG WHERE NAME = ? AND KEYCODE = ?";
		String deleteQuery2 = "DELETE FROM KEYCONFIG WHERE NAME = ? AND KEYCODE != ?"
				+ "AND ((ACTIONMAPKEY != ? AND ACTIONMAPKEY = ?) OR "
				+ "(ACTIONMAPKEY = ? AND ACTIONMAPKEY = ? AND ITEMTYPE = ? AND ITEMCODE = ?))";
		String insertQuery = "INSERT INTO KEYCONFIG VALUES (?, ?, ?, ?, ?)";
		PreparedStatement deletePstat1 = null, deletePstat2 = null, insertPstat = null;
		
		try {
			deletePstat1 = Maplestory.connection.prepareStatement(deleteQuery1);
			deletePstat1.setString(1, Maplestory.player.name);
			deletePstat2 = Maplestory.connection.prepareStatement(deleteQuery2);
			deletePstat2.setString(1, Maplestory.player.name);
			deletePstat2.setString(3, "ItemUse");
			deletePstat2.setString(5, "ItemUse");
			insertPstat = Maplestory.connection.prepareStatement(insertQuery);
			insertPstat.setString(1, Maplestory.player.name);
			for(int i=0;i<121;i++) {
				int keyCode = Slot[i].keyCode;
				String actionMapKey = Slot[i].actionMapKey;
			
				deletePstat1.setInt(2, keyCode);
				deletePstat2.setInt(2, keyCode);
				deletePstat2.setString(4, actionMapKey);
				deletePstat2.setString(6, actionMapKey);
				deletePstat2.setString(7, "");
				deletePstat2.setInt(8, -1);
				insertPstat.setInt(2, keyCode);
				insertPstat.setString(3, actionMapKey);
				insertPstat.setString(4, "");
				insertPstat.setInt(5, -1);
			
				if(keyCode > -1) {
					Maplestory.keyConfig.actionMapKeyArray[keyCode] = actionMapKey;
					if(actionMapKey.equals("ItemUse")) {
						Maplestory.keyConfig.actionMapKeyArray[keyCode] = "ItemUse" + keyCode;
						Maplestory.keyConfig.itemUse[keyCode].setValues(Slot[i].getItem().getItemCode(), Slot[i].getItem().getType());
						deletePstat2.setString(7, Slot[i].getItem().getType());
						deletePstat2.setInt(8, Slot[i].getItem().getItemCode());
						insertPstat.setString(4, Slot[i].getItem().getType());
						insertPstat.setInt(5, Slot[i].getItem().getItemCode());
					}
				}
			
				if(!actionMapKey.equals("")) {
					deletePstat1.executeUpdate();
					deletePstat2.executeUpdate();
					insertPstat.executeUpdate();
				}
				else {
					if(Slot[i].tempKey != null) {
						deletePstat1.executeUpdate();
					}
				}
			
				Slot[i].tempItem = null;
				Slot[i].tempKey = "";
				Slot[i].tempImage = null;
			}
		
			Maplestory.keyConfig.setKeyBoard();
			isChanged = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void notsave() {
		for(int i=0;i<121;i++) {
			Slot[i].item = Slot[i].tempItem;
			Slot[i].actionMapKey = Slot[i].tempKey;
			Slot[i].actionImage = Slot[i].tempImage;

			Slot[i].tempItem = Slot[i].item;
			Slot[i].tempKey = Slot[i].actionMapKey;
			Slot[i].tempImage = Slot[i].actionImage;
		}
		isChanged = false;
	}
	
	public void settoBottom() {
		Maplestory.current_stage.setComponentZOrder(this, 4);
	}
}
