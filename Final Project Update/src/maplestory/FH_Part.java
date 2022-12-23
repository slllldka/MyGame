package maplestory;

import java.awt.Point;

public class FH_Part {
	protected Point start, end;
	protected boolean isDiagonal;
	protected boolean isUphill;
	protected int fillBottom = 0;
	protected int bottomType = 0;
	
	protected static final int PLAIN = 0, LEFT = 1, RIGHT = 2;

	
	public FH_Part(Point _start, Point _end) {
		start = _start;
		end = _end;
		
		if(start.y != end.y) {
			isDiagonal = true;
		}
		else {
			isDiagonal = false;
		}
		
		if(end.y <= start.y) {
			isUphill = true;
		}
		else {
			isUphill = false;
		}
	}
	public FH_Part(Point _start, Point _end, int _fillBottom, int _bottomType) {
		start = _start;
		end = _end;
		fillBottom = _fillBottom;
		bottomType = _bottomType;
		
		if(start.y != end.y) {
			isDiagonal = true;
		}
		else {
			isDiagonal = false;
		}
		
		if(end.y <= start.y) {
			isUphill = true;
		}
		else {
			isUphill = false;
		}
	}
	
	public int getY(int x) {
		return (end.y - start.y) * (x - start.x) / (end.x - start.x) + start.y;
	}
}
