package maplestory;

import javax.swing.ImageIcon;

public class Mob_MushMom extends Mob {
	protected static final String name = "머쉬맘";
	protected static final int width = 60;
	protected static final int level = 8;
	protected static final int MaxHP = 10000, MaxMP = 100;
	protected static final int Min_ATK = 8, Max_ATK = 16;
	protected static final int DEF = 10;
	protected static final long Exp = 15;
	protected static ImageIcon StandImgLeft[], MoveImgLeft[], JumpImgLeft[], DieImgLeft[], HitImgLeft;
	protected static String Damage_Music_Name = "Mushroom_Damage.wav";
	protected static String Die_Music_Name = "Blue_Orange_Mushroom_Die.wav";

	public Mob_MushMom(int Location_Percent, Foothold _foothold, boolean _respawn) {
		super(_foothold, _respawn);
		
		isBoss = true;

		FirstX = X = (_foothold.lastX() - width / 2 - _foothold.firstX() - width / 2) * Location_Percent / 100 
				+ _foothold.firstX() - width/2;
		FirstY = Y = _foothold.getY(FirstX + width / 2);

		HP = MaxHP;
		MP = MaxMP;
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

	@Override
	public void Mob_Stand() {
	}

	@Override
	public void Mob_Move() {
	}

	@Override
	public void Mob_Hit(int stroke_num, int Damage_Percent) {
	}

	@Override
	public void Mob_DropItem() {
	}

	@Override
	public void Mob_Die() {
	}

	@Override
	public void Mob_Start() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				//if()
			}
			
		};
		
		if(!isStart) {
			Maplestory.thread_pool.submit(runnable);
		}
	}

	@Override
	public void Mob_Done() {
	}

	@Override
	public void Mob_Respawn() {
	}

	@Override
	public void Mob_BodyAttack() {
	}
	
	public void Skill1() {
		
	}
	
	public void Skill2() {
		
	}

}
