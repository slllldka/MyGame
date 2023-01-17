package maplestory;


import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Map1 extends Map{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//CD: CharacterDirection, CX: Character Xpos, CY: Character Ypos, CW: Character Width, CH: Character Heigth
	//(B)GName: (Back)Ground Image Name, FHName: FootHold Image Name
	public Map1(int CD, String BGName, String GName, String FH1Name, String FH2Name, JFrame _frame, JButton _back
			, boolean _isTown, Map _nearestTown) {
		super(CD, BGName, GName, FH1Name, FH2Name, _frame, _back, _isTown, _nearestTown);
		
		theme = "Henesys";
		
		Stage_Music = new Music("Henesys.wav", -1);
		StageNum = 1;
		
		X_Size = 1800;
		Y_Size = 1000;
		
		CameraFirstX = 500;
		CameraFirstY = 300;
		
		MiniMap_Available = true;
		
		super.setCharacter(900, 630);
		
		//add footholds
		Foothold_List.add(new Foothold(true, new FH_Part(new Point(-60, 570), new Point(30, 570))
				, new FH_Part(new Point(30, 570), new Point(120, 570))
				, new FH_Part(new Point(120, 570), new Point(210, 570))
				, new FH_Part(new Point(210, 570), new Point(300, 570))
				, new FH_Part(new Point(300, 570), new Point(390, 570))
				, new FH_Part(new Point(390, 570), new Point(480, 570))
				, new FH_Part(new Point(480, 570), new Point(570, 630))
				, new FH_Part(new Point(570, 630), new Point(660, 630))
				, new FH_Part(new Point(660, 630), new Point(750, 630))
				, new FH_Part(new Point(750, 630), new Point(840, 630))
				, new FH_Part(new Point(840, 630), new Point(930, 630))
				, new FH_Part(new Point(930, 630), new Point(1020, 630))
				, new FH_Part(new Point(1020, 630), new Point(1110, 630))
				, new FH_Part(new Point(1110, 630), new Point(1200, 630))
				, new FH_Part(new Point(1200, 630), new Point(1290, 630))
				, new FH_Part(new Point(1290, 630), new Point(1380, 690))
				, new FH_Part(new Point(1380, 690), new Point(1470, 750))
				, new FH_Part(new Point(1470, 750), new Point(1560, 750))
				, new FH_Part(new Point(1560, 750), new Point(1650, 750))
				, new FH_Part(new Point(1650, 750), new Point(1740, 750))
				, new FH_Part(new Point(1740, 750), new Point(1830, 750))));
		Foothold_List.add(new Foothold(new FH_Part(new Point(740, 300), new Point(770, 300))
				, new FH_Part(new Point(770, 300), new Point(860, 300))
				, new FH_Part(new Point(860, 300), new Point(950, 300))
				, new FH_Part(new Point(950, 300), new Point(1040, 300))
				, new FH_Part(new Point(1040, 300), new Point(1130, 300))
				, new FH_Part(new Point(1130, 300), new Point(1220, 300))
				, new FH_Part(new Point(1220, 300), new Point(1310, 300))
				, new FH_Part(new Point(1310, 300), new Point(1340, 300))));
		Foothold_List.add(new Foothold(new FH_Part(new Point(1300, 350), new Point(1330, 350))
				, new FH_Part(new Point(1330, 350), new Point(1420, 350))
				, new FH_Part(new Point(1420, 350), new Point(1510, 350))
				, new FH_Part(new Point(1510, 350), new Point(1600, 350))
				, new FH_Part(new Point(1600, 350), new Point(1690, 350))
				, new FH_Part(new Point(1690, 350), new Point(1780, 350))
				, new FH_Part(new Point(1780, 350), new Point(1810, 350))));

		//add Walls
		setDefaultWalls();

		//add ladders
		super.setLadders(new Ladder(1030, 300, 5));
		
		//add NPCs
		NPC_List.add(new NPC_Shop("루나", Maplestory.images.LunaStand1, Maplestory.generalStore, 850, 300, NPC.RIGHT));

		//add mobs
		//Mob_List.add(new Mob_Orange_Mushroom(50, Foothold_List.get(2), true));
	}
	
}
