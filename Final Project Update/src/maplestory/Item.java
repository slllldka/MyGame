package maplestory;

import javax.swing.ImageIcon;

public abstract class Item {
	protected int Quantity, angle;
	protected int X_Center, Y_Center;
	protected float alpha = 1f;
	protected long timer;
	protected boolean pickable, remove = false, infiniteQuantity = false;
	protected static long disappear_period = 150000;
	
	public void Drop(int _X_Center, int _Y_Center) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				remove = false;
				Music DragEnd_Music = new Music("DragEnd.wav", 1);
				DragEnd_Music.play();
				
				if(getType().equals("Meso")) {
					ChangeIcon();
				}
				
				angle = 0;
				X_Center = _X_Center;
				Y_Center = _Y_Center;
				pickable = false;
				
				for(int i=0;i<50;i++) {
					Y_Center--;
					angle+=15;
					try {
						Thread.sleep(8);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				pickable = true;
				while(!IsLandable()) {
					Y_Center++;
					angle+=15;
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
				}
				angle = 0;
				
				timer = System.currentTimeMillis();
				int i=0;
				while(true) {
					if(!pickable) {
						PickedUp();
						break;
					}
					if(System.currentTimeMillis() > timer + disappear_period) {
						Disappear();
						break;
					}
						
					if(i >= 0 && i < 5) {
						Y_Center--;
					}
					else if(i >= 5 && i < 10) {
						Y_Center++;
					}
					
					if(i==9) {
						i = 0;
					}
					else {
						i++;
					}
					
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		};
		Maplestory.thread_pool.submit(runnable);
		
		Maplestory.current_stage.Item_List.add(this);
	}
	
	public void Disappear() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				pickable = false;
				for(float i=1f;i>0f;i-=0.001f) {
					alpha = i;
					try {
						Thread.sleep(1);
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
	
	public void PickedUp() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Music PickUpItem_Music = new Music("PickUpItem.wav", 1);
				PickUpItem_Music.play();
				
				int Char_X_Center, Char_Y_Center;
				float Delta_X, Delta_Y;
				float X_Center_float = X_Center;
				float Y_Center_float = Y_Center;
				
				for(float i=1f;i>0f;i-=0.003f) {
					alpha = i;
					Char_X_Center = Maplestory.player.CharacterX + Character.CharacterWidth/2;
					Char_Y_Center = Maplestory.player.CharacterY - Character.CharacterHeight/2;
					Delta_X = Char_X_Center - X_Center;
					Delta_Y = Char_Y_Center - Y_Center;
					X_Center_float += (Delta_X / 333);
					Y_Center_float += (Delta_Y / 333);
					X_Center = (int) X_Center_float;
					Y_Center = (int) Y_Center_float;
					try {
						Thread.sleep(1);
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
	
	public boolean IsLandable() {
		for (Foothold foothold : Maplestory.current_stage.Foothold_List) {
			if(foothold.isLandable(X_Center, Y_Center + getRawIcon().getIconHeight()/2 - 3)) {
				return true;
			}
		}
		return false;
	}
	
	public abstract int getItemCode();
	public abstract String getName();
	public abstract String getTooltip();
	public abstract String getType();
	public abstract ImageIcon getRawIcon();
	public abstract ImageIcon getIcon();
	public abstract ImageIcon getInfo();
	public abstract int getSellPrice();
	public abstract Item getNew(int Quantity);
	
	//for consume
	public void Use(int index) {}
	
	//for meso
	public void ChangeIcon() {}
	public int getOffset() {
		return -1;
	}
}
