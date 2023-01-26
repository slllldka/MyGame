package maplestory;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Mob_MushMom extends Mob {
	protected static final String name = "머쉬맘";
	protected static final int width = 120, height = 110;
	protected int xOffset = 0, yOffset = 0;
	protected int effectXOffset = 0, effectYOffset = 0;
	protected static final int level = 60;
	protected static final int MaxHP = 20000, MaxMP = 100;
	protected static final int Min_ATK = 50, Max_ATK = 70;
	protected static final int DEF = 30;
	protected static final long Exp = 1200;
	
	protected boolean isAttacking = false;
	
	protected static final int respawnPeriod = 1500000;
	
	protected static ImageIcon StandImgLeft[], MoveImgLeft[], JumpImgLeft[], AttackImgLeft[], AttackEffectImg[]
			, DieImgLeft[], HitImgLeft;
	protected static int StandXOffset[], MoveXOffset[], JumpXOffset[], AttackXOffset[], AttackEffectXOffset[]
			, DieXOffset[], HitXOffset;
	protected static int StandYOffset[], MoveYOffset[], JumpYOffset[], AttackYOffset[], AttackEffectYOffset[]
			, DieYOffset[], HitYOffset;
	
	protected static int EffectDelay[] = {100, 100, 100, 100, 100, 100, 200, 300, 100, 100, 100, 100, 100, 200, 50};
	
	protected static String Damage_Music_Name = "Mushroom_Damage.wav";
	protected static String Attack_Music_Name = "MushMom_Attack1.wav";
	protected static String Die_Music_Name = "MushMom_Die.wav";

	public Mob_MushMom(int Location_Percent, Foothold _foothold, boolean _respawn) {
		super(_foothold, _respawn);
		
		isBoss = true;

		FirstX = X = (_foothold.lastX() - width / 2 - _foothold.firstX() - width / 2) * Location_Percent / 100 
				+ _foothold.firstX() - width/2;
		FirstY = Y = _foothold.getY(FirstX + width / 2);

		HP = MaxHP;
		MP = MaxMP;
		
		gaugeIcon = Maplestory.images.MushMomGaugeIcon;
		
		StandImgLeft = new ImageIcon[1];
		MoveImgLeft = new ImageIcon[6];
		JumpImgLeft = new ImageIcon[1];
		AttackImgLeft = new ImageIcon[5];
		AttackEffectImg = new ImageIcon[15];
		DieImgLeft = new ImageIcon[6];

		StandImgLeft[0] = Maplestory.images.MushMomStand;

		MoveImgLeft[0] = Maplestory.images.MushMomMove0;
		MoveImgLeft[1] = Maplestory.images.MushMomMove1;
		MoveImgLeft[2] = Maplestory.images.MushMomMove2;
		MoveImgLeft[3] = Maplestory.images.MushMomMove3;
		MoveImgLeft[4] = Maplestory.images.MushMomMove4;
		MoveImgLeft[5] = Maplestory.images.MushMomMove5;

		JumpImgLeft[0] = Maplestory.images.MushMomJump;

		AttackImgLeft[0] = Maplestory.images.MushMomAttack0;
		AttackImgLeft[1] = Maplestory.images.MushMomAttack1;
		AttackImgLeft[2] = Maplestory.images.MushMomAttack2;
		AttackImgLeft[3] = Maplestory.images.MushMomAttack3;
		AttackImgLeft[4] = Maplestory.images.MushMomAttack4;
		
		AttackEffectImg[0] = Maplestory.images.MushMomAttackEffect0;
		AttackEffectImg[1] = Maplestory.images.MushMomAttackEffect1;
		AttackEffectImg[2] = Maplestory.images.MushMomAttackEffect2;
		AttackEffectImg[3] = Maplestory.images.MushMomAttackEffect3;
		AttackEffectImg[4] = Maplestory.images.MushMomAttackEffect4;
		AttackEffectImg[5] = Maplestory.images.MushMomAttackEffect5;
		AttackEffectImg[6] = Maplestory.images.MushMomAttackEffect6;
		AttackEffectImg[7] = Maplestory.images.MushMomAttackEffect7;
		AttackEffectImg[8] = Maplestory.images.MushMomAttackEffect8;
		AttackEffectImg[9] = Maplestory.images.MushMomAttackEffect9;
		AttackEffectImg[10] = Maplestory.images.MushMomAttackEffect10;
		AttackEffectImg[11] = Maplestory.images.MushMomAttackEffect11;
		AttackEffectImg[12] = Maplestory.images.MushMomAttackEffect12;
		AttackEffectImg[13] = Maplestory.images.MushMomAttackEffect13;
		AttackEffectImg[14] = Maplestory.images.MushMomAttackEffect14;
		
		DieImgLeft[0] = Maplestory.images.MushMomDie0;
		DieImgLeft[1] = Maplestory.images.MushMomDie1;
		DieImgLeft[2] = Maplestory.images.MushMomDie2;
		DieImgLeft[3] = Maplestory.images.MushMomDie3;
		DieImgLeft[4] = Maplestory.images.MushMomDie4;
		DieImgLeft[5] = Maplestory.images.MushMomDie5;

		HitImgLeft = Maplestory.images.MushMomHit;
		
		StandXOffset = new int[1];
		MoveXOffset = new int[6];
		JumpXOffset = new int[1];
		AttackXOffset = new int[5];
		AttackEffectXOffset = new int[15];
		DieXOffset = new int[6];
		
		StandYOffset = new int[1];
		MoveYOffset = new int[6];
		JumpYOffset = new int[1];
		AttackYOffset = new int[5];
		AttackEffectYOffset = new int[15];
		DieYOffset = new int[6];
		
		StandXOffset[0] = 0;
		StandYOffset[0] = 0;
		
		MoveXOffset[0] = 0;
		MoveYOffset[0] = 0;
		MoveXOffset[1] = -1;
		MoveYOffset[1] = 0;
		MoveXOffset[2] = 0;
		MoveYOffset[2] = 0;
		MoveXOffset[3] = 0;
		MoveYOffset[3] = -55;
		MoveXOffset[4] = 0;
		MoveYOffset[4] = -35;
		MoveXOffset[5] = -1;
		MoveYOffset[5] = 0;

		JumpXOffset[0] = 0;
		JumpYOffset[0] = 0;
		
		AttackXOffset[0] = 0;
		AttackYOffset[0] = 0;
		AttackXOffset[1] = 0;
		AttackYOffset[1] = -88;
		AttackXOffset[2] = -1;
		AttackYOffset[2] = -70;
		AttackXOffset[3] = -1;
		AttackYOffset[3] = -8;
		AttackXOffset[4] = 1;
		AttackYOffset[4] = 0;
		
		AttackEffectXOffset[0] = 13;
		AttackEffectYOffset[0] = -63;
		AttackEffectXOffset[1] = 8;
		AttackEffectYOffset[1] = -69;
		AttackEffectXOffset[2] = 1;
		AttackEffectYOffset[2] = -78;
		AttackEffectXOffset[3] = 0;
		AttackEffectYOffset[3] = -80;
		AttackEffectXOffset[4] = 6;
		AttackEffectYOffset[4] = -75;
		AttackEffectXOffset[5] = 40;
		AttackEffectYOffset[5] = -77;
		AttackEffectXOffset[6] = 40;
		AttackEffectYOffset[6] = -78;
		AttackEffectXOffset[7] = 0;
		AttackEffectYOffset[7] = 0;
		AttackEffectXOffset[8] = -49;
		AttackEffectYOffset[8] = -50;
		AttackEffectXOffset[9] = -63;
		AttackEffectYOffset[9] = -54;
		AttackEffectXOffset[10] = -70;
		AttackEffectYOffset[10] = -58;
		AttackEffectXOffset[11] = -42;
		AttackEffectYOffset[11] = -60;
		AttackEffectXOffset[12] = -43;
		AttackEffectYOffset[12] = -61;
		AttackEffectXOffset[13] = -44;
		AttackEffectYOffset[13] = -64;
		AttackEffectXOffset[14] = 0;
		AttackEffectYOffset[14] = 0;
		
		DieXOffset[0] = -1;
		DieYOffset[0] = 0;
		DieXOffset[1] = -23;
		DieYOffset[1] = 3;
		DieXOffset[2] = -31;
		DieYOffset[2] = 1;
		DieXOffset[3] = -31;
		DieYOffset[3] = 0;
		DieXOffset[4] = -45;
		DieYOffset[4] = 0;
		DieXOffset[5] = -37;
		DieYOffset[5] = 0;
		
		HitXOffset = 0;
		HitYOffset = 0;
		
		current_Img = StandImgLeft[0];
		xOffset = StandXOffset[0];
		yOffset = StandYOffset[0];
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
		return xOffset;
	}
	public int getYOffset() {
		return yOffset;
	}
	public long getExp() {
		return Exp;
	}

	@Override
	public void drawEffect(Graphics g, ImageObserver observer) {
		if(current_EffectImg != null) {
			int CameraX = Maplestory.current_stage.CameraX, CameraY = Maplestory.current_stage.CameraY;
			if(Direction == -1) {
				g.drawImage(current_EffectImg.getImage(), X - CameraX + effectXOffset,
						Y - CameraY + yOffset + effectYOffset, observer);
			}
			else if(Direction == 1) {
				//flip image left and right
				int effectWidth = current_EffectImg.getIconWidth();
				int effectHeight = current_EffectImg.getIconHeight();
				g.drawImage(current_EffectImg.getImage(), X-CameraX+width-effectWidth-effectXOffset
						, Y-CameraY+yOffset+effectYOffset, X-CameraX+width-effectXOffset
						, Y-CameraY+yOffset+effectYOffset+effectHeight, effectWidth, 0, 0, effectHeight, observer);
			}
		}
	}
	
	@Override
	public void stand() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					if (!alive) {
						break;
					}
					if (!hit) {
						current_Img = StandImgLeft[0];
						xOffset = StandXOffset[0];
						yOffset = StandYOffset[0];
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

	@Override
	public void move() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				if (Direction == -1) {
					for (int i = 0; i < 90; i++) {
						if (!alive) {
							break;
						}
						if (X <= cur_foothold.firstX()) {
							break;
						}
						if (!hit) {
							if (i % 15 == 0) {
								current_Img = MoveImgLeft[(i / 15) % 6];
								xOffset = MoveXOffset[(i / 15) % 6];
								yOffset = MoveYOffset[(i / 15) % 6];
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
						bodyAttack();
					}
				} else if (Direction == 1) {
					for (int i = 0; i < 90; i++) {
						if (!alive) {
							break;
						}
						if (X + width > cur_foothold.lastX()) {
							break;
						}
						if (!hit) {
							if (i % 15 == 0) {
								current_Img = MoveImgLeft[(i / 15) % 6];
								xOffset = MoveXOffset[(i / 15) % 6];
								yOffset = MoveYOffset[(i / 15) % 6];
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
						bodyAttack();
					}
				}

				done();
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}
	
	public void jump() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				if ((X - 60 >= cur_foothold.firstX()) && (X + 60 + width <= cur_foothold.lastX())) {
					if (Direction == -1) {
						if (!hit) {
							current_Img = JumpImgLeft[0];
							xOffset = JumpXOffset[0];
							yOffset = JumpYOffset[0];
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
							bodyAttack();
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
							bodyAttack();
						}
						if (!hit) {
							current_Img = StandImgLeft[0];
							xOffset = StandXOffset[0];
							yOffset = StandYOffset[0];
						}
					} else if (Direction == 1) {
						if (!hit) {
							current_Img = JumpImgLeft[0];
							xOffset = JumpXOffset[0];
							yOffset = JumpYOffset[0];
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
							bodyAttack();
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
							bodyAttack();
						}
						if (!hit) {
							current_Img = StandImgLeft[0];
							xOffset = StandXOffset[0];
							yOffset = StandYOffset[0];
						}
					}
				}

				done();
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}
	
	@Override
	public void trackPlayer() {
		if(alive) {
			if(X + width / 2 >= Maplestory.player.PlayerX + Player.PlayerWidth / 2) {
				if(X + width / 2 <= Maplestory.player.PlayerX + Player.PlayerWidth / 2 + 200) {
					if(Math.abs(Y - Maplestory.player.PlayerY) < 150) {
						attack1();
					}
					else {
						Direction = -1;
						move();
					}
				}
				else {
					Direction = -1;
					move();
				}
			}
			else {
				if(X + width / 2 >= Maplestory.player.PlayerX + Player.PlayerWidth / 2 - 200) {
					if(Math.abs(Y - Maplestory.player.PlayerY) < 150) {
						attack1();
					}
					else {
						Direction = 1;
						move();
					}
				}
				else {
					Direction = 1;
					move();
				}
			}
		}
	}

	@Override
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
					if (num >= 1 && num <= Maplestory.player.CriticalRate) {
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
							if(!isAttacking) {
								current_Img = HitImgLeft;
								xOffset = HitXOffset;
								yOffset = HitYOffset;
							}
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
				if(current_Img == HitImgLeft) {
					current_Img = StandImgLeft[0];
				}
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	@Override
	public void dropItem() {
		synchronized(Maplestory.current_stage.Item_List) {
			int itemXpos = X + current_Img.getIconWidth() / 2 - 60;
			
			//MushMom Spore
			Item item = new Item_MushMom_spore(1);			
			item.Drop(itemXpos, Y - current_Img.getIconHeight() / 2);

			//Meso
			item = new Item_Meso((int)(level * (random.nextDouble() * 1.5 + 1.5)));
			item.Drop(itemXpos + 15, Y - current_Img.getIconHeight() / 2);

			item = new Item_Meso((int)(level * (random.nextDouble() * 1.5 + 1.5)));
			item.Drop(itemXpos + 30, Y - current_Img.getIconHeight() / 2);

			item = new Item_Meso((int)(level * (random.nextDouble() * 1.5 + 1.5)));
			item.Drop(itemXpos + 45, Y - current_Img.getIconHeight() / 2);

			item = new Item_Meso((int)(level * (random.nextDouble() * 1.5 + 1.5)));
			item.Drop(itemXpos + 60, Y - current_Img.getIconHeight() / 2);

			item = new Item_Meso((int)(level * (random.nextDouble() * 1.5 + 1.5)));
			item.Drop(itemXpos + 75, Y - current_Img.getIconHeight() / 2);

			item = new Item_Meso((int)(level * (random.nextDouble() * 1.5 + 1.5)));
			item.Drop(itemXpos + 90, Y - current_Img.getIconHeight() / 2);

			item = new Item_Meso((int)(level * (random.nextDouble() * 1.5 + 1.5)));
			item.Drop(itemXpos + 105, Y - current_Img.getIconHeight() / 2);

			item = new Item_Meso((int)(level * (random.nextDouble() * 1.5 + 1.5)));
			item.Drop(itemXpos + 120, Y - current_Img.getIconHeight() / 2);
			
			//Exp
			Maplestory.player.gainExp(Exp);
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

	@Override
	public void die() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Music Die_Music = new Music(Die_Music_Name, 1);
				Die_Music.play();
				
				current_Img = DieImgLeft[0];
				xOffset = DieXOffset[0];
				yOffset = DieYOffset[0];
				try {
					Thread.sleep(Mob_Die_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current_Img = DieImgLeft[1];
				xOffset = DieXOffset[1];
				yOffset = DieYOffset[1];
				try {
					Thread.sleep(Mob_Die_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current_Img = DieImgLeft[2];
				xOffset = DieXOffset[2];
				yOffset = DieYOffset[2];
				try {
					Thread.sleep(Mob_Die_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current_Img = DieImgLeft[3];
				xOffset = DieXOffset[3];
				yOffset = DieYOffset[3];
				try {
					Thread.sleep(Mob_Die_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current_Img = DieImgLeft[4];
				xOffset = DieXOffset[4];
				yOffset = DieYOffset[4];
				alpha = 0.8f;
				try {
					Thread.sleep(Mob_Die_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current_Img = DieImgLeft[5];
				xOffset = DieXOffset[5];
				yOffset = DieYOffset[5];
				for (float i = 0.7f; i > 0f; i -= 0.1f) {
					alpha = i;
					try {
						Thread.sleep(Mob_Die_Delay / 2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				alpha = 0f;
				
				try {
					Thread.sleep(respawnPeriod);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(respawn) {
					respawn();
				}
			}

		};
		Maplestory.thread_pool.submit(runnable);
	}

	@Override
	public void start() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				isStart = true;

				if((System.currentTimeMillis() >= hit_time) && (System.currentTimeMillis() <= hit_time + 60000)) {
					trackPlayer();
					return;
				}

				int kind, direction;
				kind = random.nextInt(3);
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
							} else if (kind == 2) {
								jump();
							}
						}
					} else if (direction == 1) {
						if (Direction == -1) {
							Direction = 1;
							move();
						} else {
							if (kind == 1) {
								move();
							} else if (kind == 2) {
								jump();
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

	@Override
	public void done() {
		isStart = false;
		if (available && alive) {
			start();
		}
	}

	@Override
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
				current_Img = StandImgLeft[0];
				xOffset = StandXOffset[0];
				yOffset = StandYOffset[0];

				alpha = 1f;
				hit_time = 0;

				start();
			}

		};

		Maplestory.thread_pool.submit(runnable);
	}

	@Override
	public void bodyAttack() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				int mob_height = current_Img.getIconHeight();

				// character is attacked
				if ((Maplestory.player.PlayerY >= Y - mob_height + 10)
						&& (Maplestory.player.PlayerY <= Y + Player.PlayerHeight - 10)) {
					if ((Maplestory.player.PlayerX >= X - Player.PlayerWidth + 10)
							&& (Maplestory.player.PlayerX <= X + width - 10)) {
						int damage = Hit_Damage_Calculate(Min_ATK, Max_ATK, 1);
						Maplestory.player.HP_Damage(damage);
						int num = random.nextInt(100)+1;
						if (num >= 1 && num <= 100 - Maplestory.player.Stance) {
							if(damage > 0) {
								if (Maplestory.player.PlayerX + Player.PlayerWidth / 2 >= X + width / 2) {
									Maplestory.player.Character_Attacked2(1);
								} else {
									Maplestory.player.Character_Attacked2(-1);
								}
							}
							else {
								Player.ManageHittable();
							}
						}
						else {
							Player.ManageHittable();
						}
					}
				}
			}
		};

		if (Map.Hittable && !hit && Map.Available && Maplestory.player.HP != 0 && alive) {
			Maplestory.thread_pool.submit(runnable);
		}
	}
	
	public void attack1() {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				Music Attack_Music = new Music(Attack_Music_Name, 1);
				Attack_Music.play();
				isAttacking = true;
				
				current_Img = AttackImgLeft[0];
				xOffset = AttackXOffset[0];
				yOffset = AttackYOffset[0];
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				bodyAttack();
				
				current_Img = AttackImgLeft[1];
				xOffset = AttackXOffset[1];
				yOffset = AttackYOffset[1];
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				current_Img = AttackImgLeft[2];
				xOffset = AttackXOffset[2];
				yOffset = AttackYOffset[2];
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				current_Img = AttackImgLeft[3];
				xOffset = AttackXOffset[3];
				yOffset = AttackYOffset[3];
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				skill1();
				
				current_Img = AttackImgLeft[4];
				xOffset = AttackXOffset[4];
				yOffset = AttackYOffset[4];
				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				bodyAttack();
				
				isAttacking = false;
				done();
			}
		};
		
		Runnable effectChange = new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<15;i++) {
					current_EffectImg = AttackEffectImg[i];
					effectXOffset = AttackEffectXOffset[i];
					effectYOffset = AttackEffectYOffset[i];
					
					try {
						Thread.sleep(EffectDelay[i]);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				current_EffectImg = null;
			}
			
		};
		
		Maplestory.thread_pool.submit(runnable);
		Maplestory.thread_pool.submit(effectChange);
	}
	
	public void skill1() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				// character is attacked
				if (!Map.Jump && !Map.Ladder_Jump) {
					int damage = Hit_Damage_Calculate(Min_ATK, Max_ATK, 2);
					Maplestory.player.HP_Damage(damage);
					int num = random.nextInt(100)+1;
					if (num >= 1 && num <= 100 - Maplestory.player.Stance) {
						if(damage > 0) {
							if (Maplestory.player.PlayerX + Player.PlayerWidth / 2 >= X + width / 2) {
								Maplestory.player.Character_Attacked2(1);
							} else {
								Maplestory.player.Character_Attacked2(-1);
							}
						}
						else {
							Player.ManageHittable();
						}
					}
					else {
						Player.ManageHittable();
					}
				}
			}
		};
		if(Map.Available && Maplestory.player.HP != 0 && alive) {
			Maplestory.thread_pool.submit(runnable);
		}
	}

}
