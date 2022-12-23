package maplestory;

import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.ImageIcon;

public class Character {
	protected static final int CharacterWidth = 50, CharacterHeight = 70;
	
	//Position
	protected int CharDirection, CharacterX, CharacterY; //Direction: -1 Left / 1 Right
	protected Foothold cur_foothold = null;
	
	//Damage Skin
	protected Damage_Skin damage_skin = new Damage_Skin_Basic();
	
	//Status
	protected boolean alive = true;
	protected boolean pickable = true;
	protected boolean Item_usable = true;
	
	//Stat
	protected int MaxHP = 100, MaxMP = 100, HP = 100, MP = 100;
	protected int Min_ATK = 14, Max_ATK = 20;
	protected int DEF = 0;
	protected int Critical_Rate = 5;
	protected int Avoidability = 10;
	protected int Stance = 0;
	
	//Level
	protected int Level = 1;
	protected long MaxExp = 100, Exp = 0;
	
	//Inventory
	protected Inventory inventory = new Inventory();
	
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
	
	public void Update_Position(int x, int y) {
		synchronized(Maplestory.player) {
			CharacterX = x;
			CharacterY = y;
		}
	}
	
	public static void ManageAttacked() {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				Stage.attacked = true;
				try {
					Thread.sleep(Hit_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Stage.attacked = false;
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}
	public static void ManageHittable() {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				Stage.Hittable = false;
				try {
					Thread.sleep(Hit_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Stage.Hittable = true;
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}
	public static void ManageLadderJump() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Stage.Ladder_Jump = true;
				if(Stage.Ladder_Jump) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Stage.Ladder_Jump = false;
				}
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	// Returns if character is landable on ground
	// x: character's x position, y: character's y position
	public boolean IsLandable() {
		int CX_CENTER = CharacterX+CharacterWidth/2;
		for (Foothold foothold : Maplestory.current_stage.Foothold_List) {
			if(foothold.isLandable(CX_CENTER, CharacterY+CharacterHeight)) {
				cur_foothold = foothold;
				Update_Position(CharacterX, cur_foothold.getY(CX_CENTER)-CharacterHeight);
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
				if ((xpos + 20 >= CharacterX + 25) && (xpos - 20 <= CharacterX + 25)
						&& (CharacterY + CharacterHeight > ystart) && (CharacterY <= yend)) {
					return i;
				}
			}
			// Down Direction
			else if (direction == -1) {
				if (Stage.Ladder == false) {
					if ((xpos + 20 >= CharacterX + 25) && (xpos - 20 <= CharacterX + 25)
							&& (CharacterY + CharacterHeight == ystart)) {
						return i;
					}
				} else {
					if ((xpos + 20 >= CharacterX + 25) && (xpos - 20 <= CharacterX + 25)
							&& (CharacterY + CharacterHeight > ystart) && (CharacterY <= yend)) {
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
		if(Stage.Left) {
			for (Wall wall : Maplestory.current_stage.Wall_List) {
				if(!wall.canLeftGo(CharacterX, CharacterY, CharacterWidth, CharacterHeight)){
					return false;
				}
			}
		}
		else if(Stage.Right) {
			for (Wall wall : Maplestory.current_stage.Wall_List) {
				if(!wall.canRightGo(CharacterX, CharacterY, CharacterWidth, CharacterHeight)){
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
					Stage.Left = false;
					Stage.Right = true;
				}
				else {
					Stage.Left = true;
					Stage.Right = false;
				}
				Stage.Jump = true;
				Stage.Ladder = false;
				Stage.attacked = true;
				ManageHittable();
				
				if (xd > 0) {
					current_Img = characterJumpLeftImg;
				} else {
					current_Img = characterJumpRightImg;
				}
				
				int x = CharacterX;
				int y = CharacterY;
				// character flies high
				while (alive && Stage.Available && x > 0 && x < Maplestory.current_stage.X_Size-Character.CharacterWidth) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (wallCheck()) {
						x += xd;
					}
					if (y - 2 >= 0) {
						y -= 2;
					}

					Update_Position(x, y);
				}
				// character falls
				while (alive && Stage.Available) {
					current_Img = characterJumpLeftImg;
					if (IsLandable()) {
						break;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (wallCheck()) {
						x += xd;
					}
					y += 2;
					Update_Position(x, y);
				}

				// Done

				if(alive && Stage.Available) {
					Stage.Left = false;
					Stage.Right = false;
					Stage.Jump = false;
					Stage.attacked = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Stage.Ladder == false && Stage.Attacking == false) {
						if (CharDirection == 1) {
							if (Stage.DownKey == true) {
								current_Img = characterProneRightImg;
							} else {
								current_Img = characterRightImg;
								if (Stage.RightKey == true && Stage.Right == false) {
									Character_Right();
								}
							}
						} else if (CharDirection == -1) {
							if (Stage.DownKey == true) {
								current_Img = characterProneLeftImg;
							} else {
								current_Img = characterLeftImg;
								if (Stage.LeftKey == true && Stage.Left == false) {
									Character_Left();
								}
							}
						}
					}
				}
			}
		};

		if(alive && Stage.Hittable && Stage.Available) {
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
					Stage.Left = false;
					Stage.Right = true;
				}
				else {
					Stage.Left = true;
					Stage.Right = false;
				}
				Stage.Jump = true;
				Stage.Ladder = false;
				
				//can switch
				//Stage.attacked = true;
				ManageAttacked();
				
				ManageHittable();
				
				if (xd > 0) {
					current_Img = characterJumpLeftImg;
				} else {
					current_Img = characterJumpRightImg;
				}
				
				int x = CharacterX;
				int y = CharacterY;
				// character flies a little
				for (int i = 0; i < 13; i++) {
					if(!alive || !Stage.Available) {
						break;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (wallCheck()) {
						x += xd;
					}
					if (y - 2 >= 0) {
						y -= 2;
					}

					Update_Position(x, y);
				}
				// character falls
				while (alive && Stage.Available) {
					if (IsLandable()) {
						break;
					}
					else if(Stage.Ladder) {
						return;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (wallCheck()) {
						x += xd;
					}
					y += 2;

					Update_Position(x, y);
				}

				
				// Done
				
				if(alive && Stage.Available) {
					Stage.Left = false;
					Stage.Right = false;
					Stage.Jump = false;
					Stage.Ladder = false;
					Stage.attacked = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Stage.Ladder == false && Stage.Attacking == false) {
						if (CharDirection == 1) {
							if (Stage.DownKey == true) {
								current_Img = characterProneRightImg;
							} else {
								current_Img = characterRightImg;
								if (Stage.RightKey == true && Stage.Right == false) {
									Character_Right();
								}
							}
						} else if (CharDirection == -1) {
							if (Stage.DownKey == true) {
								current_Img = characterProneLeftImg;
							} else {
								current_Img = characterLeftImg;
								if (Stage.LeftKey == true && Stage.Left == false) {
									Character_Left();
								}
							}
						}
					}
				}
			}

		};
		if(alive && Stage.Hittable && Stage.Available) {
			Maplestory.thread_pool.submit(runnable);
		}
	}

	// Move Left the Character
	public void Character_Left() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Stage.Left = true;

				boolean islandable;
				int x = CharacterX;
				int y = CharacterY;
				int setWalkImg = 1;
				islandable = true;
				// character moves left
				while (Stage.LeftKey == true) {
					if (Stage.Left == false || Stage.Ladder == true || Stage.Attacking == true) {
						break;
					}
					if(Stage.Jump == true || Stage.attacked == true) {
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
							Thread.sleep((long)(Move_Delay * cur_foothold.getDistanceRatio(x+CharacterWidth/2)));
						}
						else {
							Thread.sleep(Move_Delay);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (wallCheck()) {
						x--;
						if(cur_foothold.getY(x+CharacterWidth/2) != -1) {
							y = cur_foothold.getY(x+CharacterWidth/2) - CharacterHeight;
						}
						Update_Position(x, y);
					}
					if (setWalkImg == 60) {
						setWalkImg = 1;
					} else {
						setWalkImg++;
					}
				}
				
				y = CharacterY;
				// character falls
				if (!islandable) {
					Stage.Jump = true;
					current_Img = characterJumpLeftImg;
					while (alive && Stage.Available) {
						if (Stage.Ladder == true || Stage.attacked == true) {
							break;
						}
						try {
							Thread.sleep(Move_Delay);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
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
							Stage.Jump = false;
							break;
						}
					}
				}

				// Done

				if(alive && Stage.Available) {
					Stage.Left = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Stage.Ladder == false && Stage.attacked == false && Stage.Attacking == false) {
						if (Stage.DownKey == false) {
							if (!islandable) {
								if (CharDirection == 1) {
									current_Img = characterRightImg;
									if (Stage.RightKey == true && Stage.Right == false) {
										Character_Right();
									}
								} else if (CharDirection == -1) {
									current_Img = characterLeftImg;
									if (Stage.LeftKey == true && Stage.Left == false) {
										Character_Left();
									}
								}
							} else if (Stage.Right == false && Stage.Jump == false && Stage.Attacking == false) {
								current_Img = characterLeftImg;
							}
						} else {
							if (!islandable) {
								if (CharDirection == 1) {
									if (Stage.RightKey == true && Stage.Right == false) {
										current_Img = characterRightImg;
										Character_Right();
									} else {
										current_Img = characterProneRightImg;
									}
								} else if (CharDirection == -1) {
									if (Stage.LeftKey == true && Stage.Left == false) {
										current_Img = characterLeftImg;
										Character_Left();
									} else {
										current_Img = characterProneLeftImg;
									}
								}
							} else if (CharDirection == 1) {
								current_Img = characterProneRightImg;
							} else if (CharDirection == -1) {
								current_Img = characterProneLeftImg;
							}
						}
					} else {
						Stage.Jump = false;
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
				Stage.Right = true;

				boolean islandable;
				int x = CharacterX;
				int y = CharacterY;
				int setWalkImg = 1;
				islandable = true;
				// character moves right
				while (Stage.RightKey == true) {
					if (Stage.Right == false || Stage.Ladder == true || Stage.Attacking == true) {
						break;
					}
					if(Stage.Jump == true || Stage.attacked == true) {
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
							Thread.sleep((long)(Move_Delay * cur_foothold.getDistanceRatio(x+CharacterWidth/2)));
						}
						else {
							Thread.sleep(Move_Delay);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (wallCheck()) {
						x++;
						if(cur_foothold.getY(x+CharacterWidth/2) != -1) {
							y = cur_foothold.getY(x+CharacterWidth/2) - CharacterHeight;
						}
						Update_Position(x, y);
					}

					if (setWalkImg == 60) {
						setWalkImg = 1;
					} else {
						setWalkImg++;
					}
				}
				
				y = CharacterY;
				// character falls
				if (!islandable) {
					Stage.Jump = true;
					current_Img = characterJumpRightImg;
					while (alive && Stage.Available) {
						if (Stage.Ladder == true || Stage.attacked == true) {
							break;
						}
						try {
							Thread.sleep(Move_Delay);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
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
							Stage.Jump = false;
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

				if(alive && Stage.Available) {
					Stage.Right = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Stage.Ladder == false && Stage.attacked == false && Stage.Attacking == false) {
						if (Stage.DownKey == false) {
							if (!islandable) {
								if (CharDirection == 1) {
									current_Img = characterRightImg;
									if (Stage.RightKey == true && Stage.Right == false) {
										Character_Right();
									}
								} else if (CharDirection == -1) {
									current_Img = characterLeftImg;
									if (Stage.LeftKey == true && Stage.Left == false) {
										Character_Left();
									}
								}
							} else if (Stage.Left == false && Stage.Jump == false && Stage.Attacking == false) {
								current_Img = characterRightImg;
							}
						} else {
							if (!islandable) {
								if (CharDirection == 1) {
									if (Stage.RightKey == true && Stage.Right == false) {
										current_Img = characterRightImg;
										Character_Right();
									} else {
										current_Img = characterProneRightImg;
									}
								} else if (CharDirection == -1) {
									if (Stage.LeftKey == true && Stage.Left == false) {
										current_Img = characterLeftImg;
										Character_Left();
									} else {
										current_Img = characterProneLeftImg;
									}
								}
							} else if (CharDirection == 1) {
								current_Img = characterProneRightImg;
							} else if (CharDirection == -1) {
								current_Img = characterProneLeftImg;
							}
						}
					} else {
						Stage.Jump = false;
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
				
				Stage.Jump = true;
				
				int x = CharacterX;
				int y = CharacterY;
				// jump up
				for (int i = 0; i < 30; i++) {
					if (!alive || !Stage.Available) {
						break;
					}
					if(Stage.Ladder) {
						Stage.Jump = false;
						return;
					}
					if(Stage.attacked) {
						return;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (y - 2 >= 0) {
						y -= 2;
						Update_Position(x, y);
					} else {
						break;
					}
				}
				// jump down
				while (alive && Stage.Available) {
					if(Stage.Ladder) {
						Stage.Jump = false;
						return;
					}
					if(Stage.attacked) {
						return;
					}
					if (IsLandable()) {
						break;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					y += 2;
					Update_Position(x, y);
				}

				// Done

				if(alive && Stage.Available) {
					Stage.Jump = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Stage.Ladder == false && Stage.attacked == false && Stage.Attacking == false) {
						if (CharDirection == 1) {
							if (Stage.DownKey == true) {
								current_Img = characterProneRightImg;
							} else {
								current_Img = characterRightImg;
								if (Stage.RightKey == true && Stage.Right == false) {
									Character_Right();
								}
							}
						} else if (CharDirection == -1) {
							if (Stage.DownKey == true) {
								current_Img = characterProneLeftImg;
							} else {
								current_Img = characterLeftImg;
								if (Stage.LeftKey == true && Stage.Left == false) {
									Character_Left();
								}
							}
						}
					} else {
						Stage.Jump = false;
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
				Stage.Left = true;
				Stage.Jump = true;
				Stage.Ladder = false;
				
				Music Jump_Music = new Music(Jump_Music_Name, 1);
				Jump_Music.play();
				
				int height = 0;
				if(onLadder) {
					height = 15;
				}
				else {
					height = 30;
				}
				
				int x = CharacterX;
				int y = CharacterY;
				// jump up
				for (int i = 0; i < height; i++) {
					if (!alive || !Stage.Available) {
						break;
					}
					if(Stage.Ladder) {
						Stage.Left = false;
						Stage.Jump = false;
						ManageLadderJump();
						return;
					}
					if(Stage.attacked) {
						return;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (wallCheck()) {
						x--;
					}
					if (y - 2 >= 0) {
						y -= 2;
					} else {
						break;
					}
					Update_Position(x, y);
				}
				// jump down
				while (alive && Stage.Available) {
					if(Stage.Ladder) {
						Stage.Left = false;
						Stage.Jump = false;
						ManageLadderJump();
						return;
					}
					if(Stage.attacked) {
						return;
					}
					if (IsLandable()) {
						break;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (wallCheck()) {
						x--;
					}
					y += 2;
					Update_Position(x, y);
				}

				// Done

				if(alive && Stage.Available) {
					Stage.Left = false;
					Stage.Jump = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Stage.Ladder == false && Stage.attacked == false && Stage.Attacking == false) {
						if (CharDirection == 1) {
							if (Stage.DownKey == true) {
								current_Img = characterProneRightImg;
							} else {
								current_Img = characterRightImg;
								if (Stage.RightKey == true && Stage.Right == false) {
									Character_Right();
								}
							}
						} else if (CharDirection == -1) {
							if (Stage.DownKey == true) {
								current_Img = characterProneLeftImg;
							} else {
								current_Img = characterLeftImg;
								if (Stage.LeftKey == true && Stage.Left == false) {
									Character_Left();
								}
							}
						}
					} else {
						Stage.Jump = false;
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
				Stage.Right = true;
				Stage.Jump = true;
				Stage.Ladder = false;
				
				Music Jump_Music = new Music(Jump_Music_Name, 1);
				Jump_Music.play();
				
				int height = 0;
				if(onLadder) {
					height = 15;
				}
				else {
					height = 30;
				}
				
				int x = CharacterX;
				int y = CharacterY;
				// jump up
				for (int i = 0; i < height; i++) {
					if (!alive || !Stage.Available) {
						break;
					}
					if(Stage.Ladder) {
						Stage.Right = false;
						Stage.Jump = false;
						ManageLadderJump();
						return;
					}
					if(Stage.attacked) {
						return;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (wallCheck()) {
						x++;
					}
					if (y - 2 >= 0) {
						y -= 2;
					} else {
						break;
					}
					Update_Position(x, y);
				}
				// jump down
				while (alive && Stage.Available) {
					if(Stage.Ladder) {
						Stage.Right = false;
						Stage.Jump = false;
						ManageLadderJump();
						return;
					}
					if(Stage.attacked) {
						return;
					}
					if (IsLandable()) {
						break;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (wallCheck()) {
						x += 1;
					}
					y += 2;
					Update_Position(x, y);
				}

				// Done

				if(alive && Stage.Available) {
					Stage.Right = false;
					Stage.Jump = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Stage.Ladder == false && Stage.attacked == false && Stage.Attacking == false) {
						if (CharDirection == 1) {
							if (Stage.DownKey == true) {
								current_Img = characterProneRightImg;
							} else {
								current_Img = characterRightImg;
								if (Stage.RightKey == true && Stage.Right == false) {
									Character_Right();
								}
							}
						} else if (CharDirection == -1) {
							if (Stage.DownKey == true) {
								current_Img = characterProneLeftImg;
							} else {
								current_Img = characterLeftImg;
								if (Stage.LeftKey == true && Stage.Left == false) {
									Character_Left();
								}
							}
						}
					} else {
						Stage.Jump = false;
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
				Stage.Jump = true;
				
				Music Jump_Music = new Music(Jump_Music_Name, 1);
				Jump_Music.play();
				
				int x = CharacterX;
				int y = CharacterY;
				// jump down
				while (alive && Stage.Available) {
					if (Stage.attacked) {
						return;
					}
					try {
						Thread.sleep(Move_Delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					y += 2;
					Update_Position(x, y);
					if (IsLandable()) {
						break;
					}
				}

				// Done

				if(alive && Stage.Available) {
					Stage.Jump = false;
					// setting character's state(where character is looking, if character is
					// proning) according to direction, key board inputs
					if (Stage.attacked == false && Stage.Attacking == false) {
						if (CharDirection == 1) {
							if (Stage.DownKey == true) {
								current_Img = characterProneRightImg;
							} else {
								current_Img = characterRightImg;
								if (Stage.RightKey == true && Stage.Right == false) {
									Character_Right();
								}
							}
						} else if (CharDirection == -1) {
							if (Stage.DownKey == true) {
								current_Img = characterProneLeftImg;
							} else {
								current_Img = characterLeftImg;
								if (Stage.LeftKey == true && Stage.Left == false) {
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
				Stage.Ladder = true;
				Stage.Up = true;
				Stage.Jump = false;
				
				Ladder ladder = Maplestory.current_stage.Ladder_List.get(idx);
				int xpos = ladder.getX();
				int ystart = ladder.getYstart();
				int yend = ladder.getYend();
				
				Update_Position(xpos - 25, CharacterY);
				
				int x = CharacterX;
				int y = CharacterY;
				int setLadderImg = 1;

				while (Stage.UpKey) {
					if(Stage.Jump || Stage.attacked) {
						Stage.Up = false;
						Stage.Ladder = false;
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if ((y + CharacterHeight - 2 >= ystart) && (y - 2 <= yend)) {
						y -= 2;
						Update_Position(x, y);
					}
					if (setLadderImg == 20) {
						setLadderImg = 1;
					} else {
						setLadderImg++;
					}
					if (y + CharacterHeight == ystart) {
						IsLandable();
						Stage.Ladder = false;
						break;
					}
				}

				// Done

				if(alive && Stage.Available) {
					Stage.Up = false;
					// setting character's state(where character is looking) according to direction
					if (Stage.Ladder == false && Stage.attacked == false && Stage.Attacking == false) {
						if (CharDirection == -1) {
							current_Img = characterLeftImg;
						} else if (CharDirection == 1) {
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
				Stage.Ladder = true;
				Stage.Down = true;

				Ladder ladder = Maplestory.current_stage.Ladder_List.get(idx);
				int xpos = ladder.getX();
				int ystart = ladder.getYstart();
				int yend = ladder.getYend();
				
				Update_Position(xpos - 25, CharacterY);
				
				boolean islandable;
				int x = CharacterX;
				int y = CharacterY;
				int setLadderImg = 1;
				islandable = false;

				while (Stage.DownKey) {
					if (Stage.attacked) {
						Stage.Down = false;
						Stage.Ladder = false;
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if ((y + CharacterHeight + 2 >= ystart) && (y + 2 <= yend)) {
						y += 2;
						Update_Position(x, y);
					}
					if (setLadderImg == 20) {
						setLadderImg = 1;
					} else {
						setLadderImg++;
					}
					if ((ystart < y + CharacterHeight) && ((y == yend) || (islandable = IsLandable()))) {
						Stage.Ladder = false;
						break;
					}
				}

				// character falls
				if (!islandable && !Stage.Ladder) {
					Stage.Down = false;
					Stage.Jump = true;
					current_Img = characterJumpLeftImg;
					while (alive && Stage.Available) {
						if (Stage.attacked) {
							return;
						}
						if (IsLandable()) {
							Stage.Jump = false;
							islandable = true;
							break;
						}
						try {
							Thread.sleep(Move_Delay);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						y += 2;
						Update_Position(x, y);
					}
				}

				// Done

				if(alive && Stage.Available) {
					Stage.Down = false;
					// setting character's state(where character is looking) according to direction
					if (islandable == true && Stage.Ladder == false && Stage.Attacking == false) {
						if (CharDirection == -1) {
							current_Img = characterLeftImg;
						} else if (CharDirection == 1) {
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
				Stage.Attacking = true;
				
				int Player_Xcenter = CharacterX + CharacterWidth / 2;
				int Player_Ycenter = CharacterY + CharacterHeight / 2;
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

				if (CharDirection == -1) {
					current_Img = characterAttackLeft1Img;
					try {
						Thread.sleep(Attack_Delay);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Music Attack_Music = new Music(Attack_Music_Name, 1);
					Attack_Music.play();
					
					for(Mob temp : Maplestory.current_stage.Mob_List) {
						int Mob_Xstart = temp.X;
						int Mob_Xend = temp.X + temp.current_Img.getIconWidth();
						int Mob_Ystart = temp.Y - temp.current_Img.getIconHeight();
						int Mob_Yend = temp.Y;

						if (temp.available && temp.alive) {
							if (((Mob_Xstart >= Player_Xcenter - Range_Xfront && Mob_Xstart <= Player_Xcenter + Range_Xbehind)
									|| (Mob_Xend >= Player_Xcenter - Range_Xfront && Mob_Xend <= Player_Xcenter + Range_Xbehind))
									&& ((Mob_Ystart >= Player_Ycenter - Range_Yabove
											&& Mob_Ystart <= Player_Ycenter + Range_Ybelow)
											|| (Mob_Yend >= Player_Ycenter - Range_Yabove
													&& Mob_Yend <= Player_Ycenter + Range_Ybelow))) {
								temp.Mob_Hit(stroke_num, Damage_Percent);
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					current_Img = characterLeftImg;
				} else if (CharDirection == 1) {
					current_Img = characterAttackRight1Img;
					try {
						Thread.sleep(Attack_Delay);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Music Attack_Music = new Music(Attack_Music_Name, 1);
					Attack_Music.play();

					for(Mob temp : Maplestory.current_stage.Mob_List) {
						int Mob_Xstart = temp.X;
						int Mob_Xend = temp.X + temp.current_Img.getIconWidth();
						int Mob_Ystart = temp.Y - temp.current_Img.getIconHeight();
						int Mob_Yend = temp.Y;

						if (temp.available && temp.alive) {
							if (((Mob_Xstart >= Player_Xcenter - Range_Xbehind && Mob_Xstart <= Player_Xcenter + Range_Xfront)
									|| (Mob_Xend >= Player_Xcenter - Range_Xbehind && Mob_Xend <= Player_Xcenter + Range_Xfront))
									&& ((Mob_Ystart >= Player_Ycenter - Range_Yabove
											&& Mob_Ystart <= Player_Ycenter + Range_Ybelow)
											|| (Mob_Yend >= Player_Ycenter - Range_Yabove
													&& Mob_Yend <= Player_Ycenter + Range_Ybelow))) {
								temp.Mob_Hit(stroke_num, Damage_Percent);
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					current_Img = characterRightImg;
				}

				Stage.Attacking = false;
				if (CharDirection == 1) {
					if (Stage.DownKey == true) {
						current_Img = characterProneRightImg;
					} else {
						current_Img = characterRightImg;
						if (Stage.RightKey == true && Stage.Right == false) {
							Character_Right();
						}
					}
				} else if (CharDirection == -1) {
					if (Stage.DownKey == true) {
						current_Img = characterProneLeftImg;
					} else {
						current_Img = characterLeftImg;
						if (Stage.LeftKey == true && Stage.Left == false) {
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
			Maplestory.current_stage.Damage_List.add(new Damage(damage, CharacterX + CharacterWidth / 2, CharacterY, false, false));
		}
		if(HP <= damage) {
			HP = 0;
			Character_Die();
		}
		else {
			HP -= damage;
		}
		Maplestory.status_bar.Manage_HpBar();
	}
	public void HP_Heal(int heal) {
		if(HP + heal > MaxHP) {
			HP = MaxHP;
		}
		else {
			HP += heal;
		}
		Maplestory.status_bar.Manage_HpBar();
	}
	public void MP_Use(int cost) {
		MP -= cost;
		Maplestory.status_bar.Manage_MpBar();
	}
	public void MP_Heal(int heal) {
		if(MP + heal > MaxMP) {
			MP = MaxHP;
		}
		else {
			MP += heal;
		}
		Maplestory.status_bar.Manage_MpBar();
	}

	public void Character_Die() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Music Tombstone_Music = new Music("Tombstone.wav", 1);
				Tombstone_Music.play();
				
				LoseExp();
				
				alive = false;
				Stage.LeftKey = false;
				Stage.RightKey = false;
				Stage.UpKey = false;
				Stage.DownKey = false;
				Stage.Left = false;
				Stage.Right = false;
				Stage.Jump = false;
				Stage.Ladder = false;
				Stage.Up = false;
				Stage.Down = false;

				while(!IsLandable()) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					CharacterY+=2;
				}
				
				Show_Tomb(CharacterX+CharacterWidth/2, CharacterY+CharacterHeight);
				
				alpha = 0.7f;
				if(CharDirection == -1) {
					current_Img = characterDieLeftImg;
				}
				else if(CharDirection == 1) {
					current_Img = characterDieRightImg;
				}
				
				Stage.Hittable = false;
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
							// TODO Auto-generated catch block
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	public void Character_Get_Item(Item item, boolean pickup) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				ArrayList<Item> inventory_list = null;
				int size = 0;
				String msg = "";
				
				if(item.getType().equals("Meso")) {
					item.pickable = false;
					pickable = false;
					inventory.Meso += item.Quantity;
					
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					pickable = true;
				}
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	public void Character_Item_Drop() {
		if(alive) {
			synchronized(inventory.current_inventory_list) {
				Item data = inventory.current_inventory_list.get(inventory.move_index);
				Item item_drop = data.getNew(1);
				inventory.Reduce_Item(inventory.move_index, 1);
				item_drop.Drop(CharacterX+Character.CharacterWidth/2,
						CharacterY+Character.CharacterHeight/2);
			}
		}
	}
	
	public void Character_Buy_Item(Item item, int price) {
		Character_Get_Item(item, false);
		inventory.Meso -= price;
	}
	
	public void Character_Sell_Item(Item item) {
		inventory.Reduce_Item(item, 1);
		inventory.Meso += item.getSellPrice();
	}
	
	public void LevelUp() {
		Music LevelUp_Music = new Music("PvpLevelUp.wav", 1);
		LevelUp_Music.play();
		Level++;
		MaxExp = (long)(MaxExp * 1.1);
		
		MaxHP += 10;
		MaxMP += 10;
		Max_ATK += 7;
		Min_ATK += 5;
		DEF += 1;
		
		HP_Heal(MaxHP);
		MP_Heal(MaxMP);
		
		synchronized(Maplestory.current_stage.LevelUp_List) {
			Maplestory.current_stage.LevelUp_List.add(new LevelUp());
		}
	}
	
	public void GainExp(long quantity) {
		if(Exp + quantity >= MaxExp) {
			long q_left = quantity - (MaxExp - Exp);
			Exp = 0;
			LevelUp();
			GainExp(q_left);
		}
		else {
			Exp += quantity;
			Maplestory.status_bar.Manage_ExpBar();
		}
	}
	
	public void LoseExp() {
		Exp -= MaxExp / 10;
		if(Exp < 0) {
			Exp = 0;
		}
		Maplestory.status_bar.Manage_ExpBar();
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
