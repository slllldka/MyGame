package maplestory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class UI_Status_Bar extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int length = 0;
	
	//Notice at Chatting Window
	protected static String notice = "";

	protected float hp_bar_percent = 1;
	protected float mp_bar_percent = 1;
	protected float exp_bar_percent = 0;
	
	protected Font font = new Font("굴림", Font.PLAIN, 11);
	
	public UI_Status_Bar(JButton BackButton) {
		setBounds(0, 529, 800, 71);
		BackButton.setBounds(730, 1, 70, 70);
		add(BackButton);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(Maplestory.images.UI_BackGround1Img.getImage(), 0, 0, this);
		g.drawImage(Maplestory.images.UI_BackGround2Img.getImage(), 0, 0, this);
		
		// Chatting Space
		g.setColor(Color.GRAY);
		g.fillRect(0, 5, 565, 24);
		
		// Level
		drawLevel(g);
		
		// HP, MP, EXP Num
		g.setColor(Color.WHITE);
		g.setFont(font);
		drawHp(g);
		drawMp(g);
		drawExp(g);
		
		// HP, MP, EXP Bar
		g.drawImage(Maplestory.images.UI_GaugeBarImg.getImage(), 216, 38, this);
		
		// HP: 218,52 ~ 322,67
		length = 105 - (int)(105 * hp_bar_percent);
		g.drawImage(Maplestory.images.UI_GaugeBar_GrayImg.getImage(), 323-length, 52, length, 16, this);
		
		// MP: 326,52 ~ 430,67
		length = 105 - (int)(105 * mp_bar_percent);
		g.drawImage(Maplestory.images.UI_GaugeBar_GrayImg.getImage(), 431-length, 52, length, 16, this);
		
		// EXP: 439,52 ~ 553,67
		length = 115 - (int)(115 * exp_bar_percent);
		g.drawImage(Maplestory.images.UI_GaugeBar_GrayImg.getImage(), 554-length, 52, length, 16, this);
		
		g.drawImage(Maplestory.images.UI_GaugeBar_GraduationImg.getImage(), 216, 37, this);
		
		//Chatting
		g.setColor(Color.YELLOW);
		g.setFont(Stage.font_meso);
		g.drawString(notice, 5, 22);
	}
	
	public void drawLevel(Graphics g) {
		int level = Maplestory.player.Level;
		int first = 0, second = 0, third = 0;
		ImageIcon[] array = Maplestory.images.Level_Num;
		if(level >= 1 && level < 10) {
			first = level;
			g.drawImage(array[first].getImage(), 48, 47, this);
		}
		else if(level >= 10 && level < 100) {
			first = level / 10;
			second = level - first * 10;
			g.drawImage(array[first].getImage(), 41, 47, this);
			g.drawImage(array[second].getImage(), 53, 47, this);
		}
		else if(level >= 100) {
			first = level / 100;
			second = (level - first * 100) / 10;
			third = level - first * 100 - second * 10;
			g.drawImage(array[first].getImage(), 36, 47, this);
			g.drawImage(array[second].getImage(), 48, 47, this);
			g.drawImage(array[third].getImage(), 60, 47, this);
		}
	}
	
	public void drawHp(Graphics g) {
		int MaxHP = Maplestory.player.MaxHP;
		int HP = Maplestory.player.HP;
		int xpos = 216 + 20, ypos = 38 + 11;
		g.drawString("["+HP+"/"+MaxHP+"]", xpos, ypos);
	}

	public void drawMp(Graphics g) {
		int MaxMP = Maplestory.player.MaxMP;
		int MP = Maplestory.player.MP;
		int xpos = 216 + 131, ypos = 38 + 11;
		g.drawString("["+MP+"/"+MaxMP+"]", xpos, ypos);
	}
	
	public void drawExp(Graphics g) {
		long MaxExp = Maplestory.player.MaxExp;
		long Exp = Maplestory.player.Exp;
		int xpos = 216 + 248, ypos = 38 + 11;
		g.drawString(Exp+" ["+String.format("%.2f", (double)Exp/(double)MaxExp*100)+"%]", xpos, ypos);
	}
	
	public void Manage_HpBar() {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				int cur_hp = Maplestory.player.HP;
				float prev_percent = hp_bar_percent;
				float cur_percent = cur_hp / (float)Maplestory.player.MaxHP;
				float diff = cur_percent - prev_percent;
				
				for(int i=1;i<=300;i++) {
					if(cur_hp != Maplestory.player.HP) {
						return;
					}
					
					hp_bar_percent = prev_percent + i * diff / 300;
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				hp_bar_percent = cur_percent;
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	public void Manage_MpBar() {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				int cur_mp = Maplestory.player.MP;
				float prev_percent = mp_bar_percent;
				float cur_percent = cur_mp / (float)Maplestory.player.MaxMP;
				float diff = cur_percent - prev_percent;
				
				for(int i=1;i<=300;i++) {
					if(cur_mp != Maplestory.player.MP) {
						return;
					}
					
					mp_bar_percent = prev_percent + i * diff / 300;
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				mp_bar_percent = cur_percent;
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	public void Manage_ExpBar() {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				long cur_exp = Maplestory.player.Exp;
				float prev_percent = exp_bar_percent;
				float cur_percent = cur_exp / (float)Maplestory.player.MaxExp;
				float diff = cur_percent - prev_percent;
				
				for(int i=1;i<=300;i++) {
					if(cur_exp != Maplestory.player.Exp) {
						return;
					}
					
					exp_bar_percent = prev_percent + i * diff / 300;
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				exp_bar_percent = cur_percent;
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}

}
