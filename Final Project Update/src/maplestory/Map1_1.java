package maplestory;

import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Map1_1 extends Map {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Map1_1(int CD, String BGName, String GName, String FH1Name, String FH2Name, JFrame _frame, JButton _back
			, boolean _isTown, Map _nearestTown) {
		super(CD, BGName, GName, FH1Name, FH2Name, _frame, _back, _isTown, _nearestTown);
		
		theme = "Henesys";
		
		Stage_Music = new Music("Henesys_Field2.wav", -1);
		StageNum = 11;
		
		X_Size = 1800;
		Y_Size = 1500;
		
		CameraX_offset = 3;
		CameraY_offset = 9;
		
		CameraFirstX = 0;
		CameraFirstY = 720;
		
		MiniMap_Available = true;
		
		super.setCharacter(50, 1070);
		
		//add footholds
		int x = 0, y = 0;
		x = 10;
		y = 1070;
		Foothold_List.add(new Foothold(true, new FH_Part(new Point(x-60, y), new Point(x+30, y))
				, new FH_Part(new Point(x+30, y), new Point(x+120, y))
				, new FH_Part(new Point(x+120, y), new Point(x+210, y))
				, new FH_Part(new Point(x+210, y), new Point(x+300, y))
				, new FH_Part(new Point(x+300, y), new Point(x+390, y))
				, new FH_Part(new Point(x+390, y), new Point(x+480, y))
				, new FH_Part(new Point(x+480, y), new Point(x+510, y))));
		x=510;
		y=1250;
		Foothold_List.add(new Foothold(true, new FH_Part(new Point(x+1200, y-150), new Point(x+1230, y-150))
				, new FH_Part(new Point(x+1230, y-150), new Point(x+1320, y-150))));
		Foothold_List.add(new Foothold(true, new FH_Part(new Point(x+1090, y-100), new Point(x+1120, y-100))
				, new FH_Part(new Point(x+1120, y-100), new Point(x+1210, y-100))));
		Foothold_List.add(new Foothold(true, new FH_Part(new Point(x+980, y-50), new Point(x+1010, y-50))
				, new FH_Part(new Point(x+1010, y-50), new Point(x+1100, y-50))));
		Foothold_List.add(new Foothold(true, new FH_Part(new Point(x, y), new Point(x+90, y))
				, new FH_Part(new Point(x+90, y), new Point(x+180, y))
				, new FH_Part(new Point(x+180, y), new Point(x+270, y))
				, new FH_Part(new Point(x+270, y), new Point(x+360, y))
				, new FH_Part(new Point(x+360, y), new Point(x+450, y))
				, new FH_Part(new Point(x+450, y), new Point(x+540, y))
				, new FH_Part(new Point(x+540, y), new Point(x+630, y))
				, new FH_Part(new Point(x+630, y), new Point(x+720, y))
				, new FH_Part(new Point(x+720, y), new Point(x+810, y))
				, new FH_Part(new Point(x+810, y), new Point(x+900, y))
				, new FH_Part(new Point(x+900, y), new Point(x+990, y))
				, new FH_Part(new Point(x+900, y), new Point(x+990, y))));
		x=100;
		y=600;
		Foothold_List.add(new Foothold(new FH_Part(new Point(x, y), new Point(x+30, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+30, y), new Point(x+120, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+120, y), new Point(x+210, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+210, y), new Point(x+300, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+300, y), new Point(x+390, y-60), 0, FH_Part.RIGHT)
				, new FH_Part(new Point(x+390, y-60), new Point(x+480, y-120), 0, FH_Part.RIGHT)
				, new FH_Part(new Point(x+480, y-120), new Point(x+570, y-120), 0, FH_Part.RIGHT)
				, new FH_Part(new Point(x+570, y-120), new Point(x+660, y-120), 0, FH_Part.PLAIN)
				, new FH_Part(new Point(x+660, y-120), new Point(x+690, y-120), 0, FH_Part.PLAIN)));
		x=450;
		y=800;
		Foothold_List.add(new Foothold(new FH_Part(new Point(x, y), new Point(x+30, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+30, y), new Point(x+120, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+120, y), new Point(x+210, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+210, y), new Point(x+300, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+300, y), new Point(x+390, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+390, y), new Point(x+480, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+480, y), new Point(x+570, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+570, y), new Point(x+600, y), 1, FH_Part.PLAIN)));
		x=1100;
		y=600;
		Foothold_List.add(new Foothold(new FH_Part(new Point(x, y), new Point(x+30, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+30, y), new Point(x+120, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+120, y), new Point(x+210, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+210, y), new Point(x+300, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+300, y), new Point(x+390, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+390, y), new Point(x+480, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+480, y), new Point(x+570, y), 1, FH_Part.PLAIN)
				, new FH_Part(new Point(x+570, y), new Point(x+600, y), 1, FH_Part.PLAIN)));
		
		//add Walls
		setDefaultWalls();
		Wall_List.add(new Wall(510, 1070, 1250, true));
		Wall_List.add(new Wall(1500, 1200, 1250, false));
		Wall_List.add(new Wall(1610, 1150, 1200, false));
		Wall_List.add(new Wall(1720, 1100, 1150, false));
		
		
		//add ladders
		super.setLadders(new Ladder(350, 600, 7));
		super.setLadders(new Ladder(570, 800, 7));
		super.setLadders(new Ladder(1220, 600, 7));
		
		//add mobs
		Mob_List.add(new Mob_Slime(18, Foothold_List.get(4), true));
		Mob_List.add(new Mob_Slime(34, Foothold_List.get(4), true));
		Mob_List.add(new Mob_Slime(50, Foothold_List.get(4), true));
		Mob_List.add(new Mob_Slime(66, Foothold_List.get(4), true));
		Mob_List.add(new Mob_Orange_Mushroom(26, Foothold_List.get(4), true));
		Mob_List.add(new Mob_Orange_Mushroom(42, Foothold_List.get(4), true));
		Mob_List.add(new Mob_Orange_Mushroom(58, Foothold_List.get(4), true));
		Mob_List.add(new Mob_Orange_Mushroom(74, Foothold_List.get(4), true));
		Mob_List.add(new Mob_Snail(15, Foothold_List.get(5), true));
		Mob_List.add(new Mob_Snail(30, Foothold_List.get(5), true));
		Mob_List.add(new Mob_BlueSnail(45, Foothold_List.get(5), true));
		Mob_List.add(new Mob_BlueSnail(60, Foothold_List.get(5), true));
		Mob_List.add(new Mob_RedSnail(75, Foothold_List.get(5), true));
		Mob_List.add(new Mob_RedSnail(90, Foothold_List.get(5), true));
		Mob_List.add(new Mob_Bubbling(30, Foothold_List.get(6), true));
		Mob_List.add(new Mob_Bubbling(50, Foothold_List.get(6), true));
		Mob_List.add(new Mob_Bubbling(70, Foothold_List.get(6), true));
		Mob_List.add(new Mob_Blue_Mushroom(30, Foothold_List.get(7), true));
		Mob_List.add(new Mob_Blue_Mushroom(50, Foothold_List.get(7), true));
		Mob_List.add(new Mob_Blue_Mushroom(70, Foothold_List.get(7), true));
	}

}
