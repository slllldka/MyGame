package maplestory;

import javax.swing.ImageIcon;

public class Mob_RedSnail extends Mob {
	protected static final String name = "빨간 달팽이";
	protected static final int width = 40;
	protected static final int level = 4;
	protected static final int MaxHP = 40, MaxMP = 0;
	protected static final int Min_ATK = 4, Max_ATK = 4;
	protected static final int DEF = 10;
	protected static final long Exp = 8;
	protected static ImageIcon StandImg, MoveImg[], DieImg[], HitImg;
	protected static String Damage_Music_Name = "Mushroom_Damage.wav";
	protected static String Die_Music_Name = "Snail_Die.wav";

	public Mob_RedSnail(int Location_Percent, Foothold _foothold, boolean _respawn) {
		super(_foothold, _respawn);

		FirstX = X = (_foothold.lastX() - width / 2 - _foothold.firstX() - width / 2) * Location_Percent / 100 
				+ _foothold.firstX() - width/2;
		FirstY = Y = _foothold.getY(FirstX + width / 2);

		HP = MaxHP;
		MP = MaxMP;

		MoveImg = new ImageIcon[4];
		DieImg = new ImageIcon[3];

		StandImg = Maplestory.images.RedSnailStand;

		MoveImg[0] = Maplestory.images.RedSnailMove0;
		MoveImg[1] = Maplestory.images.RedSnailMove1;
		MoveImg[2] = Maplestory.images.RedSnailMove2;
		MoveImg[3] = Maplestory.images.RedSnailMove3;

		DieImg[0] = Maplestory.images.RedSnailDie0;
		DieImg[1] = Maplestory.images.RedSnailDie1;
		DieImg[2] = Maplestory.images.RedSnailDie2;

		HitImg = Maplestory.images.RedSnailHit;

		current_Img = StandImg;
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
						current_Img = StandImg;
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
							if (i % 12 == 0) {
								if ((i / 12) % 3 == 0) {
									current_Img = MoveImg[0];
								} else if ((i / 12) % 3 == 1) {
									current_Img = MoveImg[1];
								} else if ((i / 12) % 3 == 2) {
									current_Img = MoveImg[2];
								} else if ((i / 12) % 3 == 3) {
									current_Img = MoveImg[3];
								} else if ((i / 12) % 3 == 4) {
									current_Img = MoveImg[4];
								}
							}
						}
						X--;
						Y = cur_foothold.getY(X + width/2);
						try {
							Thread.sleep((long)(Mob_Move_Delay * 2 * cur_foothold.getDistanceRatio(X + width/2)));
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
							if (i % 12 == 0) {
								if ((i / 12) % 3 == 0) {
									current_Img = MoveImg[0];
								} else if ((i / 12) % 3 == 1) {
									current_Img = MoveImg[1];
								} else if ((i / 12) % 3 == 2) {
									current_Img = MoveImg[2];
								} else if ((i / 12) % 3 == 3) {
									current_Img = MoveImg[3];
								} else if ((i / 12) % 3 == 4) {
									current_Img = MoveImg[4];
								}
							}
						}
						X++;
						Y = cur_foothold.getY(X + width/2);
						try {
							Thread.sleep((long)(Mob_Move_Delay * 2 * cur_foothold.getDistanceRatio(X + width/2)));
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
							current_Img = HitImg;
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
			else if(random1 >= 747 && random1 <= 1340) {
				Item item = new Item_Green_Apple(1);
				item.Drop(X + current_Img.getIconWidth() / 2 - 15, Y - current_Img.getIconHeight() / 2);
			}
			
			//Shell
			if(random2 >= 1 && random2 <= 8000) {
				Item item = new Item_Red_Shell(1);
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
				
				current_Img = DieImg[0];
				alpha = 0.8f;
				try {
					Thread.sleep(Mob_Die_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current_Img = DieImg[1];
				alpha = 0.6f;
				try {
					Thread.sleep(Mob_Die_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current_Img = DieImg[2];
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
				kind = random.nextInt(2);
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
							}
						}
					} else if (direction == 1) {
						if (Direction == -1) {
							Direction = 1;
							Mob_Move();
						} else {
							if (kind == 1) {
								Mob_Move();
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
				current_Img = StandImg;

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
				int xpos = X;
				int ypos = Y - mob_height;

				// character is attacked
				if ((Maplestory.player.CharacterY >= ypos - Character.CharacterHeight + 10)
						&& (Maplestory.player.CharacterY <= ypos + mob_height - 10)) {
					if ((Maplestory.player.CharacterX >= xpos - Character.CharacterWidth + 10)
							&& (Maplestory.player.CharacterX <= xpos + width - 10)) {
						int damage = Hit_Damage_Calculate(Min_ATK, Max_ATK);
						Maplestory.player.HP_Damage(damage);
						int num = random.nextInt(100)+1;
						if (num >= 1 && num <= 100 - Maplestory.player.Stance) {
							if(damage > 0) {
								if (Maplestory.player.CharacterX + Character.CharacterWidth / 2 >= xpos + width / 2) {
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
