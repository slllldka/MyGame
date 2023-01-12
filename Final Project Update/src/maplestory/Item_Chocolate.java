package maplestory;

import javax.swing.ImageIcon;

public class Item_Chocolate extends Item{
	protected static int item_code = 11;
	protected static String type = "Consume";
	protected static String name = "초콜릿";
	protected static String tooltip = "달콤한 향이 진하게 나는 밀크초콜릿이다. 초코스틱의 재료가 되기도 한다는데..\nHP와 MP를 각각 1000씩 회복시켜준다.";
	protected static ImageIcon Dropped_Icon = Maplestory.images.Chocolate_Raw_Icon;
	protected static ImageIcon Icon = Maplestory.images.Chocolate_Icon;
	protected static ImageIcon Info = Maplestory.images.Chocolate_Info;
	protected static int sellPrice = 1500;
	
	public Item_Chocolate() {
		Quantity = 1;
		infiniteQuantity = true;
		angle = 0;
		pickable = false;
	}
	
	public Item_Chocolate(int _Quantity) {
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
					Maplestory.player.HP_Heal(1000);
					Maplestory.player.MP_Heal(1000);
					Maplestory.player.inventory.Reduce_Item(index, 1);
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
		Item result = new Item_Chocolate(Quantity);
		return result;
	}
}
