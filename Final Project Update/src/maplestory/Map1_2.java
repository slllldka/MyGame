package maplestory;

import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Map1_2 extends Map {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Map1_2(int CD, String BGName, String GName, String FH1Name, String FH2Name, JFrame _frame, JButton _back
			, boolean _isTown, Map _nearestTown) {
		super(CD, BGName, GName, FH1Name, FH2Name, _frame, _back, _isTown, _nearestTown);
		
		theme = "Henesys";
		
		Stage_Music = new Music("Henesys.wav", -1);
		StageNum = 12;
		
		X_Size = 1200;
		Y_Size = 1200;
		
		CameraFirstX = 0;
		CameraFirstY = 0;
		
		MiniMap_Available = true;
		
		super.setCharacter(120, 300);
		
		//add footholds
		int x = -30, y = 950;
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
				, new FH_Part(new Point(x+990, y), new Point(x+1080, y))
				, new FH_Part(new Point(x+1080, y), new Point(x+1170, y))
				, new FH_Part(new Point(x+1170, y), new Point(x+1260, y))));
		
		
		x = 0;
		y = 300;
		Foothold_List.add(new Foothold(new FH_Part(new Point(x, y), new Point(x+30, y))
				, new FH_Part(new Point(x+30, y), new Point(x+120, y))
				, new FH_Part(new Point(x+120, y), new Point(x+210, y))
				, new FH_Part(new Point(x+210, y), new Point(x+240, y))));
		
		x = 210;
		y = 600;
		Foothold_List.add(new Foothold(new FH_Part(new Point(x, y), new Point(x+30, y))
				, new FH_Part(new Point(x+30, y), new Point(x+120, y))
				, new FH_Part(new Point(x+120, y), new Point(x+210, y))
				, new FH_Part(new Point(x+210, y), new Point(x+300, y))
				, new FH_Part(new Point(x+300, y), new Point(x+390, y))
				, new FH_Part(new Point(x+390, y), new Point(x+480, y))
				, new FH_Part(new Point(x+480, y), new Point(x+570, y))
				, new FH_Part(new Point(x+570, y), new Point(x+660, y))
				, new FH_Part(new Point(x+660, y), new Point(x+750, y))
				, new FH_Part(new Point(x+750, y), new Point(x+780, y))));
		
		//add Walls
		setDefaultWalls();
		
		
		//add ladders
		super.setLadders(new Ladder(180, 300, 4));
		super.setLadders(new Ladder(300, 600, 5));
		super.setLadders(new Ladder(900, 600, 5));
		
		//add mobs
		Mob_List.add(new Mob_MushMom(50, Foothold_List.get(0), true));
	}

}
