package maplestory;

public class MeteorInfo {
	private int first_x, first_y, current_x, current_y, width, height, xd, yd, distance, sleep;
	private boolean first_visible, current_visible;
	
	public MeteorInfo(int _first_x, int _first_y, int _width, int _height, int _xd, int _yd, int _distance, int _sleep, boolean _first_visible) {
		first_x = _first_x;
		first_y = _first_y;
		current_x = _first_x;
		current_y = _first_y;
		width = _width;
		height = _height;
		xd = _xd;
		yd = _yd;
		distance = _distance;
		sleep = _sleep;
		first_visible = _first_visible;
		current_visible = _first_visible;
	}

	public int getFirst_x() {
		return first_x;
	}

	public int getFirst_y() {
		return first_y;
	}

	public int getCurrent_x() {
		return current_x;
	}

	public int getCurrent_y() {
		return current_y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getXd() {
		return xd;
	}

	public int getYd() {
		return yd;
	}

	public int getDistance() {
		return distance;
	}

	public int getSleep() {
		return sleep;
	}

	public boolean isFirst_visible() {
		return first_visible;
	}

	public boolean isCurrent_visible() {
		return current_visible;
	}

	public void setCurrent_x(int _current_x) {
		current_x = _current_x;
	}

	public void setCurrent_y(int _current_y) {
		current_y = _current_y;
	}

	public void setCurrent_visible(boolean _current_visible) {
		current_visible = _current_visible;
	}
	
}