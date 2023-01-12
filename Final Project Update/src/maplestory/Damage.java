package maplestory;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Damage {
	protected boolean isAttack;
	protected boolean isCrit;
	protected int damage;
	protected int X, Y;
	protected float alpha = 1f;
	
	public Damage(int _damage, int _X, int _Y, boolean _isAttack, boolean _isCrit) {
		damage = _damage;
		X = _X;
		Y = _Y;
		isAttack = _isAttack;
		isCrit = _isCrit;
		
		Start();
	}
	
	public void Start() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<10;i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Y-=1;
				}
				for(float i=0.9f;i > 0f;i-=0.1f) {
					alpha = i;
					Y-=1;
					try {
						Thread.sleep(50);
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
	
	public void Show_Attack_Damage(Graphics2D g2, ImageObserver ob, int CameraX, int CameraY) {
		Damage_Skin skin = Maplestory.player.damage_skin;
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		if(damage <= 0) {
			g2.drawImage(skin.NoCri[10].getImage(), X - skin.NoCri[10].getIconWidth() / 2 - CameraX
					, Y - skin.NoCri[10].getIconHeight() - CameraY, ob);
		}
		else {
			if(damage > 0  && damage < 10) {
				if(isCrit) {
					g2.drawImage(skin.Cri[10].getImage(), X - skin.Cri[10].getIconWidth() / 2 - 15 - CameraX
							, Y - skin.Cri[10].getIconHeight() - CameraY, ob);
				}
				ImageIcon num = skin.AttackNumIcon(damage, false, isCrit);
				g2.drawImage(num.getImage(), X - num.getIconWidth() / 2 - CameraX, Y - num.getIconHeight() - CameraY, ob);
			}
			else if(damage >= 10 && damage < 100) {
				int Xstart = X - 30;
				int first = damage / 10;
				int second = damage - first*10;
				ImageIcon num1 = skin.AttackNumIcon(first, true, isCrit);
				ImageIcon num2 = skin.AttackNumIcon(second, false, isCrit);
				
				if(isCrit) {
					g2.drawImage(skin.Cri[10].getImage(), Xstart - skin.Cri[10].getIconWidth() / 2 - CameraX
							, Y - skin.Cri[10].getIconHeight() - CameraY, ob);
					g2.drawImage(num1.getImage(), Xstart - CameraX, Y - num1.getIconHeight() - CameraY, ob);
					g2.drawImage(num2.getImage(), Xstart + num1.getIconWidth()*3/4 - CameraX
							, Y - num2.getIconHeight() - CameraY, ob);
				}
				else {
					g2.drawImage(num1.getImage(), Xstart - CameraX, Y - num1.getIconHeight() - CameraY, ob);
					g2.drawImage(num2.getImage(), Xstart + num1.getIconWidth()*4/5 - CameraX
							, Y - num2.getIconHeight() - CameraY, ob);
				}
				
			}
			else if(damage >= 100 && damage < 1000) {
				int Xstart = X - 45;
				int first = damage / 100;
				int second = (damage - first*100) / 10;
				int third = damage - first*100 - second*10;
				ImageIcon num1 = skin.AttackNumIcon(first, true, isCrit);
				ImageIcon num2 = skin.AttackNumIcon(second, false,isCrit);
				ImageIcon num3 = skin.AttackNumIcon(third, false, isCrit);
				
				if(isCrit) {
					g2.drawImage(skin.Cri[10].getImage(), Xstart - skin.Cri[10].getIconWidth() / 2 - CameraX
							, Y - skin.Cri[10].getIconHeight() - CameraY, ob);
					g2.drawImage(num1.getImage(), Xstart - CameraX, Y - num1.getIconHeight() - CameraY, ob);
					g2.drawImage(num2.getImage(), Xstart + num1.getIconWidth()*3/4 - CameraX
							, Y - num2.getIconHeight() - CameraY, ob);
					g2.drawImage(num3.getImage(), Xstart + num1.getIconWidth()*3/4 + num2.getIconWidth()*3/4 - CameraX
							, Y - num3.getIconHeight() - CameraY, ob);
				}
				else {
					g2.drawImage(num1.getImage(), Xstart - CameraX, Y - num1.getIconHeight() - CameraY, ob);
					g2.drawImage(num2.getImage(), Xstart + num1.getIconWidth()*4/5 - CameraX
							, Y - num2.getIconHeight() - CameraY, ob);
					g2.drawImage(num3.getImage(), Xstart + num1.getIconWidth()*4/5 + num2.getIconWidth()*4/5 - CameraX
							, Y - num3.getIconHeight() - CameraY, ob);
				}
			}
			else if(damage >= 1000 && damage < 10000) {
				int Xstart = X - 60;
				int first = damage / 1000;
				int second = (damage - first*1000) / 100;
				int third = (damage - first*1000 - second*100) / 10;
				int fourth = damage - first*1000 - second*100 - third*10;
				ImageIcon num1 = skin.AttackNumIcon(first, true, isCrit);
				ImageIcon num2 = skin.AttackNumIcon(second, false, isCrit);
				ImageIcon num3 = skin.AttackNumIcon(third, false, isCrit);
				ImageIcon num4 = skin.AttackNumIcon(fourth, false, isCrit);
				
				if(isCrit) {
					g2.drawImage(skin.Cri[10].getImage(), Xstart - skin.Cri[10].getIconWidth() / 2 - CameraX
							, Y - skin.Cri[10].getIconHeight() - CameraY, ob);
					g2.drawImage(num1.getImage(), Xstart - CameraX, Y - num1.getIconHeight() - CameraY, ob);
					g2.drawImage(num2.getImage(), Xstart + num1.getIconWidth()*3/4 - CameraX
							, Y - num2.getIconHeight() - CameraY, ob);
					g2.drawImage(num3.getImage(), Xstart + num1.getIconWidth()*3/4 + num2.getIconWidth()*3/4 - CameraX
							, Y - num3.getIconHeight() - CameraY, ob);
					g2.drawImage(num4.getImage()
							, Xstart + num1.getIconWidth()*3/4 + num2.getIconWidth()*3/4 + num3.getIconWidth()*3/4 - CameraX
							, Y - num3.getIconHeight() - CameraY, ob);
				}
				else {
					g2.drawImage(num1.getImage(), Xstart - CameraX, Y - num1.getIconHeight() - CameraY, ob);
					g2.drawImage(num2.getImage(), Xstart + num1.getIconWidth()*4/5 - CameraX
							, Y - num2.getIconHeight() - CameraY, ob);
					g2.drawImage(num3.getImage(), Xstart + num1.getIconWidth()*4/5 + num2.getIconWidth()*4/5 - CameraX
							, Y - num3.getIconHeight() - CameraY, ob);
					g2.drawImage(num4.getImage()
							, Xstart + num1.getIconWidth()*4/5 + num2.getIconWidth()*4/5 + num3.getIconWidth()*4/5 - CameraX,
							Y - num3.getIconHeight() - CameraY, ob);
				}
			}
		}
	}
	
	public void Show_Hit_Damage(Graphics2D g2, ImageObserver ob, int CameraX, int CameraY) {
		Damage_Skin skin = Maplestory.player.damage_skin;
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		if(damage <= 0) {
			g2.drawImage(skin.Hit[10].getImage(), X - skin.Hit[10].getIconWidth() / 2 - CameraX
					, Y - skin.Hit[10].getIconHeight() - CameraY, ob);
		}
		else {
			if(damage > 0  && damage < 10) {
				ImageIcon num = skin.HitNumIcon(damage, false);
				g2.drawImage(num.getImage(), X - num.getIconWidth() / 2 - CameraX, Y - num.getIconHeight() - CameraY, ob);
			}
			else if(damage >= 10 && damage < 100) {
				int Xstart = X - 30;
				int first = damage / 10;
				int second = damage - first*10;
				ImageIcon num1 = skin.HitNumIcon(first, true);
				ImageIcon num2 = skin.HitNumIcon(second, false);
				
				g2.drawImage(num1.getImage(), Xstart - CameraX, Y - num1.getIconHeight() - CameraY, ob);
				g2.drawImage(num2.getImage(), Xstart + num1.getIconWidth()*4/5 - CameraX, Y - num2.getIconHeight() - CameraY, ob);
			}
			else if(damage >= 100 && damage < 1000) {
				int Xstart = X - 45;
				int first = damage / 100;
				int second = (damage - first*100) / 10;
				int third = damage - first*100 - second*10;
				ImageIcon num1 = skin.HitNumIcon(first, true);
				ImageIcon num2 = skin.HitNumIcon(second, false);
				ImageIcon num3 = skin.HitNumIcon(third, false);
				g2.drawImage(num1.getImage(), Xstart - CameraX, Y - num1.getIconHeight() - CameraY, ob);
				g2.drawImage(num2.getImage(), Xstart + num1.getIconWidth()*4/5 - CameraX, Y - num2.getIconHeight() - CameraY, ob);
				g2.drawImage(num3.getImage(), Xstart + num1.getIconWidth()*4/5 + num2.getIconWidth()*4/5 - CameraX
						, Y - num3.getIconHeight() - CameraY, ob);
			}
			else if(damage >= 1000 && damage < 10000) {
				int Xstart = X - 60;
				int first = damage / 1000;
				int second = (damage - first*1000) / 100;
				int third = (damage - first*1000 - second*100) / 10;
				int fourth = damage - first*1000 - second*100 - third*10;
				ImageIcon num1 = skin.HitNumIcon(first, true);
				ImageIcon num2 = skin.HitNumIcon(second, false);
				ImageIcon num3 = skin.HitNumIcon(third, false);
				ImageIcon num4 = skin.HitNumIcon(fourth, false);
				
				g2.drawImage(num1.getImage(), Xstart - CameraX, Y - num1.getIconHeight() - CameraY, ob);
				g2.drawImage(num2.getImage(), Xstart + num1.getIconWidth()*4/5 - CameraX, Y - num2.getIconHeight() - CameraY, ob);
				g2.drawImage(num3.getImage(), Xstart + num1.getIconWidth()*4/5 + num2.getIconWidth()*4/5 - CameraX
						, Y - num3.getIconHeight() - CameraY, ob);
				g2.drawImage(num4.getImage()
						, Xstart + num1.getIconWidth()*4/5 + num2.getIconWidth()*4/5 + num3.getIconWidth()*4/5 - CameraX,
						Y - num3.getIconHeight() - CameraY, ob);
			}
		}
	}
}
