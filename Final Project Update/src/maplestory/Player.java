package maplestory;

import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.ImageIcon;

public class Player {
	protected static final int PlayerWidth = 50, PlayerHeight = 70;
	
	protected String name = "";
	
	//Position
	protected int PlayerDirection, PlayerX, PlayerY; //Direction: -1 Left / 1 Right
	protected Foothold cur_foothold = null;
	
	//Damage Skin
	protected Damage_Skin damage_skin = new Damage_Skin_Basic();
	
	//Status
	protected boolean alive = true;
	protected boolean pickable = true;
	protected boolean Item_usable = true;
	
	//Stat
	protected int MaxHP = 100, MaxMP = 100, HP = 100, MP = 100;
	protected int MinATK = 14, MaxATK = 20;
	protected int DEF = 0;
	protected int CriticalRate = 5;
	protected int Avoidability = 10;
	protected int Stance = 0;
	
	//Level
	protected int Level = 1;
	protected long MaxExp = 100, Exp = 0;
	
	//Inventory
	protected Inventory inventory = null;
	
	//Draw
	protected ImageIcon current_Img;
	protected float alpha = 1f;

	// Delays
	public static final int Move_Delay = 8;
	public static final int Ladder_Delay = 30;
	public static final int Ladder_Jump_Delay = 200;
	public static final int Attack_Delay = 400;
	public static final int Stroke_Delay = 100;
	public static final int Hit_Delay = 1000;
	
	//Music Name
	protected static String Jump_Music_Name = "Jump.wav";
	protected static String Attack_Music_Name = "Attack.wav";

	protected ImageIcon characterLeftImg = new ImageIcon(getClass().getClassLoader().getResource("CharacterLeft.png"));
	protected ImageIcon characterRightImg = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterRight.png"));
	protected ImageIcon characterJumpLeftImg = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterJumpLeft.png"));
	protected ImageIcon characterJumpRightImg = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterJumpRight.png"));
	protected ImageIcon characterWalkLeft1Img = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterWalkLeft1.png"));
	protected ImageIcon characterWalkLeft2Img = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterWalkLeft2.png"));
	protected ImageIcon characterWalkRight1Img = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterWalkRight1.png"));
	protected ImageIcon characterWalkRight2Img = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterWalkRight2.png"));
	protected ImageIcon characterProneLeftImg = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterProneLeft.png"));
	protected ImageIcon characterProneRightImg = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterProneRight.png"));
	protected ImageIcon characterLadder1Img = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterLadder1.png"));
	protected ImageIcon characterLadder2Img = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterLadder2.png"));
	protected ImageIcon characterAttackLeft1Img = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterAttackLeft1.png"));
	protected ImageIcon characterAttackLeft2Img = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterAttackLeft2.png"));
	protected ImageIcon characterAttackRight1Img = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterAttackRight1.png"));
	protected ImageIcon characterAttackRight2Img = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterAttackRight2.png"));
	protected ImageIcon characterDieLeftImg = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterDieLeft.png"));
	protected ImageIcon characterDieRightImg = new ImageIcon(
			getClass().getClassLoader().getResource("CharacterDieRight.png"));
	
	public Player(String _name) {
		name = _name;
		loadDB();
		inventory = new Inventory(name);
	}
	
	public void loadDB() {
		String query = "";
		PreparedStatement pstat = null;
		ResultSet resultSet = null;
		try {
			//player info
			query = "SELECT * FROM PLAYER WHERE NAME = '"+name+"'";
			resultSet = Maplestory.connection.createStatement().executeQuery(query);
			
			if(resultSet.next()) {
				Level = resultSet.getInt("LEV");
				HP = resultSet.getInt("HP");
				MaxHP = resultSet.getInt("MAXHP");
				MP = resultSet.getInt("MP");
				MaxMP = resultSet.getInt("MAXMP");
				Exp = resultSet.getInt("EXP");
				MaxExp = resultSet.getInt("MAXEXP");
				MinATK = resultSet.getInt("MINATK");
				MaxATK = resultSet.getInt("MAXATK");
				DEF = resultSet.getInt("DEF");
				CriticalRate = resultSet.getInt("CRITICAL_RATE");
				Avoidability = resultSet.getInt("AVOIDABILITY");
				Stance = resultSet.getInt("Stance");
			}
			else {
				query = "INSERT INTO PLAYER VALUES ('"+name+"', 1, 100, 100, 100, 100, 0, 100, 0, 1"
						+ ", 28, 40, 28, 28, 28, 14, 20, 0, 5, 10, 0)";
				Maplestory.connection.createStatement().executeUpdate(query);
			}

			//player quickslot
			query = "SELECT * FROM QUICKSLOT WHERE NAME = '"+name+"'";
			resultSet = Maplestory.connection.createStatement().executeQuery(query);
			
			if(!resultSet.next()) {
				query = "INSERT INTO QUICKSLOT VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				pstat = Maplestory.connection.prepareStatement(query);
				pstat.setString(1, name);
				pstat.setInt(2, KeyEvent.VK_SHIFT);
				pstat.setInt(3, KeyEvent.VK_INSERT);
				pstat.setInt(4, KeyEvent.VK_HOME);
				pstat.setInt(5, KeyEvent.VK_PAGE_UP);
				pstat.setInt(6, KeyEvent.VK_CONTROL);
				pstat.setInt(7, KeyEvent.VK_DELETE);
				pstat.setInt(8, KeyEvent.VK_END);
				pstat.setInt(9, KeyEvent.VK_PAGE_DOWN);
				
				pstat.executeUpdate();
			}
			
			//player keyConfig
			query = "SELECT * FROM KEYCONFIG WHERE NAME = '"+name+"'";
			resultSet = Maplestory.connection.createStatement().executeQuery(query);
			
			if(!resultSet.next()) {
				query = "INSERT INTO KEYCONFIG VALUES (?, ?, ?, ?, ?)";
				pstat = Maplestory.connection.prepareStatement(query);
				pstat.setString(1, name);
				pstat.setString(4, "");
				pstat.setInt(5, -1);
				
				pstat.setInt(2, KeyEvent.VK_ESCAPE);
				pstat.setString(3, "EscPress");
				pstat.executeUpdate();

				pstat.setInt(2, KeyEvent.VK_TAB);
				pstat.setString(3, "TabPress");
				pstat.executeUpdate();

				pstat.setInt(2, KeyEvent.VK_I);
				pstat.setString(3, "Inventory");
				pstat.executeUpdate();

				pstat.setInt(2, KeyEvent.VK_BACK_SLASH);
				pstat.setString(3, "KeyConfigAction");
				pstat.executeUpdate();

				pstat.setInt(2, KeyEvent.VK_ENTER);
				pstat.setString(3, "EnterPress");
				pstat.executeUpdate();

				pstat.setInt(2, KeyEvent.VK_SHIFT);
				pstat.setString(3, "ShiftPress");
				pstat.executeUpdate();

				pstat.setInt(2, KeyEvent.VK_Z);
				pstat.setString(3, "PickUp");
				pstat.executeUpdate();

				pstat.setInt(2, KeyEvent.VK_M);
				pstat.setString(3, "Minimap");
				pstat.executeUpdate();

				pstat.setInt(2, KeyEvent.VK_CONTROL);
				pstat.setString(3, "CtrlPress");
				pstat.executeUpdate();

				pstat.setInt(2, KeyEvent.VK_SPACE);
				pstat.setString(3, "NPC");
				pstat.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Update_Position(int x, int y) {
		synchronized(Maplestory.player) {
			PlayerX = x;
			PlayerY = y;
		}
	}
	
	public static void ManageAttacked() {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				Map.attacked = true;
				try {
					Thread.sleep(Hit_Delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Map.attacked = false;
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}
	public static void ManageHittable() {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				Map.Hittable = false;
				try {
					Thread.sleep(Hit_Delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Map.Hittable = true;
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}
	public static void ManageLadderJump() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Map.Ladder_Jump = true;
				if(Map.Ladder_Jump) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Map.Ladder_Jump = false;
				}
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	// Returns if character is landable on ground
	// x: character's x position, y: character's y position
	public boolean IsLandable() {
		int CX_CENTER = PlayerX+PlayerWidth/2;
		for (Foothold foothold : Maplestory.current_stage.Foothold_List) {
			if(foothold.isLandable(CX_CENTER, PlayerY)) {
				cur_foothold = foothold;
				Update_Position(PlayerX, cur_foothold.getY(CX_CENTER));
				return true;
			}
		}
		cur_foothold = null;
		return false;
	}

	// Returns if character can climb the ladder
	// x: character's x position, y: character's y position, direction: character's
	// direction(1 is UP, -1 is Down)
	public int IsLadderAvailable(int direction) {
		int xpos, ystart, yend, i = 0;
		for(Ladder ladder : Maplestory.current_stage.Ladder_List) {
			xpos = ladder.getX();
			ystart = ladder.getYstart();
			yend = ladder.getYend();
			// Up Direction
			if (direction == 1) {
				if ((xpos + 20 >= PlayerX + 25) && (xpos - 20 <= PlayerX + 25)
						&& (PlayerY > ystart) && (PlayerY - PlayerHeight <= yend)) {
					return i;
				}
			}
			// Down Direction
			else if (direction == -1) {
				if (Map.Ladder == false) {
					if ((xpos + 20 >= PlayerX + 25) && (xpos - 20 <= PlayerX + 25)
							&& (PlayerY == ystart)) {
						return i;
					}
				} else {
					if ((xpos + 20 >= PlayerX + 25) && (xpos - 20 <= PlayerX + 25)
							&& (PlayerY > ystart) && (PlayerY - PlayerHeight <= yend)) {
						return i;
					}
				}
			}
			i++;
		}
		return -1;
	}
	
	//true: can go, false: can't go
	public boolean wallCheck() {
		if(Map.Left) {
			for (Wall wall : Maplestory.current_stage.Wall_List) {
				if(!wall.canLeftGo(PlayerX, PlayerY, PlayerWidth, PlayerHeight)){
					return false;
				}
			}
		}
		else if(Map.Right) {
			for (Wall wall : Maplestory.current_stage.Wall_List) {
				if(!wall.canRightGo(PlayerX, PlayerY, PlayerWidth, PlayerHeight)){
					return false;
				}
			}
		}
		return true;
	}

	// When Character is attacked by Meteors(throwing stars)
	// xd: character's moving direction when attacked(-1: Left, 1; Right)
	public void Character_Attacked1(int xd) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				if(xd > 0) {
					Map.Left = false;
					Map.Right = true;
				}
				else {
					Map.Left = true;
					Map.Right = false;
				}
				Map.Jump = true;
				Map.Ladder = false;
				Map.attacked = true;
				ManageHittable();
				
				if (xd > 0) {
					current_Img = characterJumpLeftImg;
				} else {
					current_Img = characterJumpRightImg;
				}
				
				int x = PlayerX;
				int y = PlayerY;
				// character flies high
				while (alive && Map.Available && x > 0 && x < Maplestory.current_stage.X_Size-Player.PlayerWidth) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (wallCheck()) {
						x += xd;
					}
					if (y - PlayerHeight - 2 >= 0) {
						y -= 2;
					}

					Update_Position(x, y);
				}
				// character falls
				while (alive && Map.Available) {
					current_Img = characterJumpLeftImg;
					if (IsLandable()) {
						break;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (wallCheck()) {
						x += xd;
					}
					y += 2;
					Update_Position(x, y);
				}

				// Done

				if(alive && Map.Available) {
					Map.Left = false;
					Map.Right = false;
					Map.Jump = false;
					Map.attacked = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Map.Ladder == false && Map.Attacking == false) {
						if (PlayerDirection == 1) {
							if (Map.DownKey == true) {
								current_Img = characterProneRightImg;
							} else {
								current_Img = characterRightImg;
								if (Map.RightKey == true && Map.Right == false) {
									Character_Right();
								}
							}
						} else if (PlayerDirection == -1) {
							if (Map.DownKey == true) {
								current_Img = characterProneLeftImg;
							} else {
								current_Img = characterLeftImg;
								if (Map.LeftKey == true && Map.Left == false) {
									Character_Left();
								}
							}
						}
					}
				}
			}
		};

		if(alive && Map.Hittable && Map.Available) {
			Maplestory.thread_pool.submit(runnable);
		}
	}

	// When Character is attacked by Meteors(throwing stars)
	// xd: character's moving direction when attacked(-1: Left, 1; Right)
	public void Character_Attacked2(int xd) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				if(xd > 0) {
					Map.Left = false;
					Map.Right = true;
				}
				else {
					Map.Left = true;
					Map.Right = false;
				}
				Map.Jump = true;
				Map.Ladder = false;
				
				//can switch
				//Stage.attacked = true;
				ManageAttacked();
				ManageHittable();
				
				if (xd > 0) {
					current_Img = characterJumpLeftImg;
				} else {
					current_Img = characterJumpRightImg;
				}
				
				int x = PlayerX;
				int y = PlayerY;
				// character flies a little
				for (int i = 0; i < 13; i++) {
					if(!alive || !Map.Available) {
						break;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (wallCheck()) {
						x += xd;
					}
					if (y - PlayerHeight - 2 >= 0) {
						y -= 2;
					}

					Update_Position(x, y);
				}
				// character falls
				while (alive && Map.Available) {
					if (IsLandable()) {
						break;
					}
					else if(Map.Ladder) {
						return;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (wallCheck()) {
						x += xd;
					}
					y += 2;

					Update_Position(x, y);
				}

				
				// Done
				
				if(alive && Map.Available) {
					Map.Left = false;
					Map.Right = false;
					Map.Jump = false;
					Map.Ladder = false;
					Map.attacked = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Map.Ladder == false && Map.Attacking == false) {
						if (PlayerDirection == 1) {
							if (Map.DownKey == true) {
								current_Img = characterProneRightImg;
							} else {
								current_Img = characterRightImg;
								if (Map.RightKey == true && Map.Right == false) {
									Character_Right();
								}
							}
						} else if (PlayerDirection == -1) {
							if (Map.DownKey == true) {
								current_Img = characterProneLeftImg;
							} else {
								current_Img = characterLeftImg;
								if (Map.LeftKey == true && Map.Left == false) {
									Character_Left();
								}
							}
						}
					}
				}
			}

		};
		if(alive && Map.Hittable && Map.Available) {
			Maplestory.thread_pool.submit(runnable);
		}
	}

	// Move Left the Character
	public void Character_Left() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Map.Left = true;

				boolean islandable;
				int x = PlayerX;
				int y = PlayerY;
				int setWalkImg = 1;
				islandable = true;
				// character moves left
				while (Map.LeftKey == true) {
					if (Map.Left == false || Map.Ladder == true || Map.Attacking == true) {
						break;
					}
					if(Map.Jump == true || Map.attacked == true) {
						return;
					}
					if (!IsLandable()) {
						islandable = false;
						break;
					}

					if (setWalkImg == 1) {
						current_Img = characterWalkLeft1Img;
					} else if (setWalkImg == 31) {
						current_Img = characterWalkLeft2Img;
					}

					try {
						if(cur_foothold != null) {
							Thread.sleep((long)(Move_Delay * cur_foothold.getDistanceRatio(x+PlayerWidth/2)));
						}
						else {
							Thread.sleep(Move_Delay);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (wallCheck()) {
						x--;
						if(cur_foothold.getY(x+PlayerWidth/2) != -1) {
							y = cur_foothold.getY(x+PlayerWidth/2);
						}
						Update_Position(x, y);
					}
					if (setWalkImg == 60) {
						setWalkImg = 1;
					} else {
						setWalkImg++;
					}
				}
				
				y = PlayerY;
				// character falls
				if (!islandable) {
					Map.Jump = true;
					current_Img = characterJumpLeftImg;
					while (alive && Map.Available) {
						if (Map.Ladder == true || Map.attacked == true) {
							break;
						}
						try {
							Thread.sleep(Move_Delay);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						y += 2;
						if (wallCheck()) {
							x--;
							Update_Position(x, y);
						} else {
							Update_Position(x, y);
						}
						if (IsLandable()) {
							Map.Jump = false;
							break;
						}
					}
				}

				// Done

				if(alive && Map.Available) {
					Map.Left = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Map.Ladder == false && Map.attacked == false && Map.Attacking == false) {
						if (Map.DownKey == false) {
							if (!islandable) {
								if (PlayerDirection == 1) {
									current_Img = characterRightImg;
									if (Map.RightKey == true && Map.Right == false) {
										Character_Right();
									}
								} else if (PlayerDirection == -1) {
									current_Img = characterLeftImg;
									if (Map.LeftKey == true && Map.Left == false) {
										Character_Left();
									}
								}
							} else if (Map.Right == false && Map.Jump == false && Map.Attacking == false) {
								current_Img = characterLeftImg;
							}
						} else {
							if (!islandable) {
								if (PlayerDirection == 1) {
									if (Map.RightKey == true && Map.Right == false) {
										current_Img = characterRightImg;
										Character_Right();
									} else {
										current_Img = characterProneRightImg;
									}
								} else if (PlayerDirection == -1) {
									if (Map.LeftKey == true && Map.Left == false) {
										current_Img = characterLeftImg;
										Character_Left();
									} else {
										current_Img = characterProneLeftImg;
									}
								}
							} else if (PlayerDirection == 1) {
								current_Img = characterProneRightImg;
							} else if (PlayerDirection == -1) {
								current_Img = characterProneLeftImg;
							}
						}
					} else {
						Map.Jump = false;
					}
				}
			}

		};

		Maplestory.thread_pool.submit(runnable);

	}

	// Move Right the Character
	public void Character_Right() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Map.Right = true;

				boolean islandable;
				int x = PlayerX;
				int y = PlayerY;
				int setWalkImg = 1;
				islandable = true;
				// character moves right
				while (Map.RightKey == true) {
					if (Map.Right == false || Map.Ladder == true || Map.Attacking == true) {
						break;
					}
					if(Map.Jump == true || Map.attacked == true) {
						return;
					}
					if (!IsLandable()) {
						islandable = false;
						break;
					}

					if (setWalkImg == 1) {
						current_Img = characterWalkRight1Img;
					} else if (setWalkImg == 31) {
						current_Img = characterWalkRight2Img;
					}

					try {
						if(cur_foothold != null) {
							Thread.sleep((long)(Move_Delay * cur_foothold.getDistanceRatio(x+PlayerWidth/2)));
						}
						else {
							Thread.sleep(Move_Delay);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (wallCheck()) {
						x++;
						if(cur_foothold.getY(x+PlayerWidth/2) != -1) {
							y = cur_foothold.getY(x+PlayerWidth/2);
						}
						Update_Position(x, y);
					}

					if (setWalkImg == 60) {
						setWalkImg = 1;
					} else {
						setWalkImg++;
					}
				}
				
				y = PlayerY;
				// character falls
				if (!islandable) {
					Map.Jump = true;
					current_Img = characterJumpRightImg;
					while (alive && Map.Available) {
						if (Map.Ladder == true || Map.attacked == true) {
							break;
						}
						try {
							Thread.sleep(Move_Delay);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						y += 2;
						if (wallCheck()) {
							x++;
							Update_Position(x, y);
						} else {
							Update_Position(x, y);
						}
						if (IsLandable()) {
							Map.Jump = false;
							break;
						}
						if (setWalkImg == 60) {
							setWalkImg = 1;
						} else {
							setWalkImg++;
						}
					}
				}

				// Done

				if(alive && Map.Available) {
					Map.Right = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Map.Ladder == false && Map.attacked == false && Map.Attacking == false) {
						if (Map.DownKey == false) {
							if (!islandable) {
								if (PlayerDirection == 1) {
									current_Img = characterRightImg;
									if (Map.RightKey == true && Map.Right == false) {
										Character_Right();
									}
								} else if (PlayerDirection == -1) {
									current_Img = characterLeftImg;
									if (Map.LeftKey == true && Map.Left == false) {
										Character_Left();
									}
								}
							} else if (Map.Left == false && Map.Jump == false && Map.Attacking == false) {
								current_Img = characterRightImg;
							}
						} else {
							if (!islandable) {
								if (PlayerDirection == 1) {
									if (Map.RightKey == true && Map.Right == false) {
										current_Img = characterRightImg;
										Character_Right();
									} else {
										current_Img = characterProneRightImg;
									}
								} else if (PlayerDirection == -1) {
									if (Map.LeftKey == true && Map.Left == false) {
										current_Img = characterLeftImg;
										Character_Left();
									} else {
										current_Img = characterProneLeftImg;
									}
								}
							} else if (PlayerDirection == 1) {
								current_Img = characterProneRightImg;
							} else if (PlayerDirection == -1) {
								current_Img = characterProneLeftImg;
							}
						}
					} else {
						Map.Jump = false;
					}
				}
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	// Jump In Place the Character
	public void Character_Jump() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Music Jump_Music = new Music(Jump_Music_Name, 1);
				Jump_Music.play();
				
				Map.Jump = true;
				
				int x = PlayerX;
				int y = PlayerY;
				// jump up
				for (int i = 0; i < 30; i++) {
					if (!alive || !Map.Available) {
						break;
					}
					if(Map.Ladder) {
						Map.Jump = false;
						return;
					}
					if(Map.attacked) {
						return;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (y - PlayerHeight - 2 >= 0) {
						y -= 2;
						Update_Position(x, y);
					} else {
						break;
					}
				}
				// jump down
				while (alive && Map.Available) {
					if(Map.Ladder) {
						Map.Jump = false;
						return;
					}
					if(Map.attacked) {
						return;
					}
					if (IsLandable()) {
						break;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					y += 2;
					Update_Position(x, y);
				}

				// Done

				if(alive && Map.Available) {
					Map.Jump = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Map.Ladder == false && Map.attacked == false && Map.Attacking == false) {
						if (PlayerDirection == 1) {
							if (Map.DownKey == true) {
								current_Img = characterProneRightImg;
							} else {
								current_Img = characterRightImg;
								if (Map.RightKey == true && Map.Right == false) {
									Character_Right();
								}
							}
						} else if (PlayerDirection == -1) {
							if (Map.DownKey == true) {
								current_Img = characterProneLeftImg;
							} else {
								current_Img = characterLeftImg;
								if (Map.LeftKey == true && Map.Left == false) {
									Character_Left();
								}
							}
						}
					} else {
						Map.Jump = false;
					}
				}
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	// Jump Left the Character
	public void Character_LeftJump(boolean onLadder) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Map.Left = true;
				Map.Jump = true;
				Map.Ladder = false;
				
				Music Jump_Music = new Music(Jump_Music_Name, 1);
				Jump_Music.play();
				
				int height = 0;
				if(onLadder) {
					height = 15;
				}
				else {
					height = 30;
				}
				
				int x = PlayerX;
				int y = PlayerY;
				// jump up
				for (int i = 0; i < height; i++) {
					if (!alive || !Map.Available) {
						break;
					}
					if(Map.Ladder) {
						Map.Left = false;
						Map.Jump = false;
						ManageLadderJump();
						return;
					}
					if(Map.attacked) {
						return;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (wallCheck()) {
						x--;
					}
					if (y - PlayerHeight - 2 >= 0) {
						y -= 2;
					} else {
						break;
					}
					Update_Position(x, y);
				}
				// jump down
				while (alive && Map.Available) {
					if(Map.Ladder) {
						Map.Left = false;
						Map.Jump = false;
						ManageLadderJump();
						return;
					}
					if(Map.attacked) {
						return;
					}
					if (IsLandable()) {
						break;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (wallCheck()) {
						x--;
					}
					y += 2;
					Update_Position(x, y);
				}

				// Done

				if(alive && Map.Available) {
					Map.Left = false;
					Map.Jump = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Map.Ladder == false && Map.attacked == false && Map.Attacking == false) {
						if (PlayerDirection == 1) {
							if (Map.DownKey == true) {
								current_Img = characterProneRightImg;
							} else {
								current_Img = characterRightImg;
								if (Map.RightKey == true && Map.Right == false) {
									Character_Right();
								}
							}
						} else if (PlayerDirection == -1) {
							if (Map.DownKey == true) {
								current_Img = characterProneLeftImg;
							} else {
								current_Img = characterLeftImg;
								if (Map.LeftKey == true && Map.Left == false) {
									Character_Left();
								}
							}
						}
					} else {
						Map.Jump = false;
					}
				}
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	// Jump Right the Character
	public void Character_RightJump(boolean onLadder) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Map.Right = true;
				Map.Jump = true;
				Map.Ladder = false;
				
				Music Jump_Music = new Music(Jump_Music_Name, 1);
				Jump_Music.play();
				
				int height = 0;
				if(onLadder) {
					height = 15;
				}
				else {
					height = 30;
				}
				
				int x = PlayerX;
				int y = PlayerY;
				// jump up
				for (int i = 0; i < height; i++) {
					if (!alive || !Map.Available) {
						break;
					}
					if(Map.Ladder) {
						Map.Right = false;
						Map.Jump = false;
						ManageLadderJump();
						return;
					}
					if(Map.attacked) {
						return;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (wallCheck()) {
						x++;
					}
					if (y - PlayerHeight - 2 >= 0) {
						y -= 2;
					} else {
						break;
					}
					Update_Position(x, y);
				}
				// jump down
				while (alive && Map.Available) {
					if(Map.Ladder) {
						Map.Right = false;
						Map.Jump = false;
						ManageLadderJump();
						return;
					}
					if(Map.attacked) {
						return;
					}
					if (IsLandable()) {
						break;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (wallCheck()) {
						x += 1;
					}
					y += 2;
					Update_Position(x, y);
				}

				// Done

				if(alive && Map.Available) {
					Map.Right = false;
					Map.Jump = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Map.Ladder == false && Map.attacked == false && Map.Attacking == false) {
						if (PlayerDirection == 1) {
							if (Map.DownKey == true) {
								current_Img = characterProneRightImg;
							} else {
								current_Img = characterRightImg;
								if (Map.RightKey == true && Map.Right == false) {
									Character_Right();
								}
							}
						} else if (PlayerDirection == -1) {
							if (Map.DownKey == true) {
								current_Img = characterProneLeftImg;
							} else {
								current_Img = characterLeftImg;
								if (Map.LeftKey == true && Map.Left == false) {
									Character_Left();
								}
							}
						}
					} else {
						Map.Jump = false;
					}
				}
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	// Jump Down the Character
	public void Character_DownJump() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Map.Jump = true;
				
				Music Jump_Music = new Music(Jump_Music_Name, 1);
				Jump_Music.play();
				
				int x = PlayerX;
				int y = PlayerY;
				// jump down
				while (alive && Map.Available) {
					if (Map.attacked) {
						return;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					y += 2;
					Update_Position(x, y);
					if (IsLandable()) {
						break;
					}
				}

				// Done

				if(alive && Map.Available) {
					Map.Jump = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Map.attacked == false && Map.Attacking == false) {
						if (PlayerDirection == 1) {
							if (Map.DownKey == true) {
								current_Img = characterProneRightImg;
							} else {
								current_Img = characterRightImg;
								if (Map.RightKey == true && Map.Right == false) {
									Character_Right();
								}
							}
						} else if (PlayerDirection == -1) {
							if (Map.DownKey == true) {
								current_Img = characterProneLeftImg;
							} else {
								current_Img = characterLeftImg;
								if (Map.LeftKey == true && Map.Left == false) {
									Character_Left();
								}
							}
						}
					}
				}
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	// Move Up the Character when on ladder
	// idx: ladder array idx
	public void Character_LadderUp(int idx) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Map.Ladder = true;
				Map.Up = true;
				Map.Jump = false;
				
				Ladder ladder = Maplestory.current_stage.Ladder_List.get(idx);
				int xpos = ladder.getX();
				int ystart = ladder.getYstart();
				int yend = ladder.getYend();
				
				Update_Position(xpos - 25, PlayerY);
				
				int x = PlayerX;
				int y = PlayerY;
				int setLadderImg = 1;

				while (Map.UpKey) {
					if(Map.Jump || Map.attacked) {
						Map.Up = false;
						Map.Ladder = false;
						return;
					}
					if (setLadderImg == 1) {
						current_Img = characterLadder1Img;
					} else if (setLadderImg == 11) {
						current_Img = characterLadder2Img;
					}

					try {
						Thread.sleep(Ladder_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if ((y - 2 >= ystart) && (y - PlayerHeight - 2 <= yend)) {
						y -= 2;
						Update_Position(x, y);
					}
					if (setLadderImg == 20) {
						setLadderImg = 1;
					} else {
						setLadderImg++;
					}
					if (y == ystart) {
						IsLandable();
						Map.Ladder = false;
						break;
					}
				}

				// Done

				if(alive && Map.Available) {
					Map.Up = false;
					// setting character's state(where character is looking) according to direction
					if (Map.Ladder == false && Map.attacked == false && Map.Attacking == false) {
						if (PlayerDirection == -1) {
							current_Img = characterLeftImg;
						} else if (PlayerDirection == 1) {
							current_Img = characterRightImg;
						}
					}
				}
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	// Move Down the Character when on ladder
	// idx: ladder array idx
	public void Character_LadderDown(int idx) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Map.Ladder = true;
				Map.Down = true;

				Ladder ladder = Maplestory.current_stage.Ladder_List.get(idx);
				int xpos = ladder.getX();
				int ystart = ladder.getYstart();
				int yend = ladder.getYend();
				
				Update_Position(xpos - 25, PlayerY);
				
				boolean islandable;
				int x = PlayerX;
				int y = PlayerY;
				int setLadderImg = 1;
				islandable = false;

				while (Map.DownKey) {
					if (Map.attacked) {
						Map.Down = false;
						Map.Ladder = false;
						return;
					}
					if (setLadderImg == 1) {
						current_Img = characterLadder1Img;
					} else if (setLadderImg == 11) {
						current_Img = characterLadder2Img;
					}

					try {
						Thread.sleep(Ladder_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if ((y + 2 >= ystart) && (y - PlayerHeight + 2 <= yend)) {
						y += 2;
						Update_Position(x, y);
					}
					if (setLadderImg == 20) {
						setLadderImg = 1;
					} else {
						setLadderImg++;
					}
					if ((ystart < y) && ((y - PlayerHeight == yend) || (islandable = IsLandable()))) {
						Map.Ladder = false;
						break;
					}
				}

				// character falls
				if (!islandable && !Map.Ladder) {
					Map.Down = false;
					Map.Jump = true;
					current_Img = characterJumpLeftImg;
					while (alive && Map.Available) {
						if (Map.attacked) {
							return;
						}
						if (IsLandable()) {
							Map.Jump = false;
							islandable = true;
							break;
						}
						try {
							Thread.sleep(Move_Delay);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						y += 2;
						Update_Position(x, y);
					}
				}

				// Done

				if(alive && Map.Available) {
					Map.Down = false;
					// setting character's state(where character is looking) according to direction
					if (islandable == true && Map.Ladder == false && Map.Attacking == false) {
						if (PlayerDirection == -1) {
							current_Img = characterLeftImg;
						} else if (PlayerDirection == 1) {
							current_Img = characterRightImg;
						}
					}
				}
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	public void Character_Attack(int target_num, int Range_Xfront, int Range_Xbehind, int Range_Yabove, int Range_Ybelow,
			int stroke_num, int Damage_Percent) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Map.Attacking = true;
				
				int Player_Xcenter = PlayerX + PlayerWidth / 2;
				int Player_Ycenter = PlayerY - PlayerHeight / 2;
				int count = 0;

				synchronized(Maplestory.current_stage.Mob_List) {
					Maplestory.current_stage.Mob_List.sort(new Comparator<Mob>() {
	
						@Override
						public int compare(Mob o1, Mob o2) {
							if (!o1.available || !o2.available) {
								return 0;
							}
							else{
								int Delta_X1 = o1.X + o1.current_Img.getIconWidth() / 2 - Player_Xcenter;
								int Delta_X2 = o2.X + o2.current_Img.getIconWidth() / 2 - Player_Xcenter;
								int Delta_Y1 = o1.Y + o1.current_Img.getIconHeight() / 2 - Player_Ycenter;
								int Delta_Y2 = o2.Y + o2.current_Img.getIconHeight() / 2 - Player_Ycenter;
								
								if (Delta_X1*Delta_X1 + Delta_Y1*Delta_Y1 > Delta_X2*Delta_X2 + Delta_Y2*Delta_Y2) {
									return +1;
								} else {
									return -1;
								}
							}
						}
	
					});
				}

				if (PlayerDirection == -1) {
					current_Img = characterAttackLeft1Img;
					try {
						Thread.sleep(Attack_Delay);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Music Attack_Music = new Music(Attack_Music_Name, 1);
					Attack_Music.play();
					
					for(Mob temp : Maplestory.current_stage.Mob_List) {
						int Mob_Xstart = temp.X;
						int Mob_Xend = temp.X + temp.getWidth();
						int Mob_Ystart = temp.Y - temp.getHeight();
						int Mob_Yend = temp.Y;
						int Range_Xstart = Player_Xcenter - Range_Xfront;
						int Range_Xend = Player_Xcenter + Range_Xbehind;
						int Range_Ystart = Player_Ycenter - Range_Yabove;
						int Range_Yend = Player_Ycenter + Range_Ybelow;

						if (temp.available && temp.alive) {
							if ((Mob_Xend >= Range_Xstart && Mob_Xstart <= Range_Xend)
									&& (Mob_Yend >= Range_Ystart && Mob_Ystart <= Range_Yend)) {
								temp.hit(stroke_num, Damage_Percent);
								count++;
								if (count == target_num) {
									break;
								}
							}
						}
					}
					current_Img = characterAttackLeft2Img;
					try {
						Thread.sleep(Attack_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					current_Img = characterLeftImg;
				} else if (PlayerDirection == 1) {
					current_Img = characterAttackRight1Img;
					try {
						Thread.sleep(Attack_Delay);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Music Attack_Music = new Music(Attack_Music_Name, 1);
					Attack_Music.play();

					for(Mob temp : Maplestory.current_stage.Mob_List) {
						int Mob_Xstart = temp.X;
						int Mob_Xend = temp.X + temp.current_Img.getIconWidth();
						int Mob_Ystart = temp.Y - temp.current_Img.getIconHeight();
						int Mob_Yend = temp.Y;
						int Range_Xstart = Player_Xcenter - Range_Xbehind;
						int Range_Xend = Player_Xcenter + Range_Xfront;
						int Range_Ystart = Player_Ycenter - Range_Yabove;
						int Range_Yend = Player_Ycenter + Range_Ybelow;

						if (temp.available && temp.alive) {
							if ((Mob_Xend >= Range_Xstart && Mob_Xstart <= Range_Xend)
									&& (Mob_Yend >= Range_Ystart && Mob_Ystart <= Range_Yend)) {
								temp.hit(stroke_num, Damage_Percent);
								count++;
								if (count == target_num) {
									break;
								}
							}
						}
					}
					current_Img = characterAttackRight2Img;
					try {
						Thread.sleep(Attack_Delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					current_Img = characterRightImg;
				}

				Map.Attacking = false;
				if (PlayerDirection == 1) {
					if (Map.DownKey == true) {
						current_Img = characterProneRightImg;
					} else {
						current_Img = characterRightImg;
						if (Map.RightKey == true && Map.Right == false) {
							Character_Right();
						}
					}
				} else if (PlayerDirection == -1) {
					if (Map.DownKey == true) {
						current_Img = characterProneLeftImg;
					} else {
						current_Img = characterLeftImg;
						if (Map.LeftKey == true && Map.Left == false) {
							Character_Left();
						}
					}
				}
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}
	
	public void HP_Damage(int damage) {
		synchronized(Maplestory.current_stage.Damage_List) {
			Maplestory.current_stage.Damage_List.add(new Damage(damage, PlayerX + PlayerWidth / 2, PlayerY - PlayerHeight, false, false));
		}
		if(HP <= damage) {
			HP = 0;
			Character_Die();
		}
		else {
			HP -= damage;
		}
		Maplestory.ui_status_bar.Manage_HpBar();
	}
	public void HP_Heal(int heal) {
		if(HP + heal > MaxHP) {
			HP = MaxHP;
		}
		else {
			HP += heal;
		}
		Maplestory.ui_status_bar.Manage_HpBar();
	}
	public void MP_Use(int cost) {
		MP -= cost;
		Maplestory.ui_status_bar.Manage_MpBar();
	}
	public void MP_Heal(int heal) {
		if(MP + heal > MaxMP) {
			MP = MaxHP;
		}
		else {
			MP += heal;
		}
		Maplestory.ui_status_bar.Manage_MpBar();
	}

	public void Character_Die() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Music Tombstone_Music = new Music("Tombstone.wav", 1);
				Tombstone_Music.play();
				
				loseExp();
				
				alive = false;
				Map.LeftKey = false;
				Map.RightKey = false;
				Map.UpKey = false;
				Map.DownKey = false;
				Map.Left = false;
				Map.Right = false;
				Map.Jump = false;
				Map.Ladder = false;
				Map.Up = false;
				Map.Down = false;
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				while(!IsLandable()) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					PlayerY+=2;
				}
				
				Show_Tomb(PlayerX+PlayerWidth/2, PlayerY);
				
				alpha = 0.7f;
				if(PlayerDirection == -1) {
					current_Img = characterDieLeftImg;
				}
				else if(PlayerDirection == 1) {
					current_Img = characterDieRightImg;
				}
				
				Map.Hittable = false;
				
				if(Maplestory.ui_notice.open(UI_Notice.DIE_NOTICE, "")) {
					while(UI_Notice.isOpen) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					if(UI_Notice.status == UI_Notice.OK) {
						Map nearestTown = Maplestory.current_stage.nearestTown;
						if(nearestTown == Maplestory.current_stage) {
							Maplestory.current_stage.reopen();
						}
						else {
							Maplestory.current_stage.close(null);
							nearestTown.open(null);
						}
					}
				}
			}
			
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	public void Show_Tomb(int X, int Y) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Maplestory.current_stage.Tomb_Alpha = 1f;
				Maplestory.current_stage.Tomb_X = X;
				Maplestory.current_stage.Tomb_Y = 0;
				
				for(int i=0;i<20;i++) {
					if(i<12) {
						if(i==0) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg0;
						}
						else if(i==1) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg1;
						}
						else if(i==2) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg2;
						}
						else if(i==3) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg3;
						}
						else if(i==4) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg4;
						}
						else if(i==5) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg5;
						}
						else if(i==6) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg6;
						}
						else if(i==7) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg7;
						}
						else if(i==8) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg8;
						}
						else if(i==9) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg9;
						}
						else if(i==10) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg10;
						}
						else if(i==11) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg11;
						}
						Maplestory.current_stage.Tomb_Y += Y / 12;
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					else {
						Maplestory.current_stage.Tomb_Y = Y;
						if(i==12) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg12;
						}
						else if(i==13) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg13;
						}
						else if(i==14) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg14;
						}
						else if(i==15) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg15;
						}
						else if(i==16) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg16;
						}
						else if(i==17) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg17;
						}
						else if(i==18) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg18;
						}
						else if(i==19) {
							Maplestory.current_stage.current_TombImg = Maplestory.images.TombImg19;
						}
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	public void Character_PickUp_Item() {
		int Char_X_Center = PlayerX + PlayerWidth / 2;
		int Char_Y =PlayerY - PlayerHeight * 1 / 6;
		synchronized (Maplestory.current_stage.Item_List) {
			Iterator<Item> iter1 = Maplestory.current_stage.Item_List.iterator();
			while (iter1.hasNext()) {
				Item data = iter1.next();
				if (Char_X_Center + 15 > data.X_Center - data.getRawIcon().getIconWidth() / 2
						&& Char_X_Center - 15 < data.X_Center + data.getRawIcon().getIconWidth() / 2
						&& Char_Y + 10 > data.Y_Center - data.getRawIcon().getIconHeight() / 2
						&& Char_Y - 10 < data.Y_Center + data.getRawIcon().getIconHeight() / 2) {
					if (data.pickable) {
						Character_Get_Item(data, true);
						break;
					}
				}
			}
		}
	}
	
	public void Character_Get_Item(Item item, boolean pickup) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				ArrayList<Item> inventory_list = null;
				int size = 0;
				String msg = "";

				String query = "";
				PreparedStatement pstat = null;
				
				if(item.getType().equals("Meso")) {
					item.pickable = false;
					pickable = false;
					gainMeso(item.Quantity);
					
					if(pickup) {
						msg = Make_CombatMsg(item);
						synchronized(Message.combat_messages) {
							if(Message.combat_messages.size() == Message.max_messages) {
								Message.combat_messages.removeLast();
							}
							Message.combat_messages.addFirst(new Message("Combat", msg, System.currentTimeMillis()
									, Maplestory.current_stage.getFontMetrics().stringWidth(msg)));
						}
					}
				}
				else {
					if(item.getType().equals("Equip")){
						inventory_list = inventory.Equip_inventory_list;
						size = inventory.Equip_size;
					}
					else if(item.getType().equals("Consume")){
						inventory_list = inventory.Consume_inventory_list;
						size = inventory.Consume_size;
					}
					else if(item.getType().equals("Etc")){
						inventory_list = inventory.Etc_inventory_list;
						size = inventory.Etc_size;
					}
					else if(item.getType().equals("Install")){
						inventory_list = inventory.Install_inventory_list;
						size = inventory.Install_size;
					}
					else if(item.getType().equals("Cash")){
						inventory_list = inventory.Cash_inventory_list;
						size = inventory.Cash_size;
					}
						
					synchronized(inventory_list) {
						int empty_index = -1;
						for(int i=0;i<size;i++) {
							Item data = inventory_list.get(i);
							if(data == null) {
								if(empty_index == -1) {
									empty_index = i;
								}
							}
							else {
								if((item.getItemCode() == data.getItemCode()) && (item.getType().equals(data.getType()))) {
									item.pickable = false;
									pickable = false;
									data.Quantity += item.Quantity;
									inventory_list.set(i, data);
									
									try {
										query = "UPDATE "+data.getType()+"_INVENTORY "
												+"SET QUANTITY = ? WHERE NAME = ? and IDX = ? and ITEMCODE = ?";
										pstat = Maplestory.connection.prepareStatement(query);
										pstat.setInt(1, data.Quantity);
										pstat.setString(2, Maplestory.player.name);
										pstat.setInt(3, i);
										pstat.setInt(4, data.getItemCode());
										
										pstat.executeUpdate();
									} catch (SQLException e) {
										e.printStackTrace();
									}
									
									if(pickup) {
										msg = Make_CombatMsg(item);
										synchronized(Message.combat_messages) {
											if(Message.combat_messages.size() == Message.max_messages) {
												Message.combat_messages.removeLast();
											}
											Message.combat_messages.addFirst(new Message("Combat", msg, System.currentTimeMillis()
													, Maplestory.current_stage.getFontMetrics().stringWidth(msg)));
										}
									}
								}
							}
						}
						
						if(pickable && (empty_index != -1)) {
							item.pickable = false;
							pickable = false;
							inventory_list.set(empty_index, item.getNew(item.Quantity));
							Maplestory.player.inventory.updateNullRemovedList(item.getType());
							
							try {
								query = "INSERT INTO "+item.getType()+"_INVENTORY (NAME, IDX, ITEMCODE, QUANTITY) "
										+"VALUES (?, ?, ?, ?)";
								pstat = Maplestory.connection.prepareStatement(query);
								pstat.setString(1, Maplestory.player.name);
								pstat.setInt(2, empty_index);
								pstat.setInt(3, item.getItemCode());
								pstat.setInt(4, item.Quantity);
								
								pstat.executeUpdate();
							} catch (SQLException e) {
								e.printStackTrace();
							}
							
							if(pickup) {
								msg = Make_CombatMsg(item);
								synchronized(Message.combat_messages) {
									if(Message.combat_messages.size() == Message.max_messages) {
										Message.combat_messages.removeLast();
									}
									Message.combat_messages.addFirst(new Message("Combat", msg, System.currentTimeMillis()
											, Maplestory.current_stage.getFontMetrics().stringWidth(msg)));
								}
							}
						}
					}
				}
	
	
				if(!pickable) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					pickable = true;
				}
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	public void Character_Drop_Item() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				if(alive) {
					int num = 0;
					if(Maplestory.ui_notice.open(UI_Notice.WITH_TEXTFIELD | UI_Notice.WITH_OK | UI_Notice.WITH_CANCEL
							, "몇 개나 버리시겠습니까?")) {
						while(UI_Notice.isOpen) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						if(UI_Notice.status == UI_Notice.OK) {
							num = UI_Notice.value;
							synchronized(inventory.current_inventory_list) {
								Item data = inventory.current_inventory_list.get(inventory.move_index);
								if(num == 0) {
									Maplestory.ui_notice.open(UI_Notice.WITH_OK, "1 이상의 숫자만 가능합니다.");
								}
								else if(data.Quantity >= num) {
									Item item_drop = data.getNew(num);
									inventory.Reduce_Item(inventory.move_index, num);
									item_drop.Drop(PlayerX+Player.PlayerWidth/2,
											PlayerY-Player.PlayerHeight/2);
								}
								else {
									Maplestory.ui_notice.open(UI_Notice.WITH_OK, data.Quantity + " 이하의 숫자만 가능합니다.");
								}
							}
						}
					}
				}
			}
			
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	public void Character_Drop_Meso() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				if(alive) {
					int num = 0;
					if(Maplestory.ui_notice.open(UI_Notice.WITH_TEXTFIELD | UI_Notice.WITH_OK | UI_Notice.WITH_CANCEL
							, "얼마를 버리시겠습니까?")) {
						while(UI_Notice.isOpen) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						if(UI_Notice.status == UI_Notice.OK) {
							num = UI_Notice.value;
							if(num == 0) {
								Maplestory.ui_notice.open(UI_Notice.WITH_OK, "1 이상의 숫자만 가능합니다.");
							}
							else if(Maplestory.player.inventory.Meso >= num) {
								Item item_drop = new Item_Meso(num);
								loseMeso(num);
								item_drop.Drop(PlayerX+Player.PlayerWidth/2,
										PlayerY-Player.PlayerHeight/2);
							}
							else {
								Maplestory.ui_notice.open(UI_Notice.WITH_OK, Maplestory.player.inventory.Meso + " 이하의 숫자만 가능합니다.");
							}
						}
					}
				}
			}
			
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	public void Character_TalktoNPC() {
		if(!Shop.isOpen && !UI_Notice.isOpen) {
			for(NPC npc : Maplestory.current_stage.NPC_List) {
				if(((Maplestory.player.PlayerX + Player.PlayerWidth / 2) >= npc.xcenter - 100)
						&& ((Maplestory.player.PlayerX + Player.PlayerWidth / 2) <= npc.xcenter + 100)
						&& ((Maplestory.player.PlayerY - Player.PlayerHeight / 2) >= npc.ypos - 100)
						&& ((Maplestory.player.PlayerY - Player.PlayerHeight / 2) <= npc.ybottom + 100)) {
					if(npc.getType().equals("Shop")) {
						npc.clickEvent();
					}
				}
			}
		}
	}
	
	public void Character_Buy_Item(Item item, int price) {
		Character_Get_Item(item, false);
		loseMeso(price * item.Quantity);
	}
	
	public void Character_Sell_Item(Item item, int num) {
		inventory.Reduce_Item(item, num);
		gainMeso(item.getSellPrice() * num);
	}
	
	public void LevelUp() {
		String query = "";
		PreparedStatement pstat = null;
		
		Music LevelUp_Music = new Music("PvpLevelUp.wav", 1);
		LevelUp_Music.play();
		Level++;
		MaxExp = (long)(MaxExp * 1.1);
		
		MaxHP += 10;
		MaxMP += 10;
		MaxATK += 7;
		MinATK += 5;
		DEF += 1;
		
		HP_Heal(MaxHP);
		MP_Heal(MaxMP);
		
		try {
			query = "UPDATE PLAYER "
					+"SET LEV = ?, MINATK = ?, MAXATK = ?, DEF = ? WHERE NAME = ?";
			pstat = Maplestory.connection.prepareStatement(query);
			pstat.setInt(1, Level);
			pstat.setInt(2, MinATK);
			pstat.setInt(3, MaxATK);
			pstat.setInt(4, DEF);
			pstat.setString(5, Maplestory.player.name);
			
			pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		synchronized(Maplestory.current_stage.LevelUp_List) {
			Maplestory.current_stage.LevelUp_List.add(new LevelUp());
		}
	}
	
	public void gainMeso(int quantity) {
		String query = "";
		PreparedStatement pstat = null;
		
		inventory.Meso += quantity;
		
		try {
			query = "UPDATE PLAYER SET MESO = ? WHERE NAME = ?";
			pstat = Maplestory.connection.prepareStatement(query);
			pstat.setInt(1, inventory.Meso);
			pstat.setString(2, Maplestory.player.name);
			
			pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loseMeso(int quantity) {
		String query = "";
		PreparedStatement pstat = null;
		
		inventory.Meso -= quantity;
		
		try {
			query = "UPDATE PLAYER SET MESO = ? WHERE NAME = ?";
			pstat = Maplestory.connection.prepareStatement(query);
			pstat.setInt(1, inventory.Meso);
			pstat.setString(2, Maplestory.player.name);
			
			pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void gainExp(long quantity) {
		if(Exp + quantity >= MaxExp) {
			long q_left = quantity - (MaxExp - Exp);
			Exp = 0;
			LevelUp();
			gainExp(q_left);
		}
		else {
			Exp += quantity;
			Maplestory.ui_status_bar.Manage_ExpBar();
		}
	}
	
	public void loseExp() {
		Exp -= MaxExp / 10;
		if(Exp < 0) {
			Exp = 0;
		}
		Maplestory.ui_status_bar.Manage_ExpBar();
	}
	
	public String Make_CombatMsg(Item item) {
		String msg = "";
		
		if(item.getType().equals("Meso")) {
			msg += "메소를 얻었습니다 (+" + item.Quantity + ")";
		}
		else {
			if(item.getType().equals("Equip")) {
				msg+="장비";
			}
			else if(item.getType().equals("Consume")) {
				msg+="소비";
			}
			else if(item.getType().equals("Etc")) {
				msg+="기타";
			}
			else if(item.getType().equals("Install")) {
				msg+="설치";
			}
			else if(item.getType().equals("Cash")) {
				msg+="캐시";
			}
			
			msg += " 아이템을 얻었습니다 (" + item.getName();
			
			if(item.Quantity > 1) {
				msg += " " + item.Quantity + "개";
			}
			
			msg+=")";
		}
		
		return msg;
	}
}
