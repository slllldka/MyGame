package maplestory;

import java.util.Random;

import javax.swing.ImageIcon;

public abstract class Mob {
	protected boolean isBoss = false;
	
	protected Stage stage = null;
	
	protected int FirstX, FirstY, X, Y;
	protected long HP, MP;
	protected int Direction; // Left: -1, Right: 1
	protected ImageIcon current_Img;
	protected Foothold cur_foothold;
	protected boolean hit, alive, available, isStart, respawn, dropped_item;
	protected boolean First_respawn;
	protected float alpha;
	protected long hit_time;
	
	protected static Random random = new Random();
	
	// Delays
	protected static final int Mob_Stand_Delay = 200;
	protected static final int Mob_Move_Delay = 10;
	protected static final int Mob_Jump_Delay = 8;
	protected static final int Mob_Hit_Image_Delay = 500;
	protected static final int Mob_Die_Delay = 200;

	public Mob(Foothold _foothold, boolean _respawn) {
		cur_foothold = _foothold;

		hit = false;
		alive = true;
		available = false;
		isStart = false;
		dropped_item = false;

		Direction = -1;

		alpha = 1f;
		hit_time = 0;

		First_respawn = respawn = _respawn;
	}
	
	public boolean isLandable() {
		int MX_CENTER = X + getWidth() / 2;
		for (Foothold foothold : stage.Foothold_List) {
			if(foothold.isLandable(MX_CENTER, Y)) {
				cur_foothold = foothold;
				Y = cur_foothold.getY(MX_CENTER);
				return true;
			}
		}
		cur_foothold = null;
		return false;
	}

	public int Attack_Damage_Calculate(boolean isCrit, int Mob_DEF, int Damage_Percent) {
		int damage = random.nextInt(Maplestory.player.Max_ATK-Maplestory.player.Min_ATK+1)+Maplestory.player.Min_ATK;
		damage = damage * Damage_Percent / 100;
		if(isCrit) {
			damage *= 1 + (random.nextInt(31)+120) / 100;
		}

		damage -= Mob_DEF;
		
		if(damage > 9999) {
			damage = 9999;
		}
		else if(damage < 0) {
			damage = 0;
		}
		
		return damage;
	}
	
	public int Hit_Damage_Calculate(int _Min_ATK, int _Max_ATK) {
		int damage = random.nextInt(_Max_ATK - _Min_ATK+1) + _Min_ATK - Maplestory.player.DEF;
		damage = damage * getLevel() / Maplestory.player.Level;
		if(damage < 0) {
			damage = 0;
		}
		
		return damage;
	}
	
	public String Make_ExpMsg(long quantity) {
		String msg = "";
		msg += "경험치를 얻었습니다 (+" + quantity + ")";
		
		return msg;
	}
	
	public abstract String getName();
	public abstract int getLevel();
	public abstract long getMaxHP();
	public abstract int getWidth();
	public abstract int getOffset();
	public abstract long getExp();
	public abstract void Mob_Stand();
	public abstract void Mob_Move();
	public void Mob_Jump() {};
	public abstract void Mob_Hit(int stroke_num, int Damage_Percent);
	public abstract void Mob_DropItem();
	public abstract void Mob_Die();
	public abstract void Mob_Start();
	public abstract void Mob_Done();
	public abstract void Mob_Respawn();
	public abstract void Mob_BodyAttack();
	
}