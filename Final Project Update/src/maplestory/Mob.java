package maplestory;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.ImageIcon;

public abstract class Mob {
	protected boolean isBoss = false;
	
	protected Map map = null;
	
	protected int FirstX, FirstY, X, Y;
	protected long HP, MP;
	protected int Direction; // Left: -1, Right: 1
	protected ImageIcon current_Img, current_EffectImg = null, gaugeIcon = null;
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
		for (Foothold foothold : map.Foothold_List) {
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
	
	public int Hit_Damage_Calculate(int _Min_ATK, int _Max_ATK, double damagePercent) {
		int minDamage = (int)(_Min_ATK*damagePercent);
		int maxDamage = (int)(_Max_ATK*damagePercent);
		int damage = random.nextInt(maxDamage - minDamage+1) + minDamage - Maplestory.player.DEF;
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
	public abstract int getHeight();
	public abstract int getXOffset();
	public int getYOffset() {
		return 0;
	};
	public abstract long getExp();
	
	public void drawEffect(Graphics g, ImageObserver observer) {}
	
	public abstract void stand();
	public abstract void move();
	public void jump() {};
	public void trackPlayer() {};
	
	public abstract void hit(int stroke_num, int Damage_Percent);
	public abstract void dropItem();
	public abstract void die();
	public abstract void start();
	public abstract void done();
	public abstract void respawn();
	public abstract void bodyAttack();
	
}