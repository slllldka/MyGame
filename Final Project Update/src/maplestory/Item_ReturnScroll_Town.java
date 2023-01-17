package maplestory;

import javax.swing.ImageIcon;

public class Item_ReturnScroll_Town extends Item{
	protected static int item_code = 12;
	protected static String type = "Consume";
	protected static String name = "마을 귀환 주문서";
	protected static String tooltip = "현재 위치에서 가장 가까운 마을로 귀환할 수 있는 주문서이다. 헌 번 사용하면 사라진다. 모든 #c마을 잡화상점#에서 구입할 수 있다.";
	protected static ImageIcon Dropped_Icon = Maplestory.images.ReturnScroll_Town_Raw_Icon;
	protected static ImageIcon Icon = Maplestory.images.ReturnScroll_Town_Icon;
	protected static ImageIcon Info = Maplestory.images.ReturnScroll_Town_Info;
	protected static int sellPrice = 200;
	
	public Item_ReturnScroll_Town() {
		Quantity = 1;
		infiniteQuantity = true;
		angle = 0;
		pickable = false;
	}
	
	public Item_ReturnScroll_Town(int _Quantity) {
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
					if(Maplestory.current_stage != Maplestory.current_stage.nearestTown) {
						Music Scroll_Use_Music = new Music("ReturnScrollMapUse.wav", 1);
						Scroll_Use_Music.play();
						Maplestory.player.inventory.Reduce_Item(index, 1);
						Maplestory.player.Item_usable = false;
						
						Map nearestTown = Maplestory.current_stage.nearestTown;
						Maplestory.current_stage.close(null);
						nearestTown.open(null);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Maplestory.player.Item_usable = true;
					}
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
		Item result = new Item_ReturnScroll_Town(Quantity);
		return result;
	}
}
