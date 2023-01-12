package maplestory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class UI_KeyConfig extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected boolean isOpen = false;
	protected boolean isChanged = false;
	
	private int mouseX, mouseY;
	
	protected UI_KeyConfig_Slot[] Slot;
	protected int[] slotIndex;
	
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
		slotIndex = new int[223];
		
		for(int i=0;i<223;i++) {
			slotIndex[i] = -1;
		}
		
		Slot[0] = new UI_KeyConfig_Slot(KeyEvent.VK_F1, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F1]);
		slotIndex[KeyEvent.VK_F1] = 0;
		Slot[1] = new UI_KeyConfig_Slot(KeyEvent.VK_F2, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F2]);
		slotIndex[KeyEvent.VK_F2] = 1;
		Slot[2] = new UI_KeyConfig_Slot(KeyEvent.VK_F3, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F3]);
		slotIndex[KeyEvent.VK_F3] = 2;
		Slot[3] = new UI_KeyConfig_Slot(KeyEvent.VK_F4, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F4]);
		slotIndex[KeyEvent.VK_F4] = 3;
		Slot[4] = new UI_KeyConfig_Slot(KeyEvent.VK_F5, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F5]);
		slotIndex[KeyEvent.VK_F5] = 4;
		Slot[5] = new UI_KeyConfig_Slot(KeyEvent.VK_F6, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F6]);
		slotIndex[KeyEvent.VK_F6] = 5;
		Slot[6] = new UI_KeyConfig_Slot(KeyEvent.VK_F7, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F7]);
		slotIndex[KeyEvent.VK_F7] = 6;
		Slot[7] = new UI_KeyConfig_Slot(KeyEvent.VK_F8, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F8]);
		slotIndex[KeyEvent.VK_F8] = 7;
		Slot[8] = new UI_KeyConfig_Slot(KeyEvent.VK_F9, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F9]);
		slotIndex[KeyEvent.VK_F9] = 8;
		Slot[9] = new UI_KeyConfig_Slot(KeyEvent.VK_F10, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F10]);
		slotIndex[KeyEvent.VK_F10] = 9;
		Slot[10] = new UI_KeyConfig_Slot(KeyEvent.VK_F11, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F11]);
		slotIndex[KeyEvent.VK_F11] = 10;
		Slot[11] = new UI_KeyConfig_Slot(KeyEvent.VK_F12, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F12]);
		slotIndex[KeyEvent.VK_F12] = 11;

		Slot[12] = new UI_KeyConfig_Slot(KeyEvent.VK_INSERT, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_INSERT]);
		slotIndex[KeyEvent.VK_INSERT] = 12;
		Slot[13] = new UI_KeyConfig_Slot(KeyEvent.VK_HOME, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_HOME]);
		slotIndex[KeyEvent.VK_HOME] = 13;
		Slot[14] = new UI_KeyConfig_Slot(KeyEvent.VK_PAGE_UP, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_PAGE_UP]);
		slotIndex[KeyEvent.VK_PAGE_UP] = 14;
		Slot[15] = new UI_KeyConfig_Slot(KeyEvent.VK_DELETE, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_DELETE]);
		slotIndex[KeyEvent.VK_DELETE] = 15;
		Slot[16] = new UI_KeyConfig_Slot(KeyEvent.VK_END, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_END]);
		slotIndex[KeyEvent.VK_END] = 16;
		Slot[17] = new UI_KeyConfig_Slot(KeyEvent.VK_PAGE_DOWN, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_PAGE_DOWN]);
		slotIndex[KeyEvent.VK_PAGE_DOWN] = 17;
		
		Slot[18] = new UI_KeyConfig_Slot(KeyEvent.VK_BACK_QUOTE, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_BACK_QUOTE]);
		slotIndex[KeyEvent.VK_BACK_QUOTE] = 18;
		Slot[19] = new UI_KeyConfig_Slot(KeyEvent.VK_1, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_1]);
		slotIndex[KeyEvent.VK_1] = 19;
		Slot[20] = new UI_KeyConfig_Slot(KeyEvent.VK_2, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_2]);
		slotIndex[KeyEvent.VK_2] = 20;
		Slot[21] = new UI_KeyConfig_Slot(KeyEvent.VK_3, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_3]);
		slotIndex[KeyEvent.VK_3] = 21;
		Slot[22] = new UI_KeyConfig_Slot(KeyEvent.VK_4, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_4]);
		slotIndex[KeyEvent.VK_4] = 22;
		Slot[23] = new UI_KeyConfig_Slot(KeyEvent.VK_5, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_5]);
		slotIndex[KeyEvent.VK_5] = 23;
		Slot[24] = new UI_KeyConfig_Slot(KeyEvent.VK_6, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_6]);
		slotIndex[KeyEvent.VK_6] = 24;
		Slot[25] = new UI_KeyConfig_Slot(KeyEvent.VK_7, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_7]);
		slotIndex[KeyEvent.VK_7] = 25;
		Slot[26] = new UI_KeyConfig_Slot(KeyEvent.VK_8, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_8]);
		slotIndex[KeyEvent.VK_8] = 26;
		Slot[27] = new UI_KeyConfig_Slot(KeyEvent.VK_9, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_9]);
		slotIndex[KeyEvent.VK_9] = 27;
		Slot[28] = new UI_KeyConfig_Slot(KeyEvent.VK_0, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_0]);
		slotIndex[KeyEvent.VK_0] = 28;
		Slot[29] = new UI_KeyConfig_Slot(KeyEvent.VK_MINUS, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_MINUS]);
		slotIndex[KeyEvent.VK_MINUS] = 29;
		Slot[30] = new UI_KeyConfig_Slot(KeyEvent.VK_EQUALS, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_EQUALS]);
		slotIndex[KeyEvent.VK_EQUALS] = 30;

		Slot[31] = new UI_KeyConfig_Slot(KeyEvent.VK_Q, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_Q]);
		slotIndex[KeyEvent.VK_Q] = 31;
		Slot[32] = new UI_KeyConfig_Slot(KeyEvent.VK_W, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_W]);
		slotIndex[KeyEvent.VK_W] = 32;
		Slot[33] = new UI_KeyConfig_Slot(KeyEvent.VK_E, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_E]);
		slotIndex[KeyEvent.VK_E] = 33;
		Slot[34] = new UI_KeyConfig_Slot(KeyEvent.VK_R, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_R]);
		slotIndex[KeyEvent.VK_R] = 34;
		Slot[35] = new UI_KeyConfig_Slot(KeyEvent.VK_T, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_T]);
		slotIndex[KeyEvent.VK_T] = 35;
		Slot[36] = new UI_KeyConfig_Slot(KeyEvent.VK_Y, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_Y]);
		slotIndex[KeyEvent.VK_Y] = 36;
		Slot[37] = new UI_KeyConfig_Slot(KeyEvent.VK_U, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_U]);
		slotIndex[KeyEvent.VK_U] = 37;
		Slot[38] = new UI_KeyConfig_Slot(KeyEvent.VK_I, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_I]
				, Maplestory.images.KeySetting_Inventory);
		slotIndex[KeyEvent.VK_I] = 38;
		Slot[39] = new UI_KeyConfig_Slot(KeyEvent.VK_O, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_O]);
		slotIndex[KeyEvent.VK_O] = 39;
		Slot[40] = new UI_KeyConfig_Slot(KeyEvent.VK_P, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_P]);
		slotIndex[KeyEvent.VK_P] = 40;
		Slot[41] = new UI_KeyConfig_Slot(KeyEvent.VK_OPEN_BRACKET, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_OPEN_BRACKET]);
		slotIndex[KeyEvent.VK_OPEN_BRACKET] = 41;
		Slot[42] = new UI_KeyConfig_Slot(KeyEvent.VK_CLOSE_BRACKET, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_CLOSE_BRACKET]);
		slotIndex[KeyEvent.VK_CLOSE_BRACKET] = 42;
		Slot[43] = new UI_KeyConfig_Slot(KeyEvent.VK_BACK_SLASH, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_BACK_SLASH]
				, Maplestory.images.KeySetting_KeyConfig);
		slotIndex[KeyEvent.VK_BACK_SLASH] = 43;

		Slot[44] = new UI_KeyConfig_Slot(KeyEvent.VK_A, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_A]);
		slotIndex[KeyEvent.VK_A] = 44;
		Slot[45] = new UI_KeyConfig_Slot(KeyEvent.VK_S, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_S]);
		slotIndex[KeyEvent.VK_S] = 45;
		Slot[46] = new UI_KeyConfig_Slot(KeyEvent.VK_D, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_D]);
		slotIndex[KeyEvent.VK_D] = 46;
		Slot[47] = new UI_KeyConfig_Slot(KeyEvent.VK_F, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_F]);
		slotIndex[KeyEvent.VK_F] = 47;
		Slot[48] = new UI_KeyConfig_Slot(KeyEvent.VK_G, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_G]);
		slotIndex[KeyEvent.VK_G] = 48;
		Slot[49] = new UI_KeyConfig_Slot(KeyEvent.VK_H, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_H]);
		slotIndex[KeyEvent.VK_H] = 49;
		Slot[50] = new UI_KeyConfig_Slot(KeyEvent.VK_J, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_J]);
		slotIndex[KeyEvent.VK_J] = 50;
		Slot[51] = new UI_KeyConfig_Slot(KeyEvent.VK_K, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_K]);
		slotIndex[KeyEvent.VK_K] = 51;
		Slot[52] = new UI_KeyConfig_Slot(KeyEvent.VK_L, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_L]);
		slotIndex[KeyEvent.VK_L] = 52;
		Slot[53] = new UI_KeyConfig_Slot(KeyEvent.VK_SEMICOLON, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_SEMICOLON]);
		slotIndex[KeyEvent.VK_SEMICOLON] = 53;
		Slot[54] = new UI_KeyConfig_Slot(KeyEvent.VK_QUOTE, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_QUOTE]);
		slotIndex[KeyEvent.VK_QUOTE] = 54;

		Slot[55] = new UI_KeyConfig_Slot(KeyEvent.VK_SHIFT, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_SHIFT]
				, Maplestory.images.KeySetting_Skill);
		slotIndex[KeyEvent.VK_SHIFT] = 55;
		Slot[56] = new UI_KeyConfig_Slot(KeyEvent.VK_Z, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_Z]
				, Maplestory.images.KeySetting_PickUp);
		slotIndex[KeyEvent.VK_Z] = 56;
		Slot[57] = new UI_KeyConfig_Slot(KeyEvent.VK_X, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_X]);
		slotIndex[KeyEvent.VK_X] = 57;
		Slot[58] = new UI_KeyConfig_Slot(KeyEvent.VK_C, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_C]);
		slotIndex[KeyEvent.VK_C] = 58;
		Slot[59] = new UI_KeyConfig_Slot(KeyEvent.VK_V, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_V]);
		slotIndex[KeyEvent.VK_V] = 59;
		Slot[60] = new UI_KeyConfig_Slot(KeyEvent.VK_B, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_B]);
		slotIndex[KeyEvent.VK_B] = 60;
		Slot[61] = new UI_KeyConfig_Slot(KeyEvent.VK_N, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_N]);
		slotIndex[KeyEvent.VK_N] = 61;
		Slot[62] = new UI_KeyConfig_Slot(KeyEvent.VK_M, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_M]
				, Maplestory.images.KeySetting_Minimap);
		slotIndex[KeyEvent.VK_M] = 62;
		Slot[63] = new UI_KeyConfig_Slot(KeyEvent.VK_COMMA, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_COMMA]);
		slotIndex[KeyEvent.VK_COMMA] = 63;
		Slot[64] = new UI_KeyConfig_Slot(KeyEvent.VK_PERIOD, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_PERIOD]);
		slotIndex[KeyEvent.VK_PERIOD] = 64;

		Slot[65] = new UI_KeyConfig_Slot(KeyEvent.VK_CONTROL, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_CONTROL]
				, Maplestory.images.KeySetting_Attack);
		slotIndex[KeyEvent.VK_CONTROL] = 65;
		Slot[66] = new UI_KeyConfig_Slot(KeyEvent.VK_SPACE, Maplestory.keyConfig.actionMapKeyArray[KeyEvent.VK_SPACE]
				, Maplestory.images.KeySetting_NPC);
		slotIndex[KeyEvent.VK_SPACE] = 66;
		
		for(int i=67;i<121;i++) {
			Slot[i] = new UI_KeyConfig_Slot(-1, "");
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
						if(Stage.move == null) {
							Item item = Slot[index].getItem();
							if(item != null) {
								Music DragStart_Music = new Music("DragStart.wav", 1);
								DragStart_Music.play();
								Stage.move = Slot[index].item.getRawIcon();
								Maplestory.player.inventory.move_index = -1;
								Maplestory.ui_quick_slot.move_index = Slot[index].quickslotindex;
								Maplestory.ui_keySetting.move_index = index;
							}
							else if(item == null) {
								String actionMapKey = Slot[index].actionMapKey;
								if(!actionMapKey.equals("")) {
									Music DragStart_Music = new Music("DragStart.wav", 1);
									DragStart_Music.play();
									Stage.move = Slot[index].actionImage;
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
								Stage.move = null;
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
												Stage.move = Slot[index].actionImage;
												Maplestory.player.inventory.move_index = -1;
												Maplestory.ui_quick_slot.move_index = -1;
												Maplestory.ui_keySetting.move_index = moveSlotToBottom(Slot[index]);
											}
										}
										Slot[index].setItem(update);
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
									Stage.move = null;
									Maplestory.ui_quick_slot.move_index = -1;
									Maplestory.ui_keySetting.move_index = -1;
								}
								//Different Slot
								else {
									Stage.move = null;
									Item srcItem = Slot[move_index].item, dstItem = Slot[index].item;
									String srcKey = Slot[move_index].actionMapKey, dstKey = Slot[index].actionMapKey;
									ImageIcon srcImage = Slot[move_index].actionImage, dstImage = Slot[index].actionImage;
									
									if(srcItem != null && dstItem != null) {
										Item temp = srcItem;
										Slot[move_index].item = dstItem;
										Slot[index].item = temp;
										
										Stage.move = Slot[move_index].item.getRawIcon();
										Maplestory.player.inventory.move_index = -1;
										Maplestory.ui_quick_slot.move_index = Slot[move_index].quickslotindex;
										Maplestory.ui_keySetting.move_index = move_index;
									}
									else if(srcItem != null && dstItem == null) {
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
											Stage.move = Slot[move_index].actionImage;
											Maplestory.player.inventory.move_index = -1;
											Maplestory.ui_quick_slot.move_index = Slot[move_index].quickslotindex;;
											Maplestory.ui_keySetting.move_index = move_index; 
										}
									}
									else if(srcItem == null && dstItem != null) {
										Slot[move_index].item = dstItem;
										Slot[move_index].actionMapKey = "ItemUse";
										Slot[move_index].actionImage = null;
										
										Slot[index].item = null;
										Slot[index].actionMapKey = srcKey;
										Slot[index].actionImage= srcImage;
										
										Stage.move = Slot[move_index].item.getRawIcon();
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
											Stage.move = Slot[move_index].actionImage;
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
						Stage.info = data.getInfo();
						Stage.info_x = getMousePosition().x + getLocation().x;
						Stage.info_y = getMousePosition().y + getLocation().y;
					}
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					Stage.info = null;
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
		
		if(Stage.move != null && Maplestory.ui_keySetting.move_index != -1) {
			Stage.move = null;
			move_index = -1;
		}
		if(Stage.info != null) {
			Stage.info = null;
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
		for(int i=0;i<121;i++) {
			int keyCode = Slot[i].keyCode;
			if(keyCode != -1) {
				String actionMapKey = Slot[i].actionMapKey;
				Maplestory.keyConfig.actionMapKeyArray[keyCode] = actionMapKey;
				if(actionMapKey.equals("ItemUse")) {
					Maplestory.keyConfig.actionMapKeyArray[keyCode] = "ItemUse" + keyCode;
					Maplestory.keyConfig.itemUse[keyCode].setValues(Slot[i].getItem().getItemCode(), Slot[i].getItem().getType());
				}
	
				Slot[i].tempItem = null;
				Slot[i].tempKey = "";
				Slot[i].tempImage = null;
			}
		}
		
		Maplestory.keyConfig.setKeyBoard();
		isChanged = false;
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
