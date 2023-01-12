package maplestory;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

//All Stages extends this class
public class Stage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected long start_time = 0;

	protected JFrame frame; // JFrame on Maplestory Class
	
	protected boolean isTown;
	protected Stage nearestTown;
	
	//Mouse Position
	protected int MouseX = 0, MouseY = 0;
	
	//Music
	protected Music Stage_Music;
	
	//Theme
	protected String theme;
	
	//Stage Number
	protected int StageNum;
	
	protected int CharFirstDirection; // Character Direction

	//Map Size
	protected int X_Size, Y_Size;
	
	//Map Consister Images
	protected ImageIcon Foothold1Img, Foothold2Img, MeteorImg;
	protected ImageIcon BackGroundImg, GroundImg, CharacterFirstImg;
	
	//Minimap
	protected boolean MiniMap_Available = false;
	
	//Shop
	protected static Shop current_shop = null;
	
	//Portal
	protected ImageIcon current_PortalImg;
	protected int Portal_Offset;
	
	//UI
	protected ImageIcon BackImg, Mob_HP_BarImg;
	
	//BlackOut
	protected ImageIcon BlackOutImg;
	protected float BlackOut_Alpha = 1f;
	protected JButton BackButton; // back button

	// List of Foothold, Wall, Ladder, Portal, Meteor, NPC
	protected ArrayList<Foothold> Foothold_List = new ArrayList<Foothold>();
	protected ArrayList<Foothold> FakeFoothold_List = new ArrayList<Foothold>();
	protected ArrayList<Wall> Wall_List = new ArrayList<Wall>();
	protected ArrayList<Ladder> Ladder_List = new ArrayList<Ladder>();
	protected ArrayList<Portal> Portal_List = new ArrayList<Portal>();
	protected ArrayList<MeteorInfo> Meteor_List = new ArrayList<MeteorInfo>();
	protected ArrayList<NPC> NPC_List = new ArrayList<NPC>();
	
	// Timer for Moving Objects (Meteors, Throwing Stars)
	protected LinkedList<Timer> Timer_List = new LinkedList<Timer>();
	
	// Mobs
	protected ArrayList<Mob> Mob_List = new ArrayList<Mob>();
	protected long mob_spawn_period = 30000;

	// Dropped Items
	protected LinkedList<Item> Item_List = new LinkedList<Item>();
	
	// Damage
	protected LinkedList<Damage> Damage_List = new LinkedList<Damage>();
	
	// Tomb
	protected ImageIcon current_TombImg = Maplestory.images.TombImg0;
	protected float Tomb_Alpha = 0f;
	protected int Tomb_X, Tomb_Y = 0;
	protected int Tomb_Offset;
	
	// LevelUp
	protected LinkedList<LevelUp> LevelUp_List = new LinkedList<LevelUp>();

	//Item Icon
	protected static ImageIcon move = null;
	
	//Item Info
	protected static ImageIcon info = null;
	protected static int info_x = 0, info_y = 0;
	
	// Camera, Character Info
	protected int CameraFirstX, CameraFirstY;
	protected int CameraX, CameraY;
	protected int CameraX_offset = 3, CameraY_offset = 4;
	protected int CharacterFirstX, CharacterFirstY;
	
	
	//Letters
	//Font
	protected static Font font = new Font("굴림", Font.PLAIN, 12);
	protected static Font font_meso = new Font("굴림", Font.PLAIN, 13);
	protected FontMetrics metrics = getFontMetrics(font);
	protected FontMetrics metrics_meso = getFontMetrics(font_meso);
	protected Message message = null;
	protected float message_alpha = 0;
	protected float message_list_size = 0;
	protected float showing_time = 3000;
	
	// FPS
	protected int saved_FPS = 0;
	protected int calculate_FPS = 0;
	protected long saved_time = 0;
	protected long current_time = 0;

	protected static boolean Available = false;
	protected static boolean Back_Pressed = false;
	
	// store If Key is pressed or not
	protected static boolean LeftKey = false;
	protected static boolean RightKey = false;
	protected static boolean UpKey = false;
	protected static boolean DownKey = false;
	protected static boolean AltKey = false;
	// store If Character is moving, jumping or on ladder
	protected static boolean Left = false;
	protected static boolean Right = false;
	protected static boolean Jump = false;
	protected static boolean Up = false;
	protected static boolean Down = false;
	protected static boolean Ladder = false;
	protected static boolean Ladder_Jump = false;
	protected static boolean attacked = false;
	protected static boolean Attacking = false;
	protected static boolean Hittable = true;

	public Stage(int CD, String BGName, String GName, String FH1Name, String FH2Name, JFrame _frame, JButton _back
			, boolean _isTown, Stage _nearestTown) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					if(move != null) {
						if(Maplestory.player.inventory.move_index != -1) {
							move = null;
							Maplestory.player.Character_Drop_Item();
						}
						else if(Maplestory.ui_keySetting.move_index != -1) {
							Music DragEnd_Music = new Music("DragEnd.wav", 1);
							DragEnd_Music.play();
							UI_KeyConfig_Slot slot = Maplestory.ui_keySetting.Slot[Maplestory.ui_keySetting.move_index];
							move = null;
							if(slot.actionMapKey.equals("ItemUse")) {
								slot.item = null;
								slot.actionMapKey = "";
								slot.actionImage = null;
							}
							else if(slot.actionMapKey.equals("Skill")) {
								
							}
							else {
								Maplestory.ui_keySetting.moveSlotToBottom(slot);
								slot.item = null;
								slot.actionMapKey = "";
								slot.actionImage = null;
							}
							Maplestory.ui_quick_slot.move_index = -1;
							Maplestory.ui_keySetting.move_index = -1;
							
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
		});
		
		frame = _frame;
		BackButton = _back;

		CharFirstDirection = CD;
		
		isTown = _isTown;
		nearestTown = _nearestTown;

		// set contentpane
		setPreferredSize(new Dimension(800, 600));
		setBorder(null);
		setLayout(null);

		// make background, ground ,Back, Portal images
		BackGroundImg = new ImageIcon(getClass().getClassLoader().getResource(BGName));
		GroundImg = new ImageIcon(getClass().getClassLoader().getResource(GName));
		
		current_PortalImg = Maplestory.images.PortalImg0;
		Portal_Offset = 0;

		// make footholds', ladders' images
		Foothold1Img = new ImageIcon(getClass().getClassLoader().getResource(FH1Name));
		Foothold2Img = new ImageIcon(getClass().getClassLoader().getResource(FH2Name));

		//make UI images
		BackImg = new ImageIcon(getClass().getClassLoader().getResource("BackIcon.png"));
		Mob_HP_BarImg = new ImageIcon(getClass().getClassLoader().getResource("Mob_HP_Bar.png"));
		
		//make blackout image
		BlackOutImg = new ImageIcon(getClass().getClassLoader().getResource("BlackOut.0.png"));
	}

	public FontMetrics getFontMetrics() {
		return metrics;
	}

	public FontMetrics getFontMetrics_meso() {
		return metrics_meso;
	}
	
	
	
//below: Method Name explains what it does
	// set Character's image, location, size
	public void setCharacter(int CX, int CY) {
		CharacterFirstX = CX;
		CharacterFirstY = CY;
	}

	// set Portal
	public void setPortal(Portal portal) {
		Portal_List.add(portal);
	}

	// set Footholds
	public void setFootholds(Foothold FH) {
		Foothold_List.add(FH);
	}

	// set FakeFootholds
	public void setFakeFootholds(Foothold FFH) {
		FakeFoothold_List.add(FFH);
	}
	
	// set Default Walls
	public void setDefaultWalls() {
		Wall_List.add(new Wall(0+25, 0, Y_Size, true));
		Wall_List.add(new Wall(X_Size-25, 0, Y_Size, false));
	}

	// set Ladders
	public void setLadders(Ladder ladder) {
		Ladder data = ladder;
		data.setYend(data.getYend() - 10);
		Ladder_List.add(data);
	}

	// set Meteors
	public void setMeteors(MeteorInfo Meteor) {
		Meteor_List.add(Meteor);
	}

	// set Mobs
	public void setMobs(Mob mob) {
		Mob_List.add(mob);
	}

	// Set Camera
	public void SetCamera(int CX, int CY) {
		if ((CameraX >= 0) && (CameraX <= X_Size-800)) {
			if(CX >= X_Size - 300 && CX <= X_Size) {
				CameraX = X_Size - 800;
			}
			else if(CX >= 0 && CX <= 300) {
				CameraX = 0;
			}
			else {
				if (CX < CameraX + 300) {
					if ((CX - 300 >= 0) && (CX - 300 <= X_Size - 800)) {
						CameraX = CX - 300;
					}
				} else if (CX > CameraX + 500) {
					if ((CX - 500 >= 0) && (CX - 500 <= X_Size - 800)) {
						CameraX = CX - 500;
					}
				}
			}
		}

		if ((CameraY >= 0) && (CameraY <= Y_Size-600)) {
			if(CY >= Y_Size - 300 && CY <= Y_Size) {
				CameraY = Y_Size - 600;
			}
			else if(CY >= 0 && CY <= 200) {
				CameraY = 0;
			}
			else {
				if (CY < CameraY + 200) {
					if ((CY - 200 >= 0) && (CY - 200 <= Y_Size - 600)) {
						CameraY = CY - 200;
					}
				} else if (CY > CameraY + 300) {
					if ((CY - 300 >= 0) && (CY - 300 <= Y_Size - 600)) {
						CameraY = CY - 300;
					}
				}
			}
		}
	}
	
	// Move Camera
	public void MoveCamera() {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				while(Available) {
					int CX = Maplestory.player.CharacterX;
					int CY = Maplestory.player.CharacterY - Character.CharacterHeight;
					if ((CameraX >= 0) && (CameraX <= X_Size-800)) {
						if (CX < CameraX + 300) {
							if ((CX - 300 >= 0) && (CX - 300 <= X_Size - 800)) {
								CameraX = CX - 300;
							}
						} else if (CX > CameraX + 500) {
							if ((CX - 500 >= 0) && (CX - 500 <= X_Size - 800)) {
								CameraX = CX - 500;
							}
						}
					}
			
					if ((CameraY >= 0) && (CameraY <= Y_Size-600)) {
						if (CY < CameraY + 200) {
							if ((CY - 200 >= 0) && (CY - 200 <= Y_Size - 600)) {
								CameraY = CY - 200;
							}
						} else if (CY > CameraY + 300) {
							if ((CY - 300 >= 0) && (CY - 300 <= Y_Size - 600)) {
								CameraY = CY - 300;
							}
						}
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}

	@Override
	public void paint(Graphics g) {
		if(!hasFocus()) {
			Stage.LeftKey = false;
			Stage.RightKey = false;
			Stage.UpKey = false;
			Stage.DownKey = false;
			Stage.AltKey = false;
		}
		
		frame.pack();
		Graphics2D g2 = (Graphics2D) g;
		Image image = createImage(800, 600);

		// Show Frame per second
		current_time = System.currentTimeMillis();
		if (saved_time == 0) {
			saved_time = current_time;
		} else {
			if (current_time >= saved_time + 1000) {
				saved_FPS = calculate_FPS;
				calculate_FPS = 0;
				saved_time = current_time;
			} else {
				calculate_FPS++;
			}
		}


		// frame limit
		//while ((System.currentTimeMillis() - saved_milli_sec) % 5 != 0);
		
		if(image != null) {
			Draw(image.getGraphics());
			g2.drawImage(image, 0, 0, this);
		}
		
		repaint();
	}

	public void Draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		// g2.drawImage(BackGroundImg.getImage(), 0, 0, 800, 600, 0, CameraY, 800,
		// CameraY+600, this);

		// Draw BackGround
		g2.drawImage(BackGroundImg.getImage(), 0, 0, 800, 600, CameraX / CameraX_offset, CameraY / CameraY_offset
				, CameraX / CameraX_offset + 800, CameraY / CameraY_offset + 600, this);
		
		// Draw Effect
		if(StageNum == 1) {
			g2.drawImage(Maplestory.images.shineWood.getImage(), 1800 - 369 - CameraX, 350 - 488 - CameraY, this);
		}
		
		// Draw Footholds
		for(Foothold foothold : Foothold_List) {
			foothold.drawFoothold(g2, CameraX, CameraY, this);
		}

		// Draw Fake Footholds
		for(Foothold foothold : FakeFoothold_List) {
			foothold.drawFoothold(g2, CameraX, CameraY, this);
		}

		// Draw Ladders
		for(Ladder ladder : Ladder_List) {
			ladder.drawLadder(g2, ladder.getX() - CameraX - 27, ladder.getYstart() - CameraY, this);
		}

		// Draw Meteors
		for(MeteorInfo meteor : Meteor_List) {
			if (meteor.isCurrent_visible()) {
				g2.drawImage(MeteorImg.getImage(), meteor.getCurrent_x() - CameraX, meteor.getCurrent_y() - CameraY, this);
			}
		}
		
		int len = 0;
		for(NPC npc : NPC_List) {
			if(npc.direction == NPC.LEFT) {
				g2.drawImage(npc.image.getImage(), npc.xpos - CameraX, npc.ypos - CameraY, this);
			}
			else if(npc.direction == NPC.RIGHT) {
				g2.drawImage(npc.image.getImage(), npc.xpos - CameraX, npc.ypos - CameraY
						, npc.xpos + npc.image.getIconWidth() - CameraX, npc.ypos + npc.image.getIconHeight() - CameraY
						, npc.image.getIconWidth(), 0, 0, npc.image.getIconHeight(), this);
			}
			len = metrics.stringWidth(npc.name);
			g2.setColor(Color.BLACK);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			g2.fillRect(npc.xpos + npc.image.getIconWidth() / 2 - len / 2 - CameraX
					, npc.ypos + npc.image.getIconHeight() - CameraY, len, 15);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g2.setFont(font);
			g2.setColor(Color.YELLOW);
			g2.drawString(npc.name, npc.xpos + npc.image.getIconWidth() / 2 - len / 2 - CameraX
					, npc.ypos + npc.image.getIconHeight() + 12 - CameraY);
			
			if(!npc.subName.equals("")) {
				len = metrics.stringWidth(npc.subName);
				g2.setColor(Color.BLACK);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
				g2.fillRect(npc.xpos + npc.image.getIconWidth() / 2 - len / 2 - CameraX
						, npc.ypos + npc.image.getIconHeight() + 15 - CameraY, len, 15);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
				g2.setFont(font);
				g2.setColor(Color.YELLOW);
				g2.drawString(npc.subName, npc.xpos + npc.image.getIconWidth() / 2 - len / 2 - CameraX
						, npc.ypos + npc.image.getIconHeight() + 27 - CameraY);
			}
			
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		// Mob Respawn Code
		if (System.currentTimeMillis() - start_time >= mob_spawn_period) {
			start_time = System.currentTimeMillis();
			for (int i = 0; i < Mob_List.size(); i++) {
				Mob temp = Mob_List.get(i);
				if (!temp.alive && temp.respawn && temp.alpha == 0f) {
					temp.Mob_Respawn();
				}
			}
		}

		// Draw Mobs
		int mob_icon_width, mob_icon_height, mob_offset, mob_rate, mob_xcenter;
		
		synchronized(Mob_List) {
			for(Mob mob : Mob_List) {
				mob_icon_width = mob.current_Img.getIconWidth();
				mob_icon_height = mob.current_Img.getIconHeight();
				mob_offset = mob.getOffset();
				//Mob Image
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, mob.alpha));
				if(mob.Direction == -1) {
					g2.drawImage(mob.current_Img.getImage(), mob.X - CameraX + mob_offset,
							mob.Y - mob_icon_height - CameraY, this);
				}
				else if(mob.Direction == 1) {
					int width = mob.getWidth();
					//flip image left and right
					g2.drawImage(mob.current_Img.getImage(), mob.X-CameraX+width-mob_icon_width-mob_offset, mob.Y-mob_icon_height-CameraY
							, mob.X-CameraX+width-mob_offset, mob.Y-CameraY, mob_icon_width, 0, 0, mob_icon_height, this);
				}
				
				//Mob HP Bar and Name
				if(!mob.isBoss) {
					if (mob.alive && (System.currentTimeMillis() >= mob.hit_time)
							&& (System.currentTimeMillis() <= mob.hit_time + 5000)) {
						mob_rate = (int) ((float)mob.HP / mob.getMaxHP() * 100);
						mob_xcenter = mob.X + mob.getWidth() / 2;
						g2.drawImage(Mob_HP_BarImg.getImage(), mob_xcenter - 28 - CameraX, mob.Y - mob_icon_height - CameraY - 11, 56, 9, this);
						g2.setPaint(Color.GREEN);
						g2.fillRect(mob_xcenter - 25 - CameraX, mob.Y - mob_icon_height - CameraY - 8,
								50 * mob_rate / 100, 3);
						
						len = metrics.stringWidth(mob.getName());
						g2.setColor(Color.BLACK);
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
						g2.fillRect(mob_xcenter - len / 2 - CameraX, mob.Y - CameraY, len, 15);
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
						g2.setFont(font);
						
						if(mob.getLevel() > Maplestory.player.Level + 20) {
							g2.setColor(Color.RED);
						}
						else if(mob.getLevel() < Maplestory.player.Level - 20) {
							g2.setColor(Color.YELLOW);
						}
						else {
							g2.setColor(Color.WHITE);
						}
						g2.drawString(mob.getName(), mob_xcenter - len / 2 - CameraX, mob.Y + 12 - CameraY);
						
					}
				}
				else {
					
				}
				
			}
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		// Draw Tomb
		if(Tomb_Alpha > 0f) {
			g2.drawImage(current_TombImg.getImage(), Tomb_X - current_TombImg.getIconWidth() / 2- CameraX,
					Tomb_Y - current_TombImg.getIconHeight() - CameraY, this);
		}
		
		// Draw Player
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Maplestory.player.alpha));
		if (Maplestory.player.CharDirection == -1) {
			g2.drawImage(
					Maplestory.player.current_Img.getImage(), Maplestory.player.CharacterX - CameraX
							- Maplestory.player.current_Img.getIconWidth() + Character.CharacterWidth,
					Maplestory.player.CharacterY - Character.CharacterHeight - CameraY, this);
		} else if (Maplestory.player.CharDirection == 1) {
			g2.drawImage(Maplestory.player.current_Img.getImage(), Maplestory.player.CharacterX - CameraX,
					Maplestory.player.CharacterY - Character.CharacterHeight - CameraY, this);
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		//Draw LevelUp
		
		synchronized(LevelUp_List) {
			Iterator<LevelUp> iter1 = LevelUp_List.iterator();
			LevelUp data;
			ImageIcon lvup_img;
			while(iter1.hasNext()) {
				data = iter1.next();
				if(data.remove) {
					iter1.remove();
				}
				else {
					lvup_img = data.current_Img;
					g2.drawImage(lvup_img.getImage(), Maplestory.player.CharacterX + Character.CharacterWidth/2 
							- Maplestory.images.LevelUp_Effect[0].getIconWidth() / 2 + data.current_x_offset - CameraX
							, Maplestory.player.CharacterY - Character.CharacterHeight + data.current_y_offset - CameraY, this);
				}
			}
		}
		
		
		// Draw Item
		AffineTransform old = g2.getTransform();
		
		synchronized(Item_List) {
			Iterator<Item> item_iter = Item_List.iterator();
			Item item_data;
			ImageIcon Item_Img;
			while(item_iter.hasNext()) {
				item_data = item_iter.next();
				if(item_data.getType().equals("Meso")) {
					Item_Img = item_data.getRawIcon();
					if(item_data.remove) {
						item_iter.remove();
					}
					else if(Item_Img != null) {
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, item_data.alpha));
						g2.drawImage(Item_Img.getImage(), item_data.X_Center - Item_Img.getIconWidth() / 2 - CameraX,
								item_data.Y_Center - Item_Img.getIconHeight() / 2 - CameraY + item_data.getOffset(), this);
					}
				}
				else {
					Item_Img = item_data.getRawIcon();
					if(item_data.remove) {
						item_iter.remove();
					}
					else if(Item_Img != null) {
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, item_data.alpha));
						g2.rotate(Math.toRadians(item_data.angle), item_data.X_Center - CameraX, item_data.Y_Center - CameraY);
						g2.drawImage(Item_Img.getImage(), item_data.X_Center - Item_Img.getIconWidth() / 2 - CameraX,
								item_data.Y_Center - Item_Img.getIconHeight() / 2 - CameraY, this);
					}
					g2.setTransform(old);
				}
			}
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		
		// Draw Portal
		for(Portal portal : Portal_List) {
			g2.drawImage(current_PortalImg.getImage(), portal.xcenter - 50 + Portal_Offset - CameraX,
					portal.y - current_PortalImg.getIconHeight() - CameraY, this);
		}
		
		// Draw Damage
		synchronized(Damage_List) {
			Iterator<Damage> damage_iter = Damage_List.iterator();
			Damage data;
			while(damage_iter.hasNext()) {
				data = damage_iter.next();
				if(data.alpha == 0f) {
					damage_iter.remove();
				}
				else {
					if(data.isAttack) {
						data.Show_Attack_Damage(g2, this, CameraX, CameraY);
					}
					else {
						data.Show_Hit_Damage(g2, this, CameraX, CameraY);
					}
				}
			}
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		// Show FPS
		g2.setPaint(Color.WHITE);
		g2.setFont(new Font("Dialog", Font.BOLD, 15));
		g2.drawString("FPS: " + saved_FPS, 730, 15);
		
		// Combat Messages
		g2.setFont(font);
		
		message_list_size = Message.combat_messages.size();
		if (message_list_size > 5) {
			message = Message.combat_messages.get(5);
			message_alpha = 1f - (current_time - message.getTime()) / showing_time;
			if (message_alpha > 0 && message_alpha <= 1) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, message_alpha));
				g2.setPaint(Color.BLACK);
				g2.drawString(message.getMsg(), 799 - message.getWidth() + 1, 375 + 1);
				g2.setPaint(Color.WHITE);
				g2.drawString(message.getMsg(), 799 - message.getWidth(), 375);
			}
		}
		if (message_list_size > 4) {
			message = Message.combat_messages.get(4);
			message_alpha = 1f - (current_time - message.getTime()) / showing_time;
			if (message_alpha > 0 && message_alpha <= 1) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, message_alpha));
				g2.setPaint(Color.BLACK);
				g2.drawString(message.getMsg(), 799 - message.getWidth() + 1, 390 + 1);
				g2.setPaint(Color.WHITE);
				g2.drawString(message.getMsg(), 799 - message.getWidth(), 390);
			}
		}
		if (message_list_size > 3) {
			message = Message.combat_messages.get(3);
			message_alpha = 1f - (current_time - message.getTime()) / showing_time;
			if (message_alpha > 0 && message_alpha <= 1) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, message_alpha));
				g2.setPaint(Color.BLACK);
				g2.drawString(message.getMsg(), 799 - message.getWidth() + 1, 405 + 1);
				g2.setPaint(Color.WHITE);
				g2.drawString(message.getMsg(), 799 - message.getWidth(), 405);
			}
		}
		if (message_list_size > 2) {
			message = Message.combat_messages.get(2);
			message_alpha = 1f - (current_time - message.getTime()) / showing_time;
			if (message_alpha > 0 && message_alpha <= 1) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, message_alpha));
				g2.setPaint(Color.BLACK);
				g2.drawString(message.getMsg(), 799 - message.getWidth() + 1, 420 + 1);
				g2.setPaint(Color.WHITE);
				g2.drawString(message.getMsg(), 799 - message.getWidth(), 420);
			}
		}
		if (message_list_size > 1) {
			message = Message.combat_messages.get(1);
			message_alpha = 1f - (current_time - message.getTime()) / showing_time;
			if (message_alpha > 0 && message_alpha <= 1) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, message_alpha));
				g2.setPaint(Color.BLACK);
				g2.drawString(message.getMsg(), 799 - message.getWidth() + 1, 435 + 1);
				g2.setPaint(Color.WHITE);
				g2.drawString(message.getMsg(), 799 - message.getWidth(), 435);
			}
		}
		if (message_list_size > 0) {
			message = Message.combat_messages.get(0);
			message_alpha = 1f - (current_time - message.getTime()) / showing_time;
			if (message_alpha > 0 && message_alpha <= 1) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, message_alpha));
				g2.setPaint(Color.BLACK);
				g2.drawString(message.getMsg(), 799 - message.getWidth() + 1, 450 + 1);
				g2.setPaint(Color.WHITE);
				g2.drawString(message.getMsg(), 799 - message.getWidth(), 450);
			}
		}
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		paintComponents(g2);
		
		//cursor's moving item
		if(move != null) {
			if(getMousePosition() != null) {
				MouseX = getMousePosition().x;
				MouseY = getMousePosition().y;
			}
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			g2.drawImage(move.getImage(), MouseX - 15, MouseY - 15, this);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		
		//cursor's item info
		if(move == null && info != null) {
			if(info_x + info.getIconWidth() >= 800) {
				info_x -= info.getIconWidth();
			}
			if(info_y + info.getIconHeight() >= 600) {
				info_y -= info.getIconHeight();
			}
			g2.drawImage(info.getImage(), info_x, info_y, this);
		}
		
		//BlackOut
		if(true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, BlackOut_Alpha));
			g2.drawImage(BlackOutImg.getImage(), 0, 0, this);
		}
	}

	public void Change_PortalImg() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				int i = 0;
				while(Available) {
					if(i == 0) {
						current_PortalImg = Maplestory.images.PortalImg0;
						Portal_Offset = 0;
					}
					else if(i == 1) {
						current_PortalImg = Maplestory.images.PortalImg1;
						Portal_Offset = 1;
					}
					else if(i == 2) {
						current_PortalImg = Maplestory.images.PortalImg2;
						Portal_Offset = -1;
					}
					else if(i == 3) {
						current_PortalImg = Maplestory.images.PortalImg3;
						Portal_Offset = -1;
					}
					else if(i == 4) {
						current_PortalImg = Maplestory.images.PortalImg4;
						Portal_Offset = -1;
					}
					else if(i == 5) {
						current_PortalImg = Maplestory.images.PortalImg5;
						Portal_Offset = -2;
					}
					else if(i == 6) {
						current_PortalImg = Maplestory.images.PortalImg6;
						Portal_Offset = 0;
					}
					else if(i == 7) {
						current_PortalImg = Maplestory.images.PortalImg7;
						Portal_Offset = 0;
					}
					i = (i+1)%8;
					try {
						Thread.sleep(120);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
			
		};
		Maplestory.thread_pool.submit(runnable);
	}
	

	
	//moves meteors(throwing stars)
	//x: meteor's x position, y: meteor's y position
	//xd: meteor's x direction, yd: meteor's y direction(1: Right, Down, -1: Left, Up, 0: Does not move)
	//distance: total distance that meteor moves, sleep: Thread sleep time, these determine the speed of meteor
	//visible: if meteor is visible when moving is done(true: visible, false: invisible)
	public void moveMeteor(MeteorInfo meteor) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				int xpos=meteor.getFirst_x();
				int ypos=meteor.getFirst_y();
				int xd=meteor.getXd();
				int yd=meteor.getYd();
				int distance=meteor.getDistance();
				int sleep=meteor.getSleep();
				meteor.setCurrent_visible(true);
				meteor.setCurrent_x(xpos);
				meteor.setCurrent_y(ypos);
				//move meteors(throwing stars)
				for(int i=0;i<distance;i++) {
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(StageNum == 2) {
						xpos+=xd;
						ypos+=yd;
						meteor.setCurrent_x(xpos);
						meteor.setCurrent_y(ypos);
					}
					else if(StageNum == 3) {
						if(i<distance/2) {
							xpos+=xd;
							ypos+=yd;
							meteor.setCurrent_x(xpos);
							meteor.setCurrent_y(ypos);
						}
						else {
							xpos-=xd;
							ypos-=yd;
							meteor.setCurrent_x(xpos);
							meteor.setCurrent_y(ypos);
						}
					}
					//character can't be attacked twice or more, which means character can be attacked again after landing to the ground
					if(!Stage.attacked && Stage.Hittable) {
						//character is attacked
						if((Maplestory.player.CharacterY - Character.CharacterHeight >= ypos-60)
								&& (Maplestory.player.CharacterY - Character.CharacterHeight <= ypos+meteor.getHeight()-10)) {
							if((Maplestory.player.CharacterX >= xpos-40) && (Maplestory.player.CharacterX <= xpos+meteor.getWidth()-10)){
								Maplestory.player.HP_Damage(1);
								if(StageNum == 2) {
									Maplestory.player.Character_Attacked1(1);
								}
								else if(StageNum == 3) {
									if(xd != 0) {
										if(i<distance/2) {
											Maplestory.player.Character_Attacked2(xd);
										}
										else {
											Maplestory.player.Character_Attacked2(-xd);
										}
									}
									else {
										Maplestory.player.Character_Attacked2(1);
									}
								}
							}
						}
					}
				}
				if(!meteor.isFirst_visible()) {
					meteor.setCurrent_visible(false);
				}
			}
			
		};
		
		Maplestory.thread_pool.submit(runnable);
	}	
	
	// setup means add to JFrame, JPanel
	public void setup() {
		add(Maplestory.ui_notice, 0);
		add(Maplestory.player.inventory, 1);
		add(Maplestory.generalStore, 2);
		add(Maplestory.ui_keySetting, 3);
		add(Maplestory.ui_minimap, 4);
		add(Maplestory.ui_status_bar, 5);
		add(Maplestory.ui_quick_slot, 6);
		for(int i=0;i<NPC_List.size();i++) {
			add(NPC_List.get(i), 7 + i);
		}
		BackButton.setEnabled(false);
		frame.add(this);
	}

	// open
	public void open(Portal portal) {
		if(Maplestory.current_stage == this && Maplestory.StageNow == StageNum) {
			reopen();
			return;
		}
		Maplestory.StageNow = StageNum;
		Maplestory.current_stage = this;
		setup();
		frame.setContentPane(this);

		if(Maplestory.isFullScreen) {
			Maplestory.device.setDisplayMode(new DisplayMode(800, 600, 32, 60));
		}
		
		frame.pack();
		
		Maplestory.keyConfig.setKeyBoard();
		Maplestory.keyConfig.setKeyBoard(this);
		
		if(MiniMap_Available) {
			Maplestory.ui_minimap.open();
		}
		else {
			Maplestory.ui_minimap.close();
		}
		
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < Mob_List.size(); i++) {
					Mob_List.get(i).available = true;
					Mob_List.get(i).stage = Maplestory.current_stage;
				}
				
				//mobs
				for (int i = 0; i < Mob_List.size(); i++) {
					if (Mob_List.get(i).available && !Mob_List.get(i).isStart && Mob_List.get(i).alive) {
						Mob_List.get(i).Mob_Start();
					}
				}

				Iterator<Timer> timer_iter = Timer_List.iterator();
				Timer timer;
				while(timer_iter.hasNext()) {
					timer = timer_iter.next();
					timer.start();
				}

				Tomb_Alpha = 0f;
				Tomb_Y = 0;
				
				if(portal == null) {
					Maplestory.player.CharDirection = CharFirstDirection;
					Maplestory.player.CharacterX = CharacterFirstX;
					Maplestory.player.CharacterY = CharacterFirstY;
				}
				else {
					Maplestory.player.CharacterX = portal.xcenter - Character.CharacterWidth / 2;
					Maplestory.player.CharacterY = portal.y - 10;
				}
				Maplestory.player.IsLandable();
				
				Maplestory.player.current_Img = CharacterFirstImg;
				Maplestory.player.alpha = 1f;

				if(portal == null) {
					CameraX = CameraFirstX;
					CameraY = CameraFirstY;
				}
				else {;
					SetCamera(Maplestory.player.CharacterX, Maplestory.player.CharacterY);
				}
				
				if (start_time == 0) {
					start_time = System.currentTimeMillis();
				}

				LeftKey = false;
				RightKey = false;
				UpKey = false;
				DownKey = false;
				Left = false;
				Right = false;
				Jump = false;
				Ladder = false;
				Up = false;
				Down = false;
				attacked = false;
				Attacking = false;
				Hittable = true;

				if(!Maplestory.player.alive) {
					Maplestory.player.HP_Heal(50);
					Maplestory.player.alive = true;
				}
				
/*				if(Maplestory.StageNow < 10) {
					Maplestory.player.HP = Maplestory.player.MaxHP;
				}
*/				
				
				for(float i=1f;i>0f;i-=0.01f) {
					BlackOut_Alpha = i;
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				BlackOut_Alpha = 0f;
				
				BackButton.setEnabled(true);
				Stage_Music.play();
				
				setFocusable(true);
				grabFocus();
				Available = true;
				Change_PortalImg();
				MoveCamera();
			}
			
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	public void reopen() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				setFocusable(false);
				BackButton.setEnabled(false);
				
				Available = false;
				LeftKey = false;
				RightKey = false;
				UpKey = false;
				DownKey = false;
				Left = false;
				Right = false;
				Jump = false;
				Ladder = false;
				Up = false;
				Down = false;
				attacked = false;
				Attacking = false;
				Hittable = false;
				
				for(float i=0f;i<1f;i+=0.002f) {
					BlackOut_Alpha = i;
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				BlackOut_Alpha = 1f;

				Tomb_Alpha = 0f;
				Tomb_Y = 0;

				Maplestory.player.CharDirection = CharFirstDirection;
				Maplestory.player.CharacterX = CharacterFirstX;
				Maplestory.player.CharacterY = CharacterFirstY;
				
				Maplestory.player.IsLandable();
				Maplestory.player.current_Img = CharacterFirstImg;
				Maplestory.player.alpha = 1f;
				
				CameraX = CameraFirstX;
				CameraY = CameraFirstY;
				
				Hittable = true;
				
				if(!Maplestory.player.alive) {
					Maplestory.player.HP_Heal(50);
					Maplestory.player.alive = true;
				}
				
		/*				if(Maplestory.StageNow < 10) {
					Maplestory.player.HP = Maplestory.player.MaxHP;
				}
		*/				
				
				for(float i=1f;i>0f;i-=0.01f) {
					BlackOut_Alpha = i;
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				BlackOut_Alpha = 0f;
				
				BackButton.setEnabled(true);
				
				setFocusable(true);
				grabFocus();
				Available = true;
				Change_PortalImg();
				MoveCamera();
			}
			
		};
		Maplestory.thread_pool.submit(runnable);
	}

	// close
	public void close(Portal portal) {
		setFocusable(false);
		BackButton.setEnabled(false);
		
		Available = false;
		LeftKey = false;
		RightKey = false;
		UpKey = false;
		DownKey = false;
		Left = false;
		Right = false;
		Jump = false;
		Ladder = false;
		Up = false;
		Down = false;
		attacked = false;
		Attacking = false;
		Hittable = false;
		
		for (int i = 0; i < Mob_List.size(); i++) {
			Mob_List.get(i).available = false;
		}
		
		Iterator<Timer> timer_iter = Timer_List.iterator();
		Timer timer;
		while(timer_iter.hasNext()) {
			timer = timer_iter.next();
			timer.stop();
		}
		
		for(float i=0f;i<1f;i+=0.002f) {
			BlackOut_Alpha = i;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		BlackOut_Alpha = 1f;
		
		Stage_Music.stop();
		frame.remove(this);
		
		if(portal == null) {
			if(Back_Pressed) {
				Maplestory.SS.open();
				Back_Pressed = false;
			}
		}
		else {
			portal.link.map.open(portal.link);
		}
	}

}