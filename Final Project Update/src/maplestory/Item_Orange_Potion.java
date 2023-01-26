package maplestory;

import javax.swing.ImageIcon;

public class Item_Orange_Potion extends Item{
	protected static int item_code = 2;
	protected static String type = "Consume";
	protected static String name = "주황 포션";
	protected static String tooltip = "붉은 약초의 농축 물약이다.\nHP를 약 150 회복시킨다.";
	protected static ImageIcon Dropped_Icon = Maplestory.images.Orange_Potion_Raw_Icon;
	protected static ImageIcon Icon = Maplestory.images.Orange_Potion_Icon;
	protected static ImageIcon Info = Maplestory.images.Orange_Potion_Info;
	protected static int sellPrice = 24;
	
	public Item_Orange_Potion() {
		Quantity = 1;
		infiniteQuantity = true;
		angle = 0;
		pickable = false;
	}
	
	public Item_Orange_Potion(int _Quantity) {
		Quantity = _Quantity;
		angle = 0;
		pickable = false;
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
	public ImageIcon getRawIcon() {
		return Dropped_Icon;
	}

	@Override
	public ImageIcon getIcon() {
		return Icon;
	}
	
	@Override
	public ImageIcon getInfo() {
		return Info;
	}
	
	@Override
	public void Use(int index) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				synchronized(Maplestory.player.inventory.Consume_inventory_list) {
					Music Potion_Use_Music = new Music("Potion_Use.wav", 1);
					Potion_Use_Music.play();
					Maplestory.player.HP_Heal(150);
					Maplestory.player.inventory.Reduce_Item(
							Maplestory.player.inventory.Consume_inventory_list.get(index), 1);

					Maplestory.player.Item_usable = false;
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Maplestory.player.Item_usable = true;
				}
			}
			
		};
		if(Maplestory.player.Item_usable) {
			Maplestory.thread_pool.submit(runnable);
		}
	}

	@Override
	public int getSellPrice() {
		// TODO Auto-generated method stub
		return sellPrice;
	}
	
	@Override
	public Item getNew(int Quantity) {
		Item result = new Item_Orange_Potion(Quantity);
		return result;
	}
}
