package maplestory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

//class for setting Where Character can stand
public class Foothold {
	protected ArrayList<FH_Part> parts = new ArrayList<FH_Part>();
	protected boolean isBottom;
	
	public Foothold(FH_Part... _parts) {
		for(FH_Part part : _parts) {
			parts.add(part);
		}
	}
	public Foothold(boolean _isBottom, FH_Part... _parts) {
		isBottom = _isBottom;
		for(FH_Part part : _parts) {
			parts.add(part);
		}
	}
		
	
	public int firstX() {
		if(parts.get(0).start.x < 0) {
			return 0;
		}
		else {
			return parts.get(0).start.x;
		}
	}
	
	public int lastX() {
		if(parts.get(parts.size()-1).end.x > 1800) {
			return 1800;
		}
		else {
			return parts.get(parts.size()-1).end.x;
		}
	}
	
	public int getY(int x) {
		for(FH_Part part : parts) {
			if(x >= part.start.x && x <= part.end.x) {
				return part.getY(x);
			}
		}
		
		return -1;
	}
	
	public double getDistanceRatio(int x) {
		for(FH_Part part : parts) {
			if(x >= part.start.x && x < part.end.x) {
				return Math.sqrt(Math.pow((double)(part.end.x-part.start.x), 2) + Math.pow((double)(part.end.y-part.start.y), 2))
						/ (part.end.x - part.start.x);
			}
		}
			
		return 1f;
	}
	
	public boolean isLandable(int x, int y) {
		int fy = getY(x);
		if((y >= fy - 1) && (y <= fy + 1)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void drawHenesysFoothold(Graphics2D g, int CameraX, int CameraY, ImageObserver ob) {
		int idx = 0, x = 0, y = 0, length = 0;
		for(FH_Part part : parts) {
			if(part.isDiagonal) {
				if(part.isUphill) {
					x = part.start.x - CameraX;
					y = part.start.y - 72 - CameraY;
					g.drawImage(Maplestory.images.grassySoil_slLU.getImage(), x, y, ob);
					if(isBottom) {
						while(y < Maplestory.current_stage.Y_Size) {
							g.drawImage(Maplestory.images.grassySoil_bsc.getImage(), x, y + 96, ob);
							y += 60;
						}
					}
					else {
						for (int i = 0; i < part.fillBottom; i++) {
							g.drawImage(Maplestory.images.grassySoil_bsc.getImage(), x, y + 96, ob);
							y += 60;
						}
						if(part.bottomType == FH_Part.PLAIN) {
							g.drawImage(Maplestory.images.grassySoil_enH1.getImage(), x, y + 96, ob);
						}
						else if(part.bottomType == FH_Part.LEFT) {
							g.drawImage(Maplestory.images.grassySoil_slLD.getImage(), x, y + 96, ob);
						}
						else if(part.bottomType == FH_Part.RIGHT) {
							g.drawImage(Maplestory.images.grassySoil_slRD.getImage(), x, y + 96, ob);
						}
					}
				}
				else {
					x = part.start.x - CameraX;
					y = part.start.y - 12 - CameraY;
					g.drawImage(Maplestory.images.grassySoil_slRU.getImage(), x, y, ob);
					if(isBottom) {
						while(y < Maplestory.current_stage.Y_Size) {
							g.drawImage(Maplestory.images.grassySoil_bsc.getImage(), x, y + 96, ob);
							y += 60;
						}
					}
					else {
						for (int i = 0; i < part.fillBottom; i++) {
							g.drawImage(Maplestory.images.grassySoil_bsc.getImage(), x, y + 96, ob);
							y += 60;
						}
						if(part.bottomType == FH_Part.PLAIN) {
							g.drawImage(Maplestory.images.grassySoil_enH1.getImage(), x, y + 96, ob);
						}
						else if(part.bottomType == FH_Part.LEFT) {
							g.drawImage(Maplestory.images.grassySoil_slLD.getImage(), x, y + 96, ob);
						}
						else if(part.bottomType == FH_Part.RIGHT) {
							g.drawImage(Maplestory.images.grassySoil_slRD.getImage(), x, y + 96, ob);
						}
					}
				}
			}
			else {
				length = part.end.x - part.start.x;
				if(length == 30) {
					if(idx == 0) {
						x = part.start.x - CameraX;
						y = part.start.y - 16 - CameraY;
						g.drawImage(Maplestory.images.grassySoil_edU.getImage(), x + 2, y, x + 30, y + 40, 0, 0, 28, 40, ob);
						if(isBottom) {
							while(y < Maplestory.current_stage.Y_Size) {
								g.drawImage(Maplestory.images.grassySoil_enV0.getImage(), x, y + 40, ob);
								y += 60;
							}
						}
						else {
							for (int i = 0; i < part.fillBottom; i++) {
								g.drawImage(Maplestory.images.grassySoil_enV0.getImage(), x, y + 40, ob);
								y += 60;
							}
							g.drawImage(Maplestory.images.grassySoil_edD.getImage(), x + 4, y + 40, x + 30, y + 59
									, 0, 0, 26, 19, ob);
						}
					}
					else {
						x = part.start.x - CameraX;
						y = part.start.y - 16 - CameraY;
						g.drawImage(Maplestory.images.grassySoil_edU.getImage(), x, y, x + 30, y + 40, 28, 0, 58, 40, ob);
						if(isBottom) {
							while(y < Maplestory.current_stage.Y_Size) {
								g.drawImage(Maplestory.images.grassySoil_enV1.getImage(), x, y + 40, ob);
								y += 60;
							}
						}
						else {
							for (int i = 0; i < part.fillBottom; i++) {
								g.drawImage(Maplestory.images.grassySoil_enV1.getImage(), x, y + 40, ob);
								y += 60;
							}
							g.drawImage(Maplestory.images.grassySoil_edD.getImage(), x, y + 40, x + 27, y + 59
									, 26, 0, 53, 19, ob);
						}
					}
				}
				else if(length == 60) {
					x = part.start.x - CameraX;
					y = part.start.y - 16 - CameraY;
					g.drawImage(Maplestory.images.grassySoil_edU.getImage(), x + 2, y, ob);
					if(isBottom) {
						while(y < Maplestory.current_stage.Y_Size) {
							g.drawImage(Maplestory.images.grassySoil_enV0.getImage(), x, y + 40, ob);
							g.drawImage(Maplestory.images.grassySoil_enV1.getImage(), x + 30, y + 40, ob);
							y += 60;
						}
					}
					else {
						for (int i = 0; i < part.fillBottom; i++) {
							g.drawImage(Maplestory.images.grassySoil_enV0.getImage(), x, y + 40, ob);
							g.drawImage(Maplestory.images.grassySoil_enV1.getImage(), x + 30, y + 40, ob);
							y += 60;
						}
						g.drawImage(Maplestory.images.grassySoil_edD.getImage(), x + 4, y + 40, ob);
					}
				}
				else if(length == 90) {
					x = part.start.x - CameraX;
					y = part.start.y + 24 - Maplestory.images.grassySoil_enH0.getIconHeight() - CameraY;
					g.drawImage(Maplestory.images.grassySoil_enH0.getImage(), x, y, ob);
					
					y += Maplestory.images.grassySoil_enH0.getIconHeight();
					if(isBottom) {
						while(y < Maplestory.current_stage.Y_Size) {
							g.drawImage(Maplestory.images.grassySoil_bsc.getImage(), x, y, ob);
							y += 60;
						}
					}
					else {
						for (int i = 0; i < part.fillBottom; i++) {
							g.drawImage(Maplestory.images.grassySoil_bsc.getImage(), x, y, ob);
							y += 60;
						}
						if(part.bottomType == FH_Part.PLAIN) {
							g.drawImage(Maplestory.images.grassySoil_enH1.getImage(), x, y, ob);
						}
						else if(part.bottomType == FH_Part.LEFT) {
							g.drawImage(Maplestory.images.grassySoil_slLD.getImage(), x, y, ob);
						}
						else if(part.bottomType == FH_Part.RIGHT) {
							g.drawImage(Maplestory.images.grassySoil_slRD.getImage(), x, y, ob);
						}
					}
				}
			}
			
			idx++;
		}
	}
	
	public void drawElliniaSleepyWoodFoothold(Graphics2D g, int CameraX, int CameraY, ImageObserver ob) {
		int x = 0, y = 0;
		for(FH_Part part : parts) {
			x = part.start.x;
			y = part.start.y - 10;
			if(part.end.x - part.start.x == 50) {
				g.drawImage(Maplestory.current_stage.Foothold1Img.getImage(), x - CameraX, y - CameraY, ob);
			}
			else if(part.end.x - part.start.x == 120) {
				g.drawImage(Maplestory.current_stage.Foothold2Img.getImage(), x - CameraX, y - CameraY, ob);
			}
			else {
				g.drawImage(Maplestory.current_stage.GroundImg.getImage(), x - CameraX, y + 10 - CameraY, ob);
			}
		}
	}
	
	public void drawFoothold(Graphics2D g, int CameraX, int CameraY, ImageObserver ob) {
		if(Maplestory.current_stage.theme.equals("Henesys")) {
			drawHenesysFoothold(g, CameraX, CameraY, ob);
		}
		else if(Maplestory.current_stage.theme.equals("Ellinia")) {
			drawElliniaSleepyWoodFoothold(g, CameraX, CameraY, ob);
		}
		else if(Maplestory.current_stage.theme.equals("SleepyWood")) {
			drawElliniaSleepyWoodFoothold(g, CameraX, CameraY, ob);
		}
		
		/*for(Foothold_Part part : parts) {
			g.setColor(Color.RED);
			g.drawLine(part.start.x - CameraX, part.start.y - CameraY, part.end.x - CameraX, part.end.y - CameraY);
			g.drawLine(part.start.x - CameraX, part.start.y - CameraY + 1, part.end.x - CameraX, part.end.y - CameraY + 1);
			g.drawLine(part.start.x - CameraX, part.start.y - CameraY + 2, part.end.x - CameraX, part.end.y - CameraY + 2);
		}*/
	}
	
	public void dsiFoothold(Graphics2D g, int offset, ImageObserver ob) {
		for(FH_Part part : parts) {
			g.setColor(Color.GREEN);
			g.drawLine(part.start.x / offset, part.start.y / offset, part.end.x / offset, part.end.y / offset);
		}
	}
}
