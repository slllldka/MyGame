package maplestory;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Map3 extends Map{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int MeteorNum; //number of meteors(throwing stars)
	public Map3(int CD, String BGName, String GName, String FH1Name, String FH2Name, JFrame _frame, JButton _back
			, boolean _isTown, Map _nearestTown) {
		super(CD, BGName, GName, FH1Name, FH2Name, _frame, _back, _isTown, _nearestTown);
		
		theme = "SleepyWood";
		
		Stage_Music = new Music("SleepyWood.wav", -1);
		StageNum = 3;
		
		X_Size = 1800;
		Y_Size = 900;
		
		CameraFirstX = 1000;
		CameraFirstY = 300;

		MiniMap_Available = true;
		
		super.setCharacter(1700, 750);
		
		//add footholds
		super.setFootholds(new Foothold(new FH_Part(new Point(0, 750), new Point(1800, 750))));
		super.setFootholds(new Foothold(new FH_Part(new Point(1490, 250), new Point(1610, 250))));
		super.setFootholds(new Foothold(new FH_Part(new Point(1390, 250), new Point(1440, 250))));
		super.setFootholds(new Foothold(new FH_Part(new Point(1315, 300), new Point(1365, 300))));
		super.setFootholds(new Foothold(new FH_Part(new Point(1240, 350), new Point(1290, 350))));
		super.setFootholds(new Foothold(new FH_Part(new Point(1165, 400), new Point(1215, 400))));
		super.setFootholds(new Foothold(new FH_Part(new Point(1065, 400), new Point(1115, 400))));
		super.setFootholds(new Foothold(new FH_Part(new Point(965, 400), new Point(1015, 400))));
		super.setFootholds(new Foothold(new FH_Part(new Point(890, 350), new Point(940, 350))));
		super.setFootholds(new Foothold(new FH_Part(new Point(815, 300), new Point(865, 300))));
		super.setFootholds(new Foothold(new FH_Part(new Point(500, 610), new Point(620, 610))));

		//add Walls
		setDefaultWalls();
		
		//add ladders
		super.setLadders(new Ladder(1550, 250, 7));
		
		//add meteors(throwing stars)
		MeteorImg = new ImageIcon(getClass().getClassLoader().getResource("ThrowingStar.png"));
		super.setMeteors(new MeteorInfo(1645, 290, 20, 20, -1, 0, 420, 3, true));
		super.setMeteors(new MeteorInfo(1445, 420, 20, 20, 1, 0, 420, 3, true));
		super.setMeteors(new MeteorInfo(1645, 550, 20, 20, -1, 0, 420, 3, true));
		super.setMeteors(new MeteorInfo(980, 495, 20, 20, 0, -1, 400, 3, true));
		super.setMeteors(new MeteorInfo(710, 500, 20, 20, 0, -1, 800, 1, true));
		super.setMeteors(new MeteorInfo(1405, 370, 20, 20, -1, 0, 1200, 2, true));
		
		//add Timers
		Timer_List.add(new Timer(2800, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveMeteor(Meteor_List.get(0));
				moveMeteor(Meteor_List.get(1));
				moveMeteor(Meteor_List.get(2));
			}
		}));
		Timer_List.add(new Timer(1800, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveMeteor(Meteor_List.get(3));
				moveMeteor(Meteor_List.get(4));
			}
		}));
		Timer_List.add(new Timer(3500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveMeteor(Meteor_List.get(5));
			}
		}));
		
		//add mobs
		//Mob_List.add(new Mob_Orange_Mushroom(70, Foothold_List.get(0), true));
	}

}
