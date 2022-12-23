package maplestory;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public abstract class Shop extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected ArrayList<ItemForSale> sell_list = new ArrayList<ItemForSale>();
	protected static ArrayList<ItemForSale> currentSellList;
	protected static ArrayList<Item> currentNullRemovedList = Maplestory.player.inventory.NullRemovedConsumeList;

	protected int current_type; //0: Equip, 1: Consume, 2: Etc, 3: Install, 4: Cash
	protected int current_shop_slot_index = 0;
	
	protected int current_user_slot_index = 0;
	protected int Equip_user_line_index = 0;
	protected int Consume_user_line_index = 0;
	protected int Etc_user_line_index = 0;
	protected int Install_user_line_index = 0;
	protected int Cash_user_line_index = 0;
	
	protected Shop_Shop_Slot[] shop_slot;
	protected Shop_User_Slot[] user_slot;
	
	protected ImageIcon NPCImage;
	
	protected static int selectedShopSlotIndex = -1, selectedUserSlotIndex = -1;
	
	//Scrolling Objects
	private JLabel shop_prev, shop_next, shop_thumb, user_prev, user_next, user_thumb;
	private int shop_scrollbar_xpos = 212, user_scrollbar_xpos = 443;
		
	//Buttons
	private JLabel bt_close, bt_equip, bt_consume, bt_etc, bt_install, bt_cash;
	
	//Control Variables
	private boolean prev_entered = false, prev_pressed = false;
	private boolean next_entered = false, next_pressed = false;
	private boolean thumb_entered = false, thumb_pressed = false;
	private boolean close_entered = false, close_pressed = false;
	protected static boolean isOpen = false;
	private int mouseX, mouseY;
	
	public Shop() {
		setBounds(167, 101, 465, 328);
		makeSlots();
		makeScrollingObjects();
		makeButtons();
		setScreenMouseEvents();
		
		setVisible(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(Maplestory.images.Shop_BackGround1.getImage(), 0, 0, Maplestory.current_stage);
		g.drawImage(Maplestory.images.Shop_BackGround2.getImage(), 6, 5, Maplestory.current_stage);
		g.drawImage(Maplestory.images.Shop_BackGround3.getImage(), 7, 64, Maplestory.current_stage);
		
		if(isVisible()) {
			g.drawImage(NPCImage.getImage(), 60 - NPCImage.getIconWidth() / 2, 45 - NPCImage.getIconHeight() / 2
					, 60 + NPCImage.getIconWidth() / 2, 45 + NPCImage.getIconHeight() / 2
					, NPCImage.getIconWidth(), 0, 0, NPCImage.getIconHeight(), Maplestory.current_stage);
		}
		g.drawImage(Maplestory.player.characterLeftImg.getImage()
				, 297 - Character.CharacterWidth / 2, 45 - Character.CharacterHeight / 2, Maplestory.current_stage);
		
		if(current_type == 0) {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab_Disabled.getImage(), 240, 90, Maplestory.current_stage);
		}
		else {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab.getImage(), 240, 91, Maplestory.current_stage);
		}
		if(current_type == 1) {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab_Disabled.getImage(), 274, 90, Maplestory.current_stage);
		}
		else {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab.getImage(), 274, 91, Maplestory.current_stage);
		}
		if(current_type == 2) {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab_Disabled.getImage(), 308, 90, Maplestory.current_stage);
		}
		else {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab.getImage(), 308, 91, Maplestory.current_stage);
		}
		if(current_type == 3) {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab_Disabled.getImage(), 342, 90, Maplestory.current_stage);
		}
		else {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab.getImage(), 342, 91, Maplestory.current_stage);
		}
		if(current_type == 4) {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab_Disabled.getImage(), 376, 90, Maplestory.current_stage);
		}
		else {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab.getImage(), 376, 91, Maplestory.current_stage);
		}
		
		for (int i = 0; i < sell_list.size(); i++) {
			if(i >= current_shop_slot_index && i < current_shop_slot_index + 5) {
				shop_slot[i].setVisible(true);
				shop_slot[i].setBounds(11, 115 + 42 * (i - current_shop_slot_index), 201, 35);
			}
			else {
				shop_slot[i].setVisible(false);
			}
		}
		
		synchronized(currentNullRemovedList) {
			for (int i = 0; i < Maplestory.player.inventory.Max_Size; i++) {
				if((i >= current_user_slot_index) && (i < current_user_slot_index + 5) && (i < currentNullRemovedList.size())) {
					user_slot[i].setVisible(true);
					user_slot[i].setBounds(241, 115 + 42 * (i - current_user_slot_index), 201, 35);
				}
				else {
					user_slot[i].setVisible(false);
				}
			}
		}
		
		paintComponents(g);
	}
	
	public void makeSlots() {
		shop_slot = new Shop_Shop_Slot[sell_list.size()];
		for(int i=0;i<sell_list.size();i++) {
			shop_slot[i] = new Shop_Shop_Slot(i);
			shop_slot[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e)) {
						settoTop();
					}
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e)) {
						Shop_Shop_Slot source = (Shop_Shop_Slot)e.getSource();
						int index = source.index;
						if(e.getClickCount() == 1) {
							selectedShopSlotIndex = index;
						}
						else if(e.getClickCount() >= 2) {
							sellItem(currentSellList.get(index));
						}
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					Shop_Shop_Slot source = (Shop_Shop_Slot)e.getSource();
					int index = source.index;
					Item data = currentSellList.get(index).item;
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
			add(shop_slot[i]);
		}
		
		user_slot = new Shop_User_Slot[Maplestory.player.inventory.Max_Size];
		for(int i=0;i<Maplestory.player.inventory.Max_Size;i++) {
			user_slot[i] = new Shop_User_Slot(i);
			user_slot[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e)) {
						settoTop();
					}
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e)) {
						Shop_User_Slot source = (Shop_User_Slot)e.getSource();
						int index = source.index;
						if(e.getClickCount() == 1) {
							selectedUserSlotIndex = index;
						}
						else if(e.getClickCount() >= 2) {
							buyItem(currentNullRemovedList.get(index));
						}
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					Shop_User_Slot source = (Shop_User_Slot)e.getSource();
					int index = source.index;
					Item data = currentNullRemovedList.get(index);
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
			add(user_slot[i]);
		}
	}
	public void makeButtons() {
		bt_close = new JLabel("");
		bt_close.setBounds(155, 6, 12, 12);
		bt_close.setIcon(Maplestory.images.Inventory_Close);
		bt_close.setDisabledIcon(Maplestory.images.Inventory_Close_Disabled);
		bt_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				close_entered = true;
				if(!close_pressed) {
					bt_close.setIcon(Maplestory.images.Inventory_Close_Rollover);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				close_entered = false;
				if(!close_pressed) {
					bt_close.setIcon(Maplestory.images.Inventory_Close);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					close_pressed = true;
					bt_close.setIcon(Maplestory.images.Inventory_Close_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					close_pressed = false;
					if(close_entered) {
						bt_close.setIcon(Maplestory.images.Inventory_Close_Rollover);
						close();
					}
					else {
						bt_close.setIcon(Maplestory.images.Inventory_Close);
					}
				}
			}
		});
		
		bt_equip = new Inventory_Tab_Button();
		bt_equip.setBounds(240, 90, 34, 19);
		bt_equip.setIcon(Maplestory.images.Inventory_Equip_String);
		bt_equip.setDisabledIcon(Maplestory.images.Inventory_Equip_String_Disabled);
		bt_equip.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					settoTop();
					if(bt_equip.isEnabled()) {
						Music Tab_Music = new Music("Tab.wav", 1);
						Tab_Music.play();
						Show_User_Equip();
					}
				}
			}
		});

		bt_consume = new Inventory_Tab_Button();
		bt_consume.setBounds(274, 90, 34, 19);
		bt_consume.setIcon(Maplestory.images.Inventory_Consume_String);
		bt_consume.setDisabledIcon(Maplestory.images.Inventory_Consume_String_Disabled);
		bt_consume.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					settoTop();
					if(bt_consume.isEnabled()) {
						Music Tab_Music = new Music("Tab.wav", 1);
						Tab_Music.play();
						Show_User_Consume();
					}
				}
			}
		});

		bt_etc = new Inventory_Tab_Button();
		bt_etc.setBounds(308, 90, 34, 19);
		bt_etc.setIcon(Maplestory.images.Inventory_Etc_String);
		bt_etc.setDisabledIcon(Maplestory.images.Inventory_Etc_String_Disabled);
		bt_etc.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					settoTop();
					if(bt_etc.isEnabled()) {
						Music Tab_Music = new Music("Tab.wav", 1);
						Tab_Music.play();
						Show_User_Etc();
					}
				}
			}
		});

		bt_install = new Inventory_Tab_Button();
		bt_install.setBounds(342, 90, 34, 19);
		bt_install.setIcon(Maplestory.images.Inventory_Install_String);
		bt_install.setDisabledIcon(Maplestory.images.Inventory_Install_String_Disabled);
		bt_install.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					settoTop();
					if(bt_install.isEnabled()) {
						Music Tab_Music = new Music("Tab.wav", 1);
						Tab_Music.play();
						Show_User_Install();
					}
				}
			}
		});

		bt_cash = new Inventory_Tab_Button();
		bt_cash.setBounds(376, 90, 34, 19);
		bt_cash.setIcon(Maplestory.images.Inventory_Cash_String);
		bt_cash.setDisabledIcon(Maplestory.images.Inventory_Cash_String_Disabled);
		bt_cash.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					settoTop();
					if(bt_cash.isEnabled()) {
						Music Tab_Music = new Music("Tab.wav", 1);
						Tab_Music.play();
						Show_User_Cash();
					}
				}
			}
		});
		
		add(bt_close);
		add(bt_equip);
		add(bt_consume);
		add(bt_etc);
		add(bt_install);
		add(bt_cash);
	}
	public void makeScrollingObjects() {
		shop_prev = new JLabel("");
		shop_prev.setBounds(shop_scrollbar_xpos, 115, 15, 13);
		shop_prev.setIcon(Maplestory.images.Inventory_Prev);
		shop_prev.setDisabledIcon(Maplestory.images.Inventory_Prev_Disabled);
		shop_prev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				prev_entered = true;
				if(!prev_pressed) {
					shop_prev.setIcon(Maplestory.images.Inventory_Prev_Rollover);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				prev_entered = false;
				if(!prev_pressed) {
					shop_prev.setIcon(Maplestory.images.Inventory_Prev);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					settoTop();
					prev_pressed = true;
					shop_prev.setIcon(Maplestory.images.Inventory_Prev_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					prev_pressed = false;
					if(prev_entered) {
						shop_prev.setIcon(Maplestory.images.Inventory_Prev_Rollover);
						if(current_shop_slot_index > 0) {
							current_shop_slot_index--;
							setShopThumbLocation();
						}
					}
					else {
						shop_prev.setIcon(Maplestory.images.Inventory_Prev);
					}
				}
			}
		});
		
		shop_next = new JLabel("");
		shop_next.setBounds(shop_scrollbar_xpos, 304, 15, 13);
		shop_next.setIcon(Maplestory.images.Inventory_Next);
		shop_next.setDisabledIcon(Maplestory.images.Inventory_Next_Disabled);
		shop_next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				next_entered = true;
				if(!next_pressed) {
					shop_next.setIcon(Maplestory.images.Inventory_Next_Rollover);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				next_entered = false;
				if(!next_pressed) {
					shop_next.setIcon(Maplestory.images.Inventory_Next);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					settoTop();
					next_pressed = true;
					shop_next.setIcon(Maplestory.images.Inventory_Next_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					next_pressed = false;
					if(next_entered) {
						shop_next.setIcon(Maplestory.images.Inventory_Next_Rollover);
						if(current_shop_slot_index < currentSellList.size() - 5) {
							current_shop_slot_index++;
							setShopThumbLocation();
						}
					}
					else {
						shop_next.setIcon(Maplestory.images.Inventory_Next);
					}
				}
			}
		});
		
		shop_thumb = new JLabel("");
		shop_thumb.setSize(15, 25);
		shop_thumb.setIcon(Maplestory.images.Inventory_Thumb);
		shop_thumb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				thumb_entered = true;
				if(!thumb_pressed) {
					shop_thumb.setIcon(Maplestory.images.Inventory_Thumb_Rollover);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				thumb_entered = false;
				if(!thumb_pressed) {
					shop_thumb.setIcon(Maplestory.images.Inventory_Thumb);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					settoTop();
					thumb_pressed = true;
					shop_thumb.setIcon(Maplestory.images.Inventory_Thumb_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					thumb_pressed = false;
					if(thumb_entered) {
						shop_thumb.setIcon(Maplestory.images.Inventory_Thumb_Rollover);
					}
					else {
						shop_thumb.setIcon(Maplestory.images.Inventory_Thumb);
					}
				}
			}
		});
		shop_thumb.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int Y = e.getY()+shop_thumb.getY();
				if(shop_thumb.isEnabled()) {
					for(int i=0;i<=(currentSellList.size()-5);i++) {
						if(Y >= 128+151*i/(currentSellList.size()-5) && Y <= 128+151*(i+1)/(currentSellList.size()-5)) {
							current_shop_slot_index = i;
							setShopThumbLocation();
						}
					}
				}
			}
		});
		
		user_prev = new JLabel("");
		user_prev.setBounds(user_scrollbar_xpos, 115, 15, 13);
		user_prev.setIcon(Maplestory.images.Inventory_Prev);
		user_prev.setDisabledIcon(Maplestory.images.Inventory_Prev_Disabled);
		user_prev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				prev_entered = true;
				if(!prev_pressed) {
					user_prev.setIcon(Maplestory.images.Inventory_Prev_Rollover);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				prev_entered = false;
				if(!prev_pressed) {
					user_prev.setIcon(Maplestory.images.Inventory_Prev);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					settoTop();
					prev_pressed = true;
					user_prev.setIcon(Maplestory.images.Inventory_Prev_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					prev_pressed = false;
					if(prev_entered) {
						user_prev.setIcon(Maplestory.images.Inventory_Prev_Rollover);
						if(current_user_slot_index > 0) {
							current_user_slot_index--;
							setUserThumbLocation();
						}
					}
					else {
						user_prev.setIcon(Maplestory.images.Inventory_Prev);
					}
				}
			}
		});
		
		user_next = new JLabel("");
		user_next.setBounds(user_scrollbar_xpos, 304, 15, 13);
		user_next.setIcon(Maplestory.images.Inventory_Next);
		user_next.setDisabledIcon(Maplestory.images.Inventory_Next_Disabled);
		user_next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				next_entered = true;
				if(!next_pressed) {
					user_next.setIcon(Maplestory.images.Inventory_Next_Rollover);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				next_entered = false;
				if(!next_pressed) {
					user_next.setIcon(Maplestory.images.Inventory_Next);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					settoTop();
					next_pressed = true;
					user_next.setIcon(Maplestory.images.Inventory_Next_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					next_pressed = false;
					if(next_entered) {
						user_next.setIcon(Maplestory.images.Inventory_Next_Rollover);
						if(current_user_slot_index < (currentNullRemovedList.size() - 5)) {
							current_user_slot_index++;
							setUserThumbLocation();
						}
					}
					else {
						user_next.setIcon(Maplestory.images.Inventory_Next);
					}
				}
			}
		});
		
		user_thumb = new JLabel("");
		user_thumb.setSize(15, 25);
		user_thumb.setIcon(Maplestory.images.Inventory_Thumb);
		user_thumb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				thumb_entered = true;
				if(!thumb_pressed) {
					user_thumb.setIcon(Maplestory.images.Inventory_Thumb_Rollover);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				thumb_entered = false;
				if(!thumb_pressed) {
					user_thumb.setIcon(Maplestory.images.Inventory_Thumb);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					settoTop();
					thumb_pressed = true;
					user_thumb.setIcon(Maplestory.images.Inventory_Thumb_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					thumb_pressed = false;
					if(thumb_entered) {
						user_thumb.setIcon(Maplestory.images.Inventory_Thumb_Rollover);
					}
					else {
						user_thumb.setIcon(Maplestory.images.Inventory_Thumb);
					}
				}
			}
		});
		user_thumb.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int Y = e.getY()+user_thumb.getY();
				if(user_thumb.isEnabled()) {
					for(int i=0;i<=currentNullRemovedList.size()-5;i++) {
						if(Y >= 128+151*i/(currentNullRemovedList.size()-5) && Y <= 128+151*(i+1)/(currentNullRemovedList.size()-5)) {
							current_user_slot_index = i;
							setUserThumbLocation();
						}
					}
				}
			}
		});
		
		add(shop_prev);
		add(shop_next);
		add(shop_thumb);
		add(user_prev);
		add(user_next);
		add(user_thumb);
	}
	public void setScreenMouseEvents() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					mouseX = e.getX();
					mouseY = e.getY();
					settoTop();
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					int X = e.getX();
					int Y = e.getY();
					int Screen_X = getX();
					int Screen_Y = getY();
					if(mouseX >= 4 && mouseX <= 450 && mouseY >= 4 && mouseY <= 15) {
						if(Screen_X >= -100 && Screen_X <= 450 && Screen_Y >= 0 && Screen_Y <= 550) {
							int Screen_New_X = X + Screen_X - mouseX;
							int Screen_New_Y = Y + Screen_Y - mouseY;
							if(Screen_New_X >= -100 && Screen_New_X <= 450 && Screen_New_Y >= 0 && Screen_New_Y <= 550) {
								setLocation(Screen_New_X, Screen_New_Y);
							}
						}
					}
				}
			}
		});
		addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int direction = e.getWheelRotation();
				mouseX = e.getX();
				mouseY = e.getY();
				if(shop_thumb.isEnabled()) {
					if(mouseX >= 7 && mouseX <= 226 && mouseY >= 114 && mouseY <= 317) {
						if(direction > 0) {
							if(current_shop_slot_index < (currentSellList.size() - 5)) {
								current_shop_slot_index++;
							}
						}
						else if(direction < 0) {
							if(current_shop_slot_index > 0) {
								current_shop_slot_index--;
							}
						}
						setShopThumbLocation();
					}
				}

				if(user_thumb.isEnabled()) {
					if(mouseX >= 237 && mouseX <= 456 && mouseY >= 114 && mouseY <= 317) {
						if(direction > 0) {
							if(current_user_slot_index < (currentNullRemovedList.size() - 5)) {
								current_user_slot_index++;
							}
						}
						else if(direction < 0) {
							if(current_user_slot_index > 0) {
								current_user_slot_index--;
							}
						}
						setUserThumbLocation();
					}
				}
			}
		});
		
	}
	
	public abstract void setSellList();
	
	public void sellItem(ItemForSale itemForSale) {
		Maplestory.player.Character_Buy_Item(itemForSale.item.getNew(1), itemForSale.price);
		if(!itemForSale.item.infiniteQuantity) {
			itemForSale.item.Quantity--;
		}
	}
	
	public void buyItem(Item item) {
		Maplestory.player.Character_Sell_Item(item);
	}
	
	public void Show_User_Equip() {
		bt_equip.setEnabled(false);
		bt_consume.setEnabled(true);
		bt_etc.setEnabled(true);
		bt_install.setEnabled(true);
		bt_cash.setEnabled(true);
		currentNullRemovedList = Maplestory.player.inventory.NullRemovedEquipList;
		current_type = 0;
		current_user_slot_index = Equip_user_line_index;

		Stage.move = null;
		setShopScrollingObjects();
		setUserScrollingObjects();
	}
	public void Show_User_Consume() {
		bt_equip.setEnabled(true);
		bt_consume.setEnabled(false);
		bt_etc.setEnabled(true);
		bt_install.setEnabled(true);
		bt_cash.setEnabled(true);
		currentNullRemovedList = Maplestory.player.inventory.NullRemovedConsumeList;
		current_type = 1;
		current_user_slot_index = Consume_user_line_index;

		Stage.move = null;
		setShopScrollingObjects();
		setUserScrollingObjects();
	}
	public void Show_User_Etc() {
		bt_equip.setEnabled(true);
		bt_consume.setEnabled(true);
		bt_etc.setEnabled(false);
		bt_install.setEnabled(true);
		bt_cash.setEnabled(true);
		currentNullRemovedList = Maplestory.player.inventory.NullRemovedEtcList;
		current_type = 2;
		current_user_slot_index = Etc_user_line_index;

		Stage.move = null;
		setShopScrollingObjects();
		setUserScrollingObjects();
	}
	public void Show_User_Install() {
		bt_equip.setEnabled(true);
		bt_consume.setEnabled(true);
		bt_etc.setEnabled(true);
		bt_install.setEnabled(false);
		bt_cash.setEnabled(true);
		currentNullRemovedList = Maplestory.player.inventory.NullRemovedInstallList;
		current_type = 3;
		current_user_slot_index = Install_user_line_index;

		Stage.move = null;
		setShopScrollingObjects();
		setUserScrollingObjects();
	}
	public void Show_User_Cash() {
		bt_equip.setEnabled(true);
		bt_consume.setEnabled(true);
		bt_etc.setEnabled(true);
		bt_install.setEnabled(true);
		bt_cash.setEnabled(false);
		currentNullRemovedList = Maplestory.player.inventory.NullRemovedCashList;
		current_type = 4;
		current_user_slot_index = Cash_user_line_index;

		Stage.move = null;
		setShopScrollingObjects();
		setUserScrollingObjects();
	}
	
	public void setShopScrollingObjects() {
		if(currentSellList.size() <= 5) {
			shop_prev.setEnabled(false);
			shop_next.setEnabled(false);
			shop_thumb.setEnabled(false);
			shop_thumb.setVisible(false);
		}
		else {
			shop_prev.setEnabled(true);
			shop_next.setEnabled(true);
			shop_thumb.setEnabled(true);
			shop_thumb.setVisible(true);
			setShopThumbLocation();
		}
	}
	public void setShopThumbLocation() {
		shop_thumb.setLocation(shop_scrollbar_xpos, 128+151*current_shop_slot_index/(currentSellList.size()-5));
	}
	
	public void setUserScrollingObjects() {
		if(currentNullRemovedList.size() <= 5) {
			user_prev.setEnabled(false);
			user_next.setEnabled(false);
			user_thumb.setEnabled(false);
			user_thumb.setVisible(false);
		}
		else {
			user_prev.setEnabled(true);
			user_next.setEnabled(true);
			user_thumb.setEnabled(true);
			user_thumb.setVisible(true);
			setUserThumbLocation();
		}
	}
	public void setUserThumbLocation() {
		user_thumb.setLocation(user_scrollbar_xpos, 128+151*current_user_slot_index/(currentNullRemovedList.size()-5));
		if(current_type == 0) {
			Equip_user_line_index = current_user_slot_index;
		}
		else if(current_type == 1) {
			Consume_user_line_index = current_user_slot_index;
		}
		else if(current_type == 2) {
			Etc_user_line_index = current_user_slot_index;
		}
		else if(current_type == 3) {
			Install_user_line_index = current_user_slot_index;
		}
		else if(current_type == 4) {
			Cash_user_line_index = current_user_slot_index;
		}
	}
	
	public void open(ImageIcon _NPCImage) {
		if(isOpen) {
			return;
		}
		Stage.current_shop = this;
		
		NPCImage = _NPCImage;
		
		currentSellList = sell_list;
		setVisible(true);
		settoTop();
		
		current_shop_slot_index = 0;
		current_user_slot_index = 0;
		Equip_user_line_index = 0;
		Consume_user_line_index = 0;
		Etc_user_line_index = 0;
		Install_user_line_index = 0;
		Cash_user_line_index = 0;
		
		selectedShopSlotIndex = -1;
		selectedUserSlotIndex = -1;
		
		Show_User_Equip();
		
		isOpen = true;
	}
	
	public void close() {
		Stage.current_shop = null;
		
		isOpen = false;
		setVisible(false);
	}
	
	public void settoTop() {
		Maplestory.current_stage.setComponentZOrder(this, 0);
	}
}