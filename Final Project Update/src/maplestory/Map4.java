package maplestory;

import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Map4 extends Map {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Map4(int CD, String BGName, String GName, String FH1Name, String FH2Name, JFrame _frame, JButton _back
			, boolean _isTown, Map _nearestTown) {
		super(CD, BGName, GName, FH1Name, FH2Name, _frame, _back, _isTown, _nearestTown);
		
		theme = "Ellinia";
		
		Stage_Music = new Music("Orbis.wav", -1);
		StageNum = 4;
		
		X_Size = 1800;
		Y_Size = 900;
		
		CameraFirstX = 0;
		CameraFirstY = 300;

		MiniMap_Available = true;
		
		super.setCharacter(50, 750);

		//add footholds
		super.setFootholds(new Foothold(new FH_Part(new Point(0, 750), new Point(2300, 750))));

		Mob_List.add(new Mob_Blue_Mushroom(10, Foothold_List.get(0), true));
		Mob_List.add(new Mob_Blue_Mushroom(20, Foothold_List.get(0), true));
		Mob_List.add(new Mob_Blue_Mushroom(30, Foothold_List.get(0), true));
		Mob_List.add(new Mob_Blue_Mushroom(40, Foothold_List.get(0), true));
		Mob_List.add(new Mob_Blue_Mushroom(50, Foothold_List.get(0), true));
		
		Mob_List.add(new Mob_Orange_Mushroom(60, Foothold_List.get(0), true));
		Mob_List.add(new Mob_Orange_Mushroom(70, Foothold_List.get(0), true));
		Mob_List.add(new Mob_Orange_Mushroom(80, Foothold_List.get(0), true));
		Mob_List.add(new Mob_Orange_Mushroom(90, Foothold_List.get(0), true));

		//add Walls
		setDefaultWalls();
	}

}
