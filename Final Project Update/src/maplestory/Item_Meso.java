package maplestory;

import javax.swing.ImageIcon;

public class Item_Meso extends Item{
	protected static int item_code = -1;
	protected static String type = "Meso";
	protected static String name = "메소";
	protected static String tooltip = "메소";
	
	protected static ImageIcon Meso_Bronze_0 = Maplestory.images.Meso_Bronze_0;
	protected static ImageIcon Meso_Bronze_1 = Maplestory.images.Meso_Bronze_1;
	protected static ImageIcon Meso_Bronze_2 = Maplestory.images.Meso_Bronze_2;
	protected static ImageIcon Meso_Bronze_3 = Maplestory.images.Meso_Bronze_3;
	protected static ImageIcon Meso_Gold_0 = Maplestory.images.Meso_Gold_0;
	protected static ImageIcon Meso_Gold_1 = Maplestory.images.Meso_Gold_1;
	protected static ImageIcon Meso_Gold_2 = Maplestory.images.Meso_Gold_2;
	protected static ImageIcon Meso_Gold_3 = Maplestory.images.Meso_Gold_3;
	protected static ImageIcon Meso_Bill_0 = Maplestory.images.Meso_Bill_0;
	protected static ImageIcon Meso_Bill_1 = Maplestory.images.Meso_Bill_1;
	protected static ImageIcon Meso_Bill_2 = Maplestory.images.Meso_Bill_2;
	protected static ImageIcon Meso_Bill_3 = Maplestory.images.Meso_Bill_3;
	protected static ImageIcon Meso_Bag_0 = Maplestory.images.Meso_Bag_0;
	protected static ImageIcon Meso_Bag_1 = Maplestory.images.Meso_Bag_1;
	protected static ImageIcon Meso_Bag_2 = Maplestory.images.Meso_Bag_2;
	protected static ImageIcon Meso_Bag_3 = Maplestory.images.Meso_Bag_3;
	
	protected ImageIcon Icon0, Icon1, Icon2, Icon3;
	protected ImageIcon current_Icon;
	protected int offset = 0;
	
	public Item_Meso(int _Quantity) {
		Quantity = _Quantity;
		angle = 0;
		pickable = false;
		
		if(Quantity >= 1 && Quantity < 50) {
			Icon0 = Meso_Bronze_0;
			Icon1 = Meso_Bronze_1;
			Icon2 = Meso_Bronze_2;
			Icon3 = Meso_Bronze_3;
		}
		if(Quantity >= 50 && Quantity < 100) {
			Icon0 = Meso_Gold_0;
			Icon1 = Meso_Gold_1;
			Icon2 = Meso_Gold_2;
			Icon3 = Meso_Gold_3;
		}
		if(Quantity >= 100 && Quantity < 1000) {
			Icon0 = Meso_Bill_0;
			Icon1 = Meso_Bill_1;
			Icon2 = Meso_Bill_2;
			Icon3 = Meso_Bill_3;
		}
		if(Quantity >= 1000) {
			Icon0 = Meso_Bag_0;
			Icon1 = Meso_Bag_1;
			Icon2 = Meso_Bag_2;
			Icon3 = Meso_Bag_3;
		}
		
		current_Icon = Icon0;
	}

	@Override
	public int getItemCode() {
		return item_code;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getTooltip() {
		return tooltip;
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public Item getNew(int Quantity) {
		Item result = new Item_Meso(Quantity);
		return result;
	}

	@Override
	public ImageIcon getRawIcon() {
		// TODO Auto-generated method stub
		return current_Icon;
	}

	@Override
	public ImageIcon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageIcon getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void ChangeIcon() {
		Runnable runnable = new Runnable() {
			int index = 0;

			@Override
			public void run() {
				while(!remove) {
					if(index == 0) {
						current_Icon = Icon0;
						if(Quantity >= 100 && Quantity < 1000) {
							offset = 0;
						}
					}
					else if(index == 1) {
						current_Icon = Icon1;
						if(Quantity >= 100 && Quantity < 1000) {
							offset = 0;
						}
					}
					else if(index == 2) {
						current_Icon = Icon2;
						if(Quantity >= 100 && Quantity < 1000) {
							offset = 0;
						}
					}
					else if(index == 3) {
						current_Icon = Icon3;
						if(Quantity >= 100 && Quantity < 1000) {
							offset = 0;
						}
					}
					index = (index + 1) % 4;
					
					try {
						Thread.sleep(120);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		};
		Maplestory.thread_pool.submit(runnable);
	}

	@Override
	public int getSellPrice() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getOffset() {
		return offset;
	}
}