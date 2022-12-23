package maplestory;

import javax.swing.ImageIcon;

public class LevelUp {
	protected ImageIcon current_Img = Maplestory.images.LevelUp_Effect[0];
	
	protected boolean remove = false;
	protected static int[] x_offset = {64, 100, 106, 121, 146, 121, 146, 107, 122, 147, 122, 147, 84, 84, 84, 84, 84, 84, 84, 82, 81, 80, 69};
	protected static int[] y_offset = {59, 221, 275, 302, 316, 332, 288, 299, 303, 316, 161, 163, 170, 184, 189, 187, 179, 145, 148, 110, 97, 71, 57};
	
	protected int current_x_offset = 0, current_y_offset = 0;
	
	public LevelUp() {
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<23;i++) {
					current_Img = Maplestory.images.LevelUp_Effect[i];
					current_x_offset = x_offset[i];
					current_y_offset = y_offset[i];
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				remove = true;
				
			}
		};
		Maplestory.thread_pool.submit(runnable);
	}
	
	static {
		for(int i=0; i < x_offset.length; i++) {
			x_offset[i] = 64 - x_offset[i];
		}
		for(int i=0; i < y_offset.length; i++) {
			y_offset[i] = 59 - y_offset[i];
		}
	
	}
}
