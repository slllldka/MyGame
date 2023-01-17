package maplestory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Inventory extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static int ZOrder = 1;
	
	//Slot
	private Inventory_Slot[] Slot;
	
	//Scrolling Objects
	private JLabel prev, next, thumb;
	private int scrollbar_xpos = 153;
	
	//Buttons
	private JLabel bt_close, bt_equip, bt_consume, bt_etc, bt_install, bt_cash, bt_coin;
	
	//Control Variables
	private boolean prev_entered = false, prev_pressed = false;
	private boolean next_entered = false, next_pressed = false;
	private boolean thumb_entered = false, thumb_pressed = false;
	private boolean close_entered = false, close_pressed = false;
	private boolean coin_entered = false, coin_pressed = false;
	protected int move_index = -1;
	private boolean isOpen = false;
	private int mouseX, mouseY;
	
	//List
	protected final int Max_Size = 128;
	protected ArrayList<Item> current_inventory_list;
	protected int current_type; //0: Equip, 1: Consume, 2: Etc, 3: Install, 4: Cash
	protected int current_size;
	protected int current_max_line_index;
	protected int current_line_index;
	
	//Equip List
	protected ArrayList<Item> Equip_inventory_list = new ArrayList<Item>(Max_Size);
	protected ArrayList<Item> NullRemovedEquipList = new ArrayList<Item>(Max_Size);
	protected int Equip_size = 28;
	protected int Equip_max_line_index = (Equip_size-1) / 4 - 5;
	protected int Equip_line_index = 0;
	
	//Consume List
	protected ArrayList<Item> Consume_inventory_list = new ArrayList<Item>(Max_Size);
	protected ArrayList<Item> NullRemovedConsumeList = new ArrayList<Item>(Max_Size);
	protected int Consume_size = 40;
	protected int Consume_max_line_index = (Consume_size-1) / 4 - 5;
	protected int Consume_line_index = 0;
	
	//Etc List
	protected ArrayList<Item> Etc_inventory_list = new ArrayList<Item>(Max_Size);
	protected ArrayList<Item> NullRemovedEtcList = new ArrayList<Item>(Max_Size);
	protected int Etc_size = 28;
	protected int Etc_max_line_index = (Etc_size-1) / 4 - 5;
	protected int Etc_line_index = 0;
	
	//Install List
	protected ArrayList<Item> Install_inventory_list = new ArrayList<Item>(Max_Size);
	protected ArrayList<Item> NullRemovedInstallList = new ArrayList<Item>(Max_Size);
	protected int Install_size = 28;
	protected int Install_max_line_index = (Install_size-1) / 4 - 5;
	protected int Install_line_index = 0;
	
	//Cash List
	protected ArrayList<Item> Cash_inventory_list = new ArrayList<Item>(Max_Size);
	protected ArrayList<Item> NullRemovedCashList = new ArrayList<Item>(Max_Size);
	protected int Cash_size = 28;
	protected int Cash_max_line_index = (Cash_size-1) / 4 - 5;
	protected int Cash_line_index = 0;
	
	//Meso
	protected int Meso = 10000;
	protected String decimal = "";
	
	public Inventory() {
		setBounds(50, 50, 175, 289);
		
		makeSlots();
		makeScrollingObjects();
		makeButtons();
		setScreenMouseEvents();
		Show_Equip();
		
		setVisible(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(Maplestory.images.Inventory_BackGroundImg.getImage(), 0, 0, this);
		g.drawImage(Maplestory.images.Inventory_Base.getImage(), scrollbar_xpos, 63, 15, 177, this);
		g.setColor(Color.WHITE);
		g.drawLine(3, 23, 172, 23);
		if(current_type == 0) {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab_Disabled.getImage(), 3, 23, this);
		}
		else {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab.getImage(), 3, 24, this);
		}
		if(current_type == 1) {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab_Disabled.getImage(), 37, 23, this);
		}
		else {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab.getImage(), 37, 24, this);
		}
		if(current_type == 2) {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab_Disabled.getImage(), 71, 23, this);
		}
		else {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab.getImage(), 71, 24, this);
		}
		if(current_type == 3) {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab_Disabled.getImage(), 105, 23, this);
		}
		else {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab.getImage(), 105, 24, this);
		}
		if(current_type == 4) {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab_Disabled.getImage(), 139, 23, this);
		}
		else {
			g.drawImage(Maplestory.images.Inventory_Equip_Tab.getImage(), 139, 24, this);
		}
		
		for(int i=0;i<Max_Size;i++) {
			if(i >= current_line_index*4 && i < current_line_index*4+24) {
				Slot[i].setVisible(true);
				Slot[i].setBounds(8 + 36 * ((i - current_line_index*4) % 4), 50 + 34 * ((i - current_line_index*4) / 4), 32, 32);
			}
			else {
				Slot[i].setVisible(false);
			}
		}
		
		g.setFont(Map.font_meso);
		g.setColor(Color.BLACK);
		decimal = Item_Meso.dec_format.format(Maplestory.player.inventory.Meso);
		g.drawString(decimal, 143 - 3 - Maplestory.current_stage.getFontMetrics_meso().stringWidth(decimal), 281 - 2);
		
		paintComponents(g);
	}
	
	public void makeSlots() {
		Slot = new Inventory_Slot[Max_Size];
		for(int i=0;i<Max_Size;i++) {
			Slot[i] = new Inventory_Slot(i);
			Slot[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
						settoTop();
					}
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
						Inventory_Slot source = (Inventory_Slot)e.getSource();
						int index = source.Get_Index();
						if(index < current_size) {
							//Click Once
							if(e.getClickCount() % 4 == 1) {
								//Not Moving Item
								if(Map.move == null) {
									Item item_src = current_inventory_list.get(index);
									if(item_src != null) {
										Music DragStart_Music = new Music("DragStart.wav", 1);
										DragStart_Music.play();
										Map.move = item_src.getRawIcon();
										Maplestory.ui_quick_slot.move_index = -1;
										Maplestory.ui_keySetting.move_index = -1;
										move_index = index;
									}
								}
								//Moving Item
								else {
									//Moving Item from Inventory
									if(move_index != -1) {
										Music DragEnd_Music = new Music("DragEnd.wav", 1);
										DragEnd_Music.play();
										Item item_src = current_inventory_list.get(move_index);
										//Same Slot
										if(index == move_index) {
											Map.move = null;
										}
										//Different Slot
										else {
											Item item_dst = current_inventory_list.get(index);
											//Dst Slot is empty
											if(item_dst == null) {
												Map.move = null;
												current_inventory_list.set(index, item_src);
												current_inventory_list.set(move_index, null);
											}
											//Dst Slot is not empty
											else {
												Map.move = null;
												Item temp = item_dst;
												current_inventory_list.set(index, item_src);
												current_inventory_list.set(move_index, temp);
											}
										}
										
										updateNullRemovedList(item_src.getType());
									}
									//Moving Item not from Inventory
									else {
										Music DragEnd_Music = new Music("DragEnd.wav", 1);
										DragEnd_Music.play();
										Map.move = null;
									}
								}
							}
							//Click Twice or More
							else if(e.getClickCount() % 4 == 2){
								if(Map.move != null) {
									if(move_index != -1) {
										Music DragEnd_Music = new Music("DragEnd.wav", 1);
										DragEnd_Music.play();
										Item data = current_inventory_list.get(index);
										if(data != null) {
											Map.move = null;
											if(Maplestory.player.alive) {
												data.Use(index);
											}
										}
									}
								}
							}
						}
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					if(!UI_Notice.isOpen) {
						Inventory_Slot source = (Inventory_Slot)e.getSource();
						int index = source.Get_Index();
						if(index < current_size) {
							Item data = current_inventory_list.get(index);
							if(data != null) {
								Map.info = data.getInfo();
								Map.info_x = getMousePosition().x + getLocation().x;
								Map.info_y = getMousePosition().y + getLocation().y;
								//System.out.println(data.Get_name() + "\r\n" + data.Get_tooltip());
							}
						}
					}
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					Map.info = null;
/*					Inventory_Slot source = (Inventory_Slot)e.getSource();
					int index = source.Get_Index();
*/				}
			});
			
			add(Slot[i]);
			Equip_inventory_list.add(i, null);
			Consume_inventory_list.add(i, null);
			Etc_inventory_list.add(i, null);
			Install_inventory_list.add(i, null);
			Cash_inventory_list.add(i, null);
		}
	}
	
	public void makeScrollingObjects() {
		prev = new JLabel("");
		prev.setBounds(scrollbar_xpos, 50, 15, 13);
		prev.setIcon(Maplestory.images.Inventory_Prev);
		prev.setDisabledIcon(Maplestory.images.Inventory_Prev_Disabled);
		prev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					prev_entered = true;
					if(!prev_pressed) {
						prev.setIcon(Maplestory.images.Inventory_Prev_Rollover);
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					prev_entered = false;
					if(!prev_pressed) {
						prev.setIcon(Maplestory.images.Inventory_Prev);
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					settoTop();
					prev_pressed = true;
					prev.setIcon(Maplestory.images.Inventory_Prev_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					prev_pressed = false;
					if(prev_entered) {
						prev.setIcon(Maplestory.images.Inventory_Prev_Rollover);
						if(current_line_index > 0) {
							current_line_index--;
							setThumbLocation();
						}
					}
					else {
						prev.setIcon(Maplestory.images.Inventory_Prev);
					}
				}
			}
		});
		
		next = new JLabel("");
		next.setBounds(scrollbar_xpos, 239, 15, 13);
		next.setIcon(Maplestory.images.Inventory_Next);
		next.setDisabledIcon(Maplestory.images.Inventory_Next_Disabled);
		next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					next_entered = true;
					if(!next_pressed) {
						next.setIcon(Maplestory.images.Inventory_Next_Rollover);
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					next_entered = false;
					if(!next_pressed) {
						next.setIcon(Maplestory.images.Inventory_Next);
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					settoTop();
					next_pressed = true;
					next.setIcon(Maplestory.images.Inventory_Next_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					next_pressed = false;
					if(next_entered) {
						next.setIcon(Maplestory.images.Inventory_Next_Rollover);
						if(current_line_index < (current_size-1) / 4 - 5) {
							current_line_index++;
							setThumbLocation();
						}
					}
					else {
						next.setIcon(Maplestory.images.Inventory_Next);
					}
				}
			}
		});
		
		thumb = new JLabel("");
		thumb.setSize(15, 25);
		thumb.setIcon(Maplestory.images.Inventory_Thumb);
		thumb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					thumb_entered = true;
					if(!thumb_pressed) {
						thumb.setIcon(Maplestory.images.Inventory_Thumb_Rollover);
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					thumb_entered = false;
					if(!thumb_pressed) {
						thumb.setIcon(Maplestory.images.Inventory_Thumb);
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					settoTop();
					thumb_pressed = true;
					thumb.setIcon(Maplestory.images.Inventory_Thumb_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					thumb_pressed = false;
					if(thumb_entered) {
						thumb.setIcon(Maplestory.images.Inventory_Thumb_Rollover);
					}
					else {
						thumb.setIcon(Maplestory.images.Inventory_Thumb);
					}
				}
			}
		});
		thumb.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					int Y = e.getY()+thumb.getY();
					if(thumb.isEnabled()) {
						for(int i=0;i<=current_max_line_index;i++) {
							if(Y >= 63+151*i/current_max_line_index && Y <= 63+151*(i+1)/current_max_line_index) {
								current_line_index = i;
								setThumbLocation();
							}
						}
					}
				}
			}
		});
		
		add(prev);
		add(next);
		add(thumb);
	}
	
	public void setThumbLocation() {
		thumb.setLocation(scrollbar_xpos, 63+151*current_line_index/current_max_line_index);
		if(current_type == 0) {
			Equip_line_index = current_line_index;
		}
		else if(current_type == 1) {
			Consume_line_index = current_line_index;
		}
		else if(current_type == 2) {
			Etc_line_index = current_line_index;
		}
		else if(current_type == 3) {
			Install_line_index = current_line_index;
		}
		else if(current_type == 4) {
			Cash_line_index = current_line_index;
		}
	}
	
	public void setScrollingObjects() {
		if(current_size <= 24) {
			prev.setEnabled(false);
			next.setEnabled(false);
			thumb.setEnabled(false);
			thumb.setVisible(false);
		}
		else {
			prev.setEnabled(true);
			next.setEnabled(true);
			thumb.setEnabled(true);
			thumb.setVisible(true);
			setThumbLocation();
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
				if(!UI_Notice.isOpen) {
					close_entered = true;
					if(!close_pressed) {
						bt_close.setIcon(Maplestory.images.Inventory_Close_Rollover);
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					close_entered = false;
					if(!close_pressed) {
						bt_close.setIcon(Maplestory.images.Inventory_Close);
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					close_pressed = true;
					bt_close.setIcon(Maplestory.images.Inventory_Close_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
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
		bt_equip.setBounds(3, 23, 34, 19);
		bt_equip.setIcon(Maplestory.images.Inventory_Equip_String);
		bt_equip.setDisabledIcon(Maplestory.images.Inventory_Equip_String_Disabled);
		bt_equip.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					settoTop();
					if(bt_equip.isEnabled()) {
						Music Tab_Music = new Music("Tab.wav", 1);
						Tab_Music.play();
						Show_Equip();
					}
				}
			}
		});

		bt_consume = new Inventory_Tab_Button();
		bt_consume.setBounds(37, 23, 34, 19);
		bt_consume.setIcon(Maplestory.images.Inventory_Consume_String);
		bt_consume.setDisabledIcon(Maplestory.images.Inventory_Consume_String_Disabled);
		bt_consume.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					settoTop();
					if(bt_consume.isEnabled()) {
						Music Tab_Music = new Music("Tab.wav", 1);
						Tab_Music.play();
						Show_Consume();
					}
				}
			}
		});

		bt_etc = new Inventory_Tab_Button();
		bt_etc.setBounds(71, 23, 34, 19);
		bt_etc.setIcon(Maplestory.images.Inventory_Etc_String);
		bt_etc.setDisabledIcon(Maplestory.images.Inventory_Etc_String_Disabled);
		bt_etc.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					settoTop();
					if(bt_etc.isEnabled()) {
						Music Tab_Music = new Music("Tab.wav", 1);
						Tab_Music.play();
						Show_Etc();
					}
				}
			}
		});

		bt_install = new Inventory_Tab_Button();
		bt_install.setBounds(105, 23, 34, 19);
		bt_install.setIcon(Maplestory.images.Inventory_Install_String);
		bt_install.setDisabledIcon(Maplestory.images.Inventory_Install_String_Disabled);
		bt_install.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					settoTop();
					if(bt_install.isEnabled()) {
						Music Tab_Music = new Music("Tab.wav", 1);
						Tab_Music.play();
						Show_Install();
					}
				}
			}
		});

		bt_cash = new Inventory_Tab_Button();
		bt_cash.setBounds(139, 23, 34, 19);
		bt_cash.setIcon(Maplestory.images.Inventory_Cash_String);
		bt_cash.setDisabledIcon(Maplestory.images.Inventory_Cash_String_Disabled);
		bt_cash.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					settoTop();
					if(bt_cash.isEnabled()) {
						Music Tab_Music = new Music("Tab.wav", 1);
						Tab_Music.play();
						Show_Cash();
					}
				}
			}
		});
		
		Runnable run_bt_coin = new Runnable() {

			@Override
			public void run() {
				int count = 0;
				while(!coin_pressed && coin_entered && !UI_Notice.isOpen) {
					count = (count + 1) % 4; 
					bt_coin.setIcon(Maplestory.images.Inventory_Coin_Rollover[count]);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		};
		bt_coin = new JLabel("");
		bt_coin.setBounds(8, 267, 14, 14);
		bt_coin.setIcon(Maplestory.images.Inventory_Coin);
		bt_coin.setDisabledIcon(Maplestory.images.Inventory_Coin_Disabled);
		bt_coin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					coin_entered = true;
					if(!coin_pressed) {
						Maplestory.thread_pool.submit(run_bt_coin);
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!UI_Notice.isOpen) {
					coin_entered = false;
					if(!coin_pressed) {
						bt_coin.setIcon(Maplestory.images.Inventory_Coin);
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					coin_pressed = true;
					bt_coin.setIcon(Maplestory.images.Inventory_Coin_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					coin_pressed = false;
					if(coin_entered) {
						Maplestory.thread_pool.submit(run_bt_coin);
						Maplestory.player.Character_Drop_Meso();
					}
					else {
						bt_coin.setIcon(Maplestory.images.Inventory_Coin);
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
		add(bt_coin);
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
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && !UI_Notice.isOpen) {
					if(Map.move != null) {
						Music DragEnd_Music = new Music("DragEnd.wav", 1);
						DragEnd_Music.play();
						Map.move = null;
					}
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
					if(mouseX >= 4 && mouseX <= 170 && mouseY >= 4 && mouseY <= 19) {
						if(Screen_X >= -100 && Screen_X <= 750 && Screen_Y >= 0 && Screen_Y <= 550) {
							int Screen_New_X = X + Screen_X - mouseX;
							int Screen_New_Y = Y + Screen_Y - mouseY;
							if(Screen_New_X >= -100 && Screen_New_X <= 750 && Screen_New_Y >= 0 && Screen_New_Y <= 550) {
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
				if(thumb.isEnabled() && !UI_Notice.isOpen) {
					int direction = e.getWheelRotation();
					mouseX = e.getX();
					mouseY = e.getY();
					
					if(mouseY >= 50 && mouseY <= 252) {
						if(direction > 0) {
							if(current_line_index < (current_size-1) / 4 - 5) {
								current_line_index++;
							}
						}
						else if(direction < 0) {
							if(current_line_index > 0) {
								current_line_index--;
							}
						}
						setThumbLocation();
					}
				}
			}
		});
		
	}
	
	public void Reduce_Item(int index, int number) {
		Item data = current_inventory_list.get(index);
		if(data.Quantity - number == 0) {
			current_inventory_list.set(index, null);
			updateNullRemovedList(data.getType());
		}
		else {
			data.Quantity -= number;
			current_inventory_list.set(index, data);
		}
	}
	public void Reduce_Item(Item item, int number) {
		if(item.Quantity - number == 0) {
			ArrayList<Item> srcList = null;
			if(item.getType().equals("Equip")) {
				srcList = Equip_inventory_list;
			}
			else if(item.getType().equals("Consume")) {
				srcList = Consume_inventory_list;
			}
			else if(item.getType().equals("Etc")) {
				srcList = Etc_inventory_list;
			}
			else if(item.getType().equals("Install")) {
				srcList = Install_inventory_list;
			}
			else if(item.getType().equals("Cash")) {
				srcList = Cash_inventory_list;
			}
			
			for(int i=0;i<srcList.size();i++) {
				if(srcList.get(i) == item) {
					srcList.set(i, null);
				}
			}
			updateNullRemovedList(item.getType());
		}
		else {
			item.Quantity -= number;
		}
	}
	
	public void Increase_Slot_Size(int num) {
		int increase = 0;
		for(int i=0;i<num;i++) {
			if(current_size+i < Max_Size) {
				increase++;
			}
			else {
				break;
			}
		}
		current_size+=increase;
	}
	
	public void updateNullRemovedList(String type) {
		if(type.equals("Equip")) {
			NullRemovedEquipList.clear();
			for(Item item : Equip_inventory_list) {
				if(item != null) {
					NullRemovedEquipList.add(item);
				}
			}
		}
		else if(type.equals("Consume")) {
			NullRemovedConsumeList.clear();
			for(Item item : Consume_inventory_list) {
				if(item != null) {
					NullRemovedConsumeList.add(item);
				}
			}
		}
		else if(type.equals("Etc")) {
			NullRemovedEtcList.clear();
			for(Item item : Etc_inventory_list) {
				if(item != null) {
					NullRemovedEtcList.add(item);
				}
			}
		}
		else if(type.equals("Install")) {
			NullRemovedInstallList.clear();
			for(Item item : Install_inventory_list) {
				if(item != null) {
					NullRemovedInstallList.add(item);
				}
			}
		}
		else if(type.equals("Cash")) {
			NullRemovedCashList.clear();
			for(Item item : Cash_inventory_list) {
				if(item != null) {
					NullRemovedCashList.add(item);
				}
			}
		}
	}
	
	public void Show_Equip() {
		bt_equip.setEnabled(false);
		bt_consume.setEnabled(true);
		bt_etc.setEnabled(true);
		bt_install.setEnabled(true);
		bt_cash.setEnabled(true);
		current_inventory_list = Equip_inventory_list;
		current_type = 0;
		current_size = Equip_size;
		current_max_line_index = Equip_max_line_index;
		current_line_index = Equip_line_index;

		Map.move = null;
		setScrollingObjects();
	}
	public void Show_Consume() {
		bt_equip.setEnabled(true);
		bt_consume.setEnabled(false);
		bt_etc.setEnabled(true);
		bt_install.setEnabled(true);
		bt_cash.setEnabled(true);
		current_inventory_list = Consume_inventory_list;
		current_type = 1;
		current_size = Consume_size;
		current_max_line_index = Consume_max_line_index;
		current_line_index = Consume_line_index;

		Map.move = null;
		setScrollingObjects();
	}
	public void Show_Etc() {
		bt_equip.setEnabled(true);
		bt_consume.setEnabled(true);
		bt_etc.setEnabled(false);
		bt_install.setEnabled(true);
		bt_cash.setEnabled(true);
		current_inventory_list = Etc_inventory_list;
		current_type = 2;
		current_size = Etc_size;
		current_max_line_index = Etc_max_line_index;
		current_line_index = Etc_line_index;

		Map.move = null;
		setScrollingObjects();
	}
	public void Show_Install() {
		bt_equip.setEnabled(true);
		bt_consume.setEnabled(true);
		bt_etc.setEnabled(true);
		bt_install.setEnabled(false);
		bt_cash.setEnabled(true);
		current_inventory_list = Install_inventory_list;
		current_type = 3;
		current_size = Install_size;
		current_max_line_index = Install_max_line_index;
		current_line_index = Install_line_index;

		Map.move = null;
		setScrollingObjects();
	}
	public void Show_Cash() {
		bt_equip.setEnabled(true);
		bt_consume.setEnabled(true);
		bt_etc.setEnabled(true);
		bt_install.setEnabled(true);
		bt_cash.setEnabled(false);
		current_inventory_list = Cash_inventory_list;
		current_type = 4;
		current_size = Cash_size;
		current_max_line_index = Cash_max_line_index;
		current_line_index = Cash_line_index;

		Map.move = null;
		setScrollingObjects();
	}
	
	public void open() {
		Music MenuUp_Music = new Music("MenuUp.wav", 1);
		MenuUp_Music.play();
		isOpen = true;
		settoTop();
		setVisible(true);
		
	}

	public void settoTop() {
		Maplestory.current_stage.setComponentZOrder(this, 1);
	}
	
	public void close() {
		Music MenuDown_Music = new Music("MenuDown.wav", 1);
		MenuDown_Music.play();
		if(Map.move != null && Maplestory.player.inventory.move_index != -1) {
			Map.move = null;
		}
		if(Map.info != null) {
			Map.info = null;
		}
		
		setVisible(false);
		settoBottom();
		isOpen = false;
	}
	
	public void settoBottom() {
		Maplestory.current_stage.setComponentZOrder(this, 4);
	}
	
	public boolean getOpen() {
		return isOpen;
	}
	
	public JLabel getScreen() {
		return this;
	}
}
