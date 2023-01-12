package maplestory;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Stage2 extends Stage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int MeteorNum; //number of meteors(throwing stars)
	//CD: CharacterDirection, CX: Character Xpos, CY: Character Ypos, CW: Character Width, CH: Character Heigth
	//(B)GName: (Back)Ground Image Name, FHName: FootHold Image Name
	public Stage2(int CD, String BGName, String GName, String FH1Name, String FH2Name, JFrame _frame, JButton _back
			, boolean _isTown, Stage _nearestTown) {
		super(CD, BGName, GName, FH1Name, FH2Name, _frame, _back, _isTown, _nearestTown);
		
		theme = "Ellinia";
		
		Stage_Music = new Music("Ellinia Field.wav", -1);
		StageNum = 2;
		
		X_Size = 1800;
		Y_Size = 900;
		
		CameraFirstX = 0;
		CameraFirstY = 300;

		MiniMap_Available = true;
		
		super.setCharacter(50, 750);
		
		//add footholds
		super.setFootholds(new Foothold(new FH_Part(new Point(0, 750), new Point(2300, 750))));
		super.setFootholds(new Foothold(new FH_Part(new Point(90, 580), new Point(210, 580))));
		super.setFootholds(new Foothold(new FH_Part(new Point(250, 620), new Point(300, 620))));
		super.setFootholds(new Foothold(new FH_Part(new Point(325, 570), new Point(375, 570))));
		super.setFootholds(new Foothold(new FH_Part(new Point(400, 520), new Point(450, 520))));
		super.setFootholds(new Foothold(new FH_Part(new Point(475, 620), new Point(525, 620))));
		super.setFootholds(new Foothold(new FH_Part(new Point(550, 620), new Point(600, 620))));
		super.setFootholds(new Foothold(new FH_Part(new Point(625, 570), new Point(675, 570))));
		super.setFootholds(new Foothold(new FH_Part(new Point(700, 520), new Point(750, 520))));
		super.setFootholds(new Foothold(new FH_Part(new Point(775, 470), new Point(825, 470))));
		super.setFootholds(new Foothold(new FH_Part(new Point(860, 620), new Point(910, 620))));
		super.setFootholds(new Foothold(new FH_Part(new Point(940, 570), new Point(990, 570))));
		super.setFootholds(new Foothold(new FH_Part(new Point(1015, 520), new Point(1065, 520))));
		super.setFootholds(new Foothold(new FH_Part(new Point(1015, 460), new Point(1065, 460))));
		super.setFootholds(new Foothold(new FH_Part(new Point(1150, 460), new Point(1270, 460))));
		super.setFootholds(new Foothold(new FH_Part(new Point(1150, 250), new Point(1270, 250))));
		super.setFootholds(new Foothold(new FH_Part(new Point(1070, 250), new Point(1120, 250))));
		super.setFootholds(new Foothold(new FH_Part(new Point(970, 250), new Point(1020, 250))));
		super.setFootholds(new Foothold(new FH_Part(new Point(870, 250), new Point(920, 250))));
		super.setFootholds(new Foothold(new FH_Part(new Point(770, 250), new Point(820, 250))));
		super.setFootholds(new Foothold(new FH_Part(new Point(670, 250), new Point(720, 250))));
		super.setFootholds(new Foothold(new FH_Part(new Point(570, 250), new Point(620, 250))));
		super.setFootholds(new Foothold(new FH_Part(new Point(470, 250), new Point(520, 250))));
		super.setFootholds(new Foothold(new FH_Part(new Point(370, 250), new Point(420, 250))));
		super.setFootholds(new Foothold(new FH_Part(new Point(270, 250), new Point(320, 250))));
		super.setFootholds(new Foothold(new FH_Part(new Point(0, 300), new Point(120, 300))));
		super.setFootholds(new Foothold(new FH_Part(new Point(80, 300), new Point(200, 300))));

		//add Walls
		setDefaultWalls();
		
		//add ladders
		super.setLadders(new Ladder(150, 580, 2));
		super.setLadders(new Ladder(1210, 460, 2));
		super.setLadders(new Ladder(1210, 250, 2));
		
		//add fake footholds
		super.setFakeFootholds(new Foothold(new FH_Part(new Point(850, 420), new Point(900, 420))));
		
		//add meteors(throwing stars)
		MeteorImg = new ImageIcon(getClass().getClassLoader().getResource("Meteor.png"));
		super.setMeteors(new MeteorInfo(1075, 110, 40, 40, 0, 1, 100, 5, false));
		super.setMeteors(new MeteorInfo(875, 110, 40, 40, 0, 1, 100, 5, false));
		super.setMeteors(new MeteorInfo(675, 110, 40, 40, 0, 1, 100, 5, false));
		super.setMeteors(new MeteorInfo(475, 110, 40, 40, 0, 1, 100, 5, false));
		super.setMeteors(new MeteorInfo(275, 110, 40, 40, 0, 1, 100, 5, false));
		
		//add Timers
		Timer_List.add(new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveMeteor(Meteor_List.get(0));
				moveMeteor(Meteor_List.get(1));
				moveMeteor(Meteor_List.get(2));
				moveMeteor(Meteor_List.get(3));
				moveMeteor(Meteor_List.get(4));
			}
		}));
		
		//add mobs
/*		Mob_List.add(new Mob_Slime(10, Foothold_List.get(0), true));
		Mob_List.add(new Mob_Slime(10, Foothold_List.get(0), true));
		Mob_List.add(new Mob_Slime(10, Foothold_List.get(0), true));
		Mob_List.add(new Mob_Slime(10, Foothold_List.get(0), true));
		
		Mob_List.add(new Mob_Bubbling(10, Foothold_List.get(0), true));
		Mob_List.add(new Mob_Bubbling(10,Foothold_List.get(0), true));
		Mob_List.add(new Mob_Bubbling(10,Foothold_List.get(0), true));
		Mob_List.add(new Mob_Bubbling(10, Foothold_List.get(0), true));*/
	}
	
}

