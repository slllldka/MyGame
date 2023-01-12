package maplestory;

public class Portal {
	protected static boolean isAvailable = true;
	
	protected Stage map;
	protected Portal link;
	protected int xcenter, xstart, xend, y;
	
	public Portal(int _xcenter, int _y, Stage _map) {
		xcenter = _xcenter;
		xstart = xcenter - 30;
		xend = xcenter + 30;
		y = _y;
		map = _map;
	}
}
