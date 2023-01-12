package maplestory;

import javax.swing.ImageIcon;

public class Mob_Orange_Mushroom extends Mob {
	protected static final String name = "주황버섯";
	protected static final int width = 60;
	protected static final int level = 8;
	protected static final int MaxHP = 80, MaxMP = 10;
	protected static final int Min_ATK = 8, Max_ATK = 16;
	protected static final int DEF = 10;
	protected static final long Exp = 15;
	protected static ImageIcon StandImgLeft[], MoveImgLeft[], JumpImgLeft[], DieImgLeft[], HitImgLeft;
	protected static String Damage_Music_Name = "Mushroom_Damage.wav";
	protected static String Die_Music_Name = "Blue_Orange_Mushroom_Die.wav";

	public Mob_Orange_Mushroom(int Location_Percent, Foothold _foothold, boolean _respawn) {
		super(_foothold, _respawn);

		FirstX = X = (_foothold.lastX() - width / 2 - _foothold.firstX() - width / 2) * Location_Percent / 100 
				+ _foothold.firstX() - width/2;
		FirstY = Y = _foothold.getY(FirstX + width / 2);

		HP = MaxHP;
		MP = MaxMP;

		StandImgLeft = new ImageIcon[2];
		MoveImgLeft = new ImageIcon[3];
		JumpImgLeft = new ImageIcon[1];
		DieImgLeft = new ImageIcon[3];

		StandImgLeft[0] = Maplestory.images.OrangeMushroomLeft0;
		StandImgLeft[1] = Maplestory.images.OrangeMushroomLeft1;

		MoveImgLeft[0] = Maplestory.images.OrangeMushroomLeft1;
		MoveImgLeft[1] = Maplestory.images.OrangeMushroomLeft2;
		MoveImgLeft[2] = Maplestory.images.OrangeMushroomLeft3;

		JumpImgLeft[0] = Maplestory.images.OrangeMushroomLeft3;

		DieImgLeft[0] = Maplestory.images.OrangeMushroomDieLeft0;
		DieImgLeft[1] = Maplestory.images.OrangeMushroomDieLeft1;
		DieImgLeft[2] = Maplestory.images.OrangeMushroomDieLeft2;

		HitImgLeft = Maplestory.images.OrangeMushroomHitLeft;

		current_Img = StandImgLeft[1];
	}
	
	public String getName() {
		return name;
	}
	
	public int getLevel() {
		return level;
	}
	public long getMaxHP() {
		return MaxHP;
	}
	public int getWidth() {
		return width;
	}
	public int getOffset() {
		return 0;
	}
	public long getExp() {
		return Exp;
	}

	// Mob methods
	public void Mob_Stand() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					if (!alive) {
						break;
					}
					if (!hit) {
						if (i % 2 == 0) {
							current_Img = StandImgLeft[0];
						} else {
							current_Img = StandImgLeft[1];
						}
					}
					try {
						Thread.sleep(Mob_Stand_Delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Mob_BodyAttack();
				}

				Mob_Done();
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	public void Mob_Move() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				if (Direction == -1) {
					for (int i = 0; i < 60; i++) {
						if (!alive) {
							break;
						}
						if (X <= cur_foothold.firstX()) {
							break;
						}
						if (!hit) {
							if (i % 15 == 0) {
								if ((i / 15) % 3 == 0) {
									current_Img = MoveImgLeft[0];
								} else if ((i / 15) % 3 == 1) {
									current_Img = MoveImgLeft[1];
								} else {
									current_Img = MoveImgLeft[2];
								}
							}
						}
						X--;
						Y = cur_foothold.getY(X + width/2);
						try {
							Thread.sleep((long)(Mob_Move_Delay * cur_foothold.getDistanceRatio(X + width/2)));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Mob_BodyAttack();
					}
				} else if (Direction == 1) {
					for (int i = 0; i < 60; i++) {
						if (!alive) {
							break;
						}
						if (X + 65 > cur_foothold.lastX()) {
							break;
						}
						if (!hit) {
							if (i % 15 == 0) {
								if ((i / 15) % 3 == 0) {
									current_Img = MoveImgLeft[0];
								} else if ((i / 15) % 3 == 1) {
									current_Img = MoveImgLeft[1];
								} else {
									current_Img = MoveImgLeft[2];
								}
							}
						}
						X++;
						Y = cur_foothold.getY(X + width/2);
						try {
							Thread.sleep((long)(Mob_Move_Delay * cur_foothold.getDistanceRatio(X + width/2)));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Mob_BodyAttack();
					}
				}

				Mob_Done();
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	public void Mob_Jump() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				if ((X - 60 >= cur_foothold.firstX()) && (X + 60 + 65 <= cur_foothold.lastX())) {
					if (Direction == -1) {
						if (!hit) {
							current_Img = JumpImgLeft[0];
						}
						for (int i = 0; i < 40; i++) {
							if (!alive) {
								break;
							}
							X--;
							Y -= 2;
							try {
								Thread.sleep(Mob_Jump_Delay);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Mob_BodyAttack();
						}
						while(true) {
							if (!alive) {
								break;
							}
							if (isLandable()) {
								break;
							}
							X--;
							Y += 2;
							try {
								Thread.sleep(Mob_Jump_Delay);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Mob_BodyAttack();
						}
						if (!hit) {
							current_Img = StandImgLeft[1];
						}
					} else if (Direction == 1) {
						if (!hit) {
							current_Img = JumpImgLeft[0];
						}
						for (int i = 0; i < 40; i++) {
							if (!alive) {
								break;
							}
							X++;
							Y -= 2;
							try {
								Thread.sleep(Mob_Jump_Delay);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Mob_BodyAttack();
						}
						while(true) {
							if (!alive) {
								break;
							}
							if (isLandable()) {
								break;
							}
							X++;
							Y += 2;
							try {
								Thread.sleep(Mob_Jump_Delay);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Mob_BodyAttack();
						}
						if (!hit) {
							current_Img = StandImgLeft[1];
						}
					}
				}

				Mob_Done();
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	public void Mob_Hit(int stroke_num, int Damage_Percent) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				boolean isCrit;
				int num;
				hit = true;
				hit_time = System.currentTimeMillis();
				for (int i = 0; i < stroke_num; i++) {
					Music Damage_Music = new Music(Damage_Music_Name, 1);
					Damage_Music.play();
					num = random.nextInt(100) + 1;
					if (num >= 1 && num <= Maplestory.player.Critical_Rate) {
						isCrit = true;
					} else {
						isCrit = false;
					}
					int damage = Attack_Damage_Calculate(isCrit, DEF, Damage_Percent);

					synchronized(Maplestory.current_stage.Damage_List) {
						Maplestory.current_stage.Damage_List.add(new Damage(damage, X + width/2
								, Y - current_Img.getIconHeight() - i * 30, true, isCrit));
					}
					if (damage > 0) {
						HP -= damage;
						if (HP <= 0) {
							HP = 0;
							if(!dropped_item) {
								Mob_DropItem();
								dropped_item = true;
							}
						} else {
							current_Img = HitImgLeft;
						}
						
						if (i == stroke_num - 1) {
							if (HP == 0) {
								hit = false;
								alive = false;
								Mob_Die();
							}
						}
					}

					try {
						Thread.sleep(Mob_Hit_Image_Delay / stroke_num);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				hit = false;
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	public void Mob_DropItem() {
		synchronized(Maplestory.current_stage.Item_List) {
			
			int random1 = random.nextInt(10000)+1;
			int random2 = random.nextInt(10000)+1;

			//Potion
			if(random1 >= 1 && random1 <= 746) {
				Item item = new Item_Red_Potion(1);
				item.Drop(X + current_Img.getIconWidth() / 2 - 15, Y - current_Img.getIconHeight() / 2);
			}
			
			//God
			if(random2 >= 1 && random2 <= 8000 ) {
				Item item = new Item_Orange_God(1);
				item.Drop(X + current_Img.getIconWidth() / 2, Y - current_Img.getIconHeight() / 2);
			}
			//Omok Piece
			else if(random2 >= 8001 && random2 <= 8078 ) {
				Item item = new Item_Orange_Omok_Piece(1);
				item.Drop(X + current_Img.getIconWidth() / 2, Y - current_Img.getIconHeight() / 2);
			}

			//Meso
			Item item = new Item_Meso((int)(level * (random.nextDouble() * 1.5 + 1.5)));
			item.Drop(X + current_Img.getIconWidth() / 2 + 15, Y - current_Img.getIconHeight() / 2);
			
			//Exp
			Maplestory.player.GainExp(Exp);
			String msg = Make_ExpMsg(Exp);
			synchronized(Message.combat_messages) {
				if(Message.combat_messages.size() == Message.max_messages) {
					Message.combat_messages.removeLast();
				}
				Message.combat_messages.addFirst(new Message("Combat", msg, System.currentTimeMillis()
						, Maplestory.current_stage.getFontMetrics().stringWidth(msg)));
			}
			
		}
	}
	
	public void Mob_Die() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Music Die_Music = new Music(Die_Music_Name, 1);
				Die_Music.play();
				
				current_Img = DieImgLeft[0];
				alpha = 0.8f;
				try {
					Thread.sleep(Mob_Die_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current_Img = DieImgLeft[1];
				alpha = 0.6f;
				try {
					Thread.sleep(Mob_Die_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current_Img = DieImgLeft[2];
				for (float i = 0.5f; i > 0f; i -= 0.1f) {
					alpha = i;
					try {
						Thread.sleep(Mob_Die_Delay / 2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				alpha = 0f;
				
			}

		};
		Maplestory.thread_pool.submit(runnable);

	}

	public void Mob_Start() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				isStart = true;
				int kind, direction;
				kind = random.nextInt(3);
				direction = random.nextInt(2);

				if (kind == 0) {
					Mob_Stand();
				} else {
					if (direction == 0) {
						if (Direction == 1) {
							Direction = -1;
							Mob_Move();
						} else {
							if (kind == 1) {
								Mob_Move();
							} else if (kind == 2) {
								Mob_Jump();
							}
						}
					} else if (direction == 1) {
						if (Direction == -1) {
							Direction = 1;
							Mob_Move();
						} else {
							if (kind == 1) {
								Mob_Move();
							} else if (kind == 2) {
								Mob_Jump();
							}
						}
					}
				}
			}

		};

		if(!isStart) {
			Maplestory.thread_pool.submit(runnable);
		}
	}

	public void Mob_Done() {
		isStart = false;
		if (available && alive) {
			Mob_Start();
		}
	}

	public void Mob_Respawn() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				X = FirstX;
				Y = FirstY;
				HP = MaxHP;
				MP = MaxMP;

				hit = false;
				alive = true;
				available = true;
				isStart = false;
				dropped_item = false;

				Direction = -1;
				current_Img = StandImgLeft[1];

				alpha = 1f;
				hit_time = 0;

				Mob_Start();
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	public void Mob_BodyAttack() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				int mob_height = current_Img.getIconHeight();

				// character is attacked
				if ((Maplestory.player.CharacterY >= Y - mob_height + 10)
						&& (Maplestory.player.CharacterY <= Y + Character.CharacterHeight - 10)) {
					if ((Maplestory.player.CharacterX >= X - Character.CharacterWidth + 10)
							&& (Maplestory.player.CharacterX <= X + width - 10)) {
						int damage = Hit_Damage_Calculate(Min_ATK, Max_ATK);
						Maplestory.player.HP_Damage(damage);
						int num = random.nextInt(100)+1;
						if (num >= 1 && num <= 100 - Maplestory.player.Stance) {
							if(damage > 0) {
								if (Maplestory.player.CharacterX + Character.CharacterWidth / 2 >= X + width / 2) {
									Maplestory.player.Character_Attacked2(1);
								} else {
									Maplestory.player.Character_Attacked2(-1);
								}
							}
							else {
								Character.ManageHittable();
							}
						}
						else {
							Character.ManageHittable();
						}
					}
				}
			}
		};

		if (Stage.Hittable && !hit && Stage.Available && Maplestory.player.HP != 0 && alive) {
			Maplestory.thread_pool.submit(runnable);
		}
	}
}
