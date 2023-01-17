package maplestory;

import javax.swing.ImageIcon;

public class Mob_Snail extends Mob {
	protected static final String name = "달팽이";
	protected static final int width = 40, height = 30;
	protected static final int level = 1;
	protected static final int MaxHP = 8, MaxMP = 0;
	protected static final int Min_ATK = 2, Max_ATK = 2;
	protected static final int DEF = 10;
	protected static final long Exp = 3;
	protected static ImageIcon StandImg, MoveImg[], DieImg[], HitImg;
	protected static String Damage_Music_Name = "Mushroom_Damage.wav";
	protected static String Die_Music_Name = "Snail_Die.wav";

	public Mob_Snail(int Location_Percent, Foothold _foothold, boolean _respawn) {
		super(_foothold, _respawn);

		FirstX = X = (_foothold.lastX() - width / 2 - _foothold.firstX() - width / 2) * Location_Percent / 100 
				+ _foothold.firstX() - width/2;
		FirstY = Y = _foothold.getY(FirstX + width / 2);

		HP = MaxHP;
		MP = MaxMP;

		MoveImg = new ImageIcon[5];
		DieImg = new ImageIcon[9];

		StandImg = Maplestory.images.SnailStand;

		MoveImg[0] = Maplestory.images.SnailMove0;
		MoveImg[1] = Maplestory.images.SnailMove1;
		MoveImg[2] = Maplestory.images.SnailMove2;
		MoveImg[3] = Maplestory.images.SnailMove3;
		MoveImg[4] = Maplestory.images.SnailMove4;

		DieImg[0] = Maplestory.images.SnailDie0;
		DieImg[1] = Maplestory.images.SnailDie1;
		DieImg[2] = Maplestory.images.SnailDie2;
		DieImg[3] = Maplestory.images.SnailDie3;
		DieImg[4] = Maplestory.images.SnailDie4;
		DieImg[5] = Maplestory.images.SnailDie5;
		DieImg[6] = Maplestory.images.SnailDie6;
		DieImg[7] = Maplestory.images.SnailDie7;
		DieImg[8] = Maplestory.images.SnailDie8;

		HitImg = Maplestory.images.SnailHit;

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
	public int getHeight() {
		return height;
	}
	public int getXOffset() {
		return 0;
	}
	public long getExp() {
		return Exp;
	}

	// Mob methods
	public void stand() {
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
					bodyAttack();
				}

				done();
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	public void move() {
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
						bodyAttack();
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
						bodyAttack();
					}
				}

				done();
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	public void hit(int stroke_num, int Damage_Percent) {
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
								dropItem();
								dropped_item = true;
							}
						} else {
							current_Img = HitImg;
						}
						
						if (i == stroke_num - 1) {
							if (HP == 0) {
								hit = false;
								alive = false;
								die();
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

	public void dropItem() {
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
			if(random2 >= 1 && random2 <= 8000 ) {
				Item item = new Item_Snail_Shell(1);
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
	
	public void die() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Music Die_Music = new Music(Die_Music_Name, 1);
				Die_Music.play();
				
				for(int i=0;i<8;i++) {
					current_Img = DieImg[i];
					try {
						Thread.sleep(Mob_Die_Delay / 4);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				current_Img = DieImg[8];
				for (float i = 1f; i > 0f; i -= 0.1f) {
					alpha = i;
					try {
						Thread.sleep(Mob_Die_Delay / 4);
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

	public void start() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				isStart = true;
				int kind, direction;
				kind = random.nextInt(2);
				direction = random.nextInt(2);

				if (kind == 0) {
					stand();
				} else {
					if (direction == 0) {
						if (Direction == 1) {
							Direction = -1;
							move();
						} else {
							if (kind == 1) {
								move();
							}
						}
					} else if (direction == 1) {
						if (Direction == -1) {
							Direction = 1;
							move();
						} else {
							if (kind == 1) {
								move();
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

	public void done() {
		isStart = false;
		if (available && alive) {
			start();
		}
	}

	public void respawn() {
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

				start();
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	public void bodyAttack() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				int mob_height = current_Img.getIconHeight();

				// character is attacked
				if ((Maplestory.player.CharacterY >= Y - mob_height + 10)
						&& (Maplestory.player.CharacterY <= Y + Character.CharacterHeight - 10)) {
					if ((Maplestory.player.CharacterX >= X - Character.CharacterWidth + 10)
							&& (Maplestory.player.CharacterX <= X + width - 10)) {
						int damage = Hit_Damage_Calculate(Min_ATK, Max_ATK, 1);
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

		if (Map.Hittable && !hit && Map.Available && Maplestory.player.HP != 0 && alive) {
			Maplestory.thread_pool.submit(runnable);
		}
	}
}
