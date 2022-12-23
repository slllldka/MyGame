package maplestory;

import javax.swing.ImageIcon;

public abstract class Damage_Skin {
	protected ImageIcon[] NoCri = new ImageIcon[11];
	protected ImageIcon[] NoCriBig = new ImageIcon[10];
	protected ImageIcon[] Cri = new ImageIcon[11];
	protected ImageIcon[] CriBig = new ImageIcon[11];
	protected ImageIcon[] Hit = new ImageIcon[11];
	protected ImageIcon[] HitBig = new ImageIcon[10];
	
	public Damage_Skin() {
		Hit[0] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet0.0.png"));
		Hit[1] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet0.1.png"));
		Hit[2] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet0.2.png"));
		Hit[3] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet0.3.png"));
		Hit[4] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet0.4.png"));
		Hit[5] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet0.5.png"));
		Hit[6] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet0.6.png"));
		Hit[7] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet0.7.png"));
		Hit[8] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet0.8.png"));
		Hit[9] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet0.9.png"));
		Hit[10] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet0.Miss.png"));
		
		HitBig[0] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet1.0.png"));
		HitBig[1] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet1.1.png"));
		HitBig[2] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet1.2.png"));
		HitBig[3] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet1.3.png"));
		HitBig[4] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet1.4.png"));
		HitBig[5] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet1.5.png"));
		HitBig[6] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet1.6.png"));
		HitBig[7] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet1.7.png"));
		HitBig[8] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet1.8.png"));
		HitBig[9] = new ImageIcon(getClass().getClassLoader().getResource("NoViolet1.9.png"));
		
	}
	
	public ImageIcon AttackNumIcon(int num, boolean isBig, boolean isCrit) {
		if(isCrit) {
			if(isBig) {
				return CriBig[num];
			}
			else {
				return Cri[num];
			}
		}
		else {
			if(isBig) {
				return NoCriBig[num];
			}
			else {
				return NoCri[num];
			}
		}
	}
	
	public ImageIcon HitNumIcon(int num, boolean isBig) {
		if(isBig) {
			return HitBig[num];
		}
		else {
			return Hit[num];
		}
	}
}
